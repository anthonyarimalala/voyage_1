
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
    (SELECT COUNT(voyage) FROM v_reservation WHERE id_voyage = vr.id_voyage AND genre=0) AS somme_femme,
    (SELECT COUNT(voyage) FROM v_reservation WHERE id_voyage = vr.id_voyage AND genre=1) AS somme_homme
    FROM v_reservation vr
    GROUP BY vr.id_voyage, vr.voyage;
