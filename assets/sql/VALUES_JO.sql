INSERT INTO PAYS (nomPays) VALUES 
('France'), 
('Allemagne'), 
('Espagne'),
('Italie'),
('Royaume-Uni'),
('Belgique'),
('Suisse'),
('Pays-Bas'),
('Portugal'),
('Luxembourg');

INSERT INTO SPORT (nomSport, typeSport, coeffAgilite, coeffEndurance, coeffForce) VALUES 
('Natation', 'Individuel', 6, 9, 7),
('Athletisme', 'Individuel', 7, 8, 6),
('Escrime', 'Individuel', 8, 7, 6),
('Volley-Ball', 'Collectif', 8, 6, 5),
('Handball', 'Collectif', 8, 7, 6);

INSERT INTO COMPETITIONS VALUES 
('Athletisme 110 haies', 'HOMME', 'Oui', 'Athletisme'),
('Athletisme 110 haies', 'FEMME', 'Oui', 'Athletisme'),
('Natation 100 brasse', 'HOMME', 'Oui', 'Natation'),
('Natation 100 brasse', 'FEMME', 'Oui', 'Natation'),
('Escrime fleuret', 'HOMME', 'Oui', 'Escrime'),
('Escrime epee', 'HOMME', 'Oui', 'Escrime'),
('Escrime epee', 'FEMME', 'Oui', 'Escrime'),
('Escrime fleuret', 'FEMME', 'Oui', 'Escrime'),
('Natation relais libre', 'HOMME', 'Non', 'Natation'),
('Natation relais libre', 'FEMME', 'Non', 'Natation'),
('Athletisme relais 400m', 'HOMME', 'Non', 'Athletisme'),
('Athletisme relais 400m', 'FEMME', 'Non', 'Athletisme'),
('Volley-Ball', 'HOMME', 'Non', 'Volley-Ball'),
('Volley-Ball', 'FEMME', 'Non', 'Volley-Ball'),
('Handball', 'HOMME', 'Non', 'Handball'),
('Handball', 'FEMME', 'Non', 'Handball');


INSERT INTO EQUIPES (nomEquipe, tailleMax, enRelais, nomPays, idCompetition) VALUES 
('Equipe France', 23, 'Non', 'France', 1), 
('Equipe Allemagne', 23, 'Non', 'Allemagne', 1),
('Equipe Espagne', 23, 'Non', 'Espagne', 1),
('Equipe Italie', 23, 'Non', 'Italie', 1),
('Equipe Royaume-Uni', 23, 'Non', 'Royaume-Uni', 1),
('Equipe Belgique', 23, 'Non', 'Belgique', 1),
('Equipe Suisse', 23, 'Non', 'Suisse', 1),
('Equipe Pays-Bas', 23, 'Non', 'Pays-Bas', 1),
('Equipe Portugal', 23, 'Non', 'Portugal', 1),
('Equipe Luxembourg', 23, 'Non', 'Luxembourg', 1);

INSERT INTO ATHLETES (nom, prenom, sexe, `force`, endurance, agilite, nomPays, idCompetition, idEquipe) VALUES 
('Mbappe', 'Kylian', 'HOMME', 8, 9, 9, 'France', 1, 1), 
('Nadal', 'Rafael', 'HOMME', 7, 9, 8, 'Espagne', 2, NULL),
('James', 'LeBron', 'HOMME', 8, 8, 8, 'Etats-Unis', 3, NULL),
('Farrell', 'Owen', 'HOMME', 7, 8, 7, 'Royaume-Uni', 4, NULL),
('Karabatic', 'Nikola', 'HOMME', 7, 8, 7, 'France', 5, NULL),
('Ngapeth', 'Earvin', 'HOMME', 7, 7, 8, 'France', 6, NULL),
('Long', 'Ma', 'HOMME', 8, 7, 9, 'Chine', 8, NULL),
('Johnson', 'Dustin', 'HOMME', 6, 7, 5, 'Etats-Unis', 9, NULL),
('Phelps', 'Michael', 'HOMME', 7, 9, 8, 'Etats-Unis', 10, NULL),
('Griezmann', 'Antoine', 'HOMME', 7, 8, 9, 'France', 1, 1);

--Select * from PAYS;
--Select * from SPORT;
--Select * from COMPETITIONS;
--Select * from EQUIPES;
--Select * from ATHLETES;


INSERT INTO ROLES VALUES 
(1, "administrateur"),
(2, "organisateur"),
(3, "journaliste");

INSERT INTO UTILISATEURS VALUES (1, "a", "a", "a", "a", 1),
(2, "orga", "orga", "orga", "orga", 2),
(3, "aa", "aa", "aa", "aa", 3);
