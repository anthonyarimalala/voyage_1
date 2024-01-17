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