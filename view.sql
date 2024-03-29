CREATE OR REPLACE VIEW v_bouquet_activite AS
SELECT
        lba.*,
        b.bouquet,
        a.activite,
        a.prix_u
    FROM l_bouquet_activite lba
    JOIN bouquet b ON lba.id_bouquet = b.id_bouquet
    JOIN activite a ON lba.id_activite = a.id_activite;



CREATE OR REPLACE VIEW v_voyage_exception AS
SELECT
        v.*,
        l.lieu,
        b.bouquet,
        d.duree
    FROM voyage v
    JOIN lieu l ON v.id_lieu = l.id_lieu
    JOIN bouquet b ON v.id_bouquet = b.id_bouquet
    JOIN duree d ON v.id_duree = d.id_duree;

-- Miasa ao am v_voyage_bouquet_activite
CREATE OR REPLACE VIEW v_voyage AS
SELECT 
        v.id_voyage,
        v.id_bouquet,
        v.id_lieu,
        v.id_duree,
        v.prix,
        v.voyage,
        v.description,
        l.lieu,
        d.duree,
        b.bouquet,
        COALESCE((SELECT SUM(prix_tot_employe) FROM v_voyage_employe WHERE id_voyage=v.id_voyage), 0) AS prix_tot_employe,
        COALESCE(SUM(a.prix_u*lfc.quantite), 0) AS prix_tot_activite,
        (v.prix - (COALESCE(SUM(a.prix_u*lfc.quantite), 0) + COALESCE((SELECT SUM(prix_tot_employe) FROM v_voyage_employe WHERE id_voyage=v.id_voyage), 0))) AS benefice
    FROM voyage v
    JOIN bouquet b ON v.id_bouquet = b.id_bouquet
    JOIN lieu l ON v.id_lieu = l.id_lieu
    JOIN duree d ON v.id_duree = d.id_duree
    LEFT JOIN l_formule_composition lfc ON l.id_lieu=lfc.id_lieu AND b.id_bouquet=lfc.id_bouquet AND d.id_duree=lfc.id_duree
    LEFT JOIN activite a ON lfc.id_activite = a.id_activite
    GROUP BY 
        v.id_voyage,
        v.id_bouquet,
        v.id_lieu,
        v.id_duree,
        v.prix,
        v.voyage,
        v.description,
        l.lieu,
        d.duree,
        b.bouquet;


CREATE OR REPLACE VIEW v_voyage_bouquet_activite AS
SELECT 
        v.*,
        l.lieu,
        d.duree,
        b.bouquet,
        vv.prix_tot_employe,
        vv.prix_tot_activite,
        vv.benefice,
        a.id_activite,
        a.activite
    FROM voyage v
    JOIN l_bouquet_activite lba ON v.id_bouquet = lba.id_bouquet
    JOIN bouquet b ON lba.id_bouquet = b.id_bouquet
    JOIN activite a on lba.id_activite = a.id_activite
    JOIN lieu l ON v.id_lieu = l.id_lieu
    JOIN duree d ON v.id_duree = d.id_duree
    JOIN v_voyage vv ON v.id_voyage = vv.id_voyage;

CREATE OR REPLACE VIEW v_voyage_employe AS
SELECT 
        lve.*, 
        v.voyage, 
        d.duree,
        v.id_duree, 
        e.nom, 
        e.new_prix AS prix,
        d.valeur,
        (e.new_prix * lve.volume_h * d.valeur) AS prix_tot_employe
    FROM l_voyage_employe lve
    JOIN voyage v ON lve.id_voyage = v.id_voyage
    JOIN v_employe e ON lve.id_employe = e.id_employe
    JOIN duree d ON v.id_duree = d.id_duree;

-- ilaina am:
--     v_stock_quantite_reste
CREATE OR REPLACE VIEW v_stock AS
SELECT 
        a.id_activite,
        a.activite,
        COALESCE(SUM(s.entree - s.sortie), 0) AS restant,
        a.prix_u,
        COALESCE(SUM(s.entree - s.sortie), 0)*a.prix_u AS prix_totale
    FROM activite a
    LEFT JOIN stock s ON s.id_activite = a.id_activite
    GROUP BY 
        a.id_activite,
        a.activite ;

CREATE OR REPLACE VIEW v_stock_quantite_reste AS
SELECT
        v.*,
        l.lieu,
        b.bouquet,
        d.duree,
        vs.activite,
        lfc.id_activite,
        lfc.quantite,
        vs.restant
    FROM voyage v
    JOIN l_formule_composition lfc ON v.id_bouquet=lfc.id_bouquet AND v.id_lieu=lfc.id_lieu AND v.id_duree=lfc.id_duree
    JOIN lieu l ON v.id_lieu = l.id_lieu
    JOIN duree d ON v.id_duree = d.id_duree
    JOIN bouquet b ON v.id_bouquet = b.id_bouquet
    JOIN v_stock vs ON lfc.id_activite = vs.id_activite
    ;

CREATE OR REPLACE VIEW v_employe AS
SELECT 
        e.*,
        EXTRACT(YEAR FROM AGE(CURRENT_DATE, date_embauche))::INTEGER AS annee_experience,
        COALESCE(lpe.promotion, (SELECT promotion FROM promo_emp WHERE annee_requis=(SELECT MAX(annee_requis) FROM promo_emp)))::VARCHAR(255) AS experience,
        COALESCE(lpe.multipl, (SELECT multipl FROM promo_emp WHERE annee_requis=(SELECT MAX(annee_requis) FROM promo_emp))) AS multipl,
        COALESCE((e.prix * lpe.multipl), (e.prix * (SELECT multipl FROM promo_emp WHERE annee_requis=(SELECT MAX(annee_requis) FROM promo_emp)))) AS new_prix
    FROM employe e
    LEFT JOIN l_promo_emp lpe ON EXTRACT(YEAR FROM AGE(CURRENT_DATE, e.date_embauche))::INTEGER = lpe.annee_requis;

CREATE OR REPLACE VIEW v_reservation AS
SELECT
        r.*,
        v.voyage,
        c.nom,
        c.prenom,
        c.genre
    FROM reservation r
    JOIN client c ON r.id_client = c.id_client
    JOIN voyage v ON r.id_voyage = v.id_voyage
    ;

CREATE OR REPLACE VIEW v_statistic AS
SELECT
    vr.id_voyage,
    vr.voyage,
    (SELECT SUM(quantite) FROM v_reservation WHERE id_voyage = vr.id_voyage AND genre=0 AND v_reservation.etat=10) AS somme_femme,
    (SELECT SUM(quantite) FROM v_reservation WHERE id_voyage = vr.id_voyage AND genre=1 AND v_reservation.etat=10) AS somme_homme
    FROM v_reservation vr
    WHERE vr.etat = 10
    GROUP BY vr.id_voyage, vr.voyage;

CREATE OR REPLACE VIEW v_formule_composition AS
SELECT
        lfc.*,
        l.lieu,
        b.bouquet,
        d.duree,
        a.activite
    FROM l_formule_composition lfc
    JOIN lieu l ON lfc.id_lieu=l.id_lieu
    JOIN bouquet b ON lfc.id_bouquet=b.id_bouquet
    JOIN duree d ON lfc.id_duree=d.id_duree
    JOIN activite a ON lfc.id_activite=a.id_activite
    ORDER BY l.lieu ASC, b.bouquet ASC, d.duree ASC, a.activite ASC;