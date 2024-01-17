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

CREATE TABLE employe (
    id_employe SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    fonction VARCHAR(255) NOT NULL,
    prix FLOAT NOT NULL
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

