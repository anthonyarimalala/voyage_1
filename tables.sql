CREATE TABLE activite (
    id_activite SERIAL PRIMARY KEY,
    activite VARCHAR(255) NOT NULL,
    prix_u FLOAT NOT NULL
);

CREATE TABLE bouquet (
    id_bouquet SERIAL PRIMARY KEY,
    bouquet VARCHAR(255) NOT NULL
);

CREATE TABLE duree (
    id_duree SERIAL PRIMARY KEY,
    duree VARCHAR(255) NOT NULL,
    valeur FLOAT NOT NULL
);

CREATE TABLE promo_emp(
    id_promotion SERIAL PRIMARY KEY,
    promotion VARCHAR(255),
    annee_requis INTEGER,
    multipl FLOAT DEFAULT(1)
);

CREATE TABLE l_promo_emp(
    id_l_promotion SERIAL PRIMARY KEY,
    id_promotion INTEGER REFERENCES promo_emp(id_promotion) ON DELETE CASCADE,
    promotion VARCHAR(255),
    annee_requis INTEGER,
    multipl FLOAT DEFAULT(1)
);

CREATE TABLE experience(
    id_experience SERIAL PRIMARY KEY,
    experience VARCHAR(255) NOT NULL,
    annee INTEGER,
    multipl FLOAT
);

CREATE TABLE employe (
    id_employe SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    fonction VARCHAR(255) NOT NULL,
    prix FLOAT NOT NULL,
    date_embauche DATE
);


CREATE TABLE lieu (
    id_lieu SERIAL PRIMARY KEY,
    lieu VARCHAR(255) NOT NULL
);

CREATE TABLE voyage (
    id_voyage SERIAL PRIMARY KEY,
    id_bouquet INT REFERENCES bouquet(id_bouquet) ON DELETE CASCADE,
    id_lieu INT REFERENCES lieu(id_lieu) ON DELETE CASCADE,
    id_duree INT REFERENCES duree(id_duree) ON DELETE CASCADE,
    prix FLOAT NOT NULL,
    voyage VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE l_bouquet_activite (
    id_bouquet_activite SERIAL PRIMARY KEY,
    id_bouquet INT REFERENCES bouquet(id_bouquet) ON DELETE CASCADE,
    id_activite INT REFERENCES activite(id_activite) ON DELETE CASCADE
);

CREATE TABLE l_formule_composition (
    id_formule_composition SERIAL PRIMARY KEY,
    id_lieu INT REFERENCES lieu(id_lieu) ON DELETE CASCADE,
    id_bouquet INT REFERENCES bouquet(id_bouquet) ON DELETE CASCADE,
    id_duree INT REFERENCES duree(id_duree) ON DELETE CASCADE,
    id_activite INT REFERENCES activite(id_activite) ON DELETE CASCADE,
    quantite FLOAT NOT NULL
);

CREATE TABLE stock (
    id_stock SERIAL PRIMARY KEY,
    date_modif DATE DEFAULT CURRENT_DATE,
    id_activite INT REFERENCES activite(id_activite) ON DELETE CASCADE,
    entree FLOAT,
    sortie FLOAT
);

create table client (
  id_client serial primary key,
  nom varchar(255),
  prenom varchar(255),
  genre int
);

-- 0: Annulé
-- 5: En attente
-- 10: Confirmé
CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    id_client INTEGER REFERENCES client(id_client) ON DELETE CASCADE,
    date_reservation DATE DEFAULT CURRENT_DATE,
    id_voyage INT REFERENCES voyage(id_voyage) ON DELETE CASCADE,
    quantite FLOAT,
    etat INTEGER DEFAULT 5
);

CREATE TABLE l_voyage_employe(
    id_voyage_employe SERIAL PRIMARY KEY,
    id_voyage INTEGER REFERENCES voyage(id_voyage) ON DELETE CASCADE,
    id_employe INTEGER REFERENCES employe(id_employe) ON DELETE CASCADE,
    volume_h FLOAT
);


-- ALTER TABLE stock
-- DROP CONSTRAINT IF EXISTS stock_id_activite_fkey,  -- Supprimez la contrainte existante si elle existe
-- ADD CONSTRAINT stock_id_activite_fkey
--     FOREIGN KEY (id_activite)
--     REFERENCES activite(id_activite)
--     ON DELETE CASCADE;