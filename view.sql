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
        SUM(a.prix_u*lfc.quantite) AS prix_tot_activite
    FROM voyage v
    JOIN bouquet b ON v.id_bouquet = b.id_bouquet
    JOIN lieu l ON v.id_lieu = l.id_lieu
    JOIN duree d ON v.id_duree = d.id_duree
    JOIN l_formule_composition lfc ON l.id_lieu=lfc.id_lieu AND b.id_bouquet=lfc.id_bouquet AND d.id_duree=lfc.id_duree
    JOIN activite a ON lfc.id_activite = a.id_activite
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
        a.id_activite,
        a.activite,
        vv.prix_tot_activite
    FROM voyage v
    JOIN l_bouquet_activite lba ON v.id_bouquet = lba.id_bouquet
    JOIN bouquet b ON lba.id_bouquet = b.id_bouquet
    JOIN activite a on lba.id_activite = a.id_activite
    JOIN lieu l ON v.id_lieu = l.id_lieu
    JOIN duree d ON v.id_duree = d.id_duree
    JOIN v_voyage vv ON v.id_voyage = vv.id_voyage;

-- ilaina am:
--     v_stock_quantite_reste
CREATE OR REPLACE VIEW v_stock AS
SELECT 
        a.id_activite,
        a.activite,
        COALESCE(SUM(s.entree - s.sortie), 0) AS restant
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