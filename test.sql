select * from v_reservation
WHERE id_voyage=15
ORDER BY CASE etat
    WHEN 5 THEN 1
    WHEN 10 THEN 2
    WHEN 0 THEN 3
END, date_reservation DESC;

