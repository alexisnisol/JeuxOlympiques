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
('Athletisme 110 haies', 0, 'Homme', 'Oui', 'Athletisme'),
('Athletisme 110 haies',  0, 'Femme', 'Oui', 'Athletisme'),
('Natation 100 brasse',  0, 'Homme', 'Oui', 'Natation'),
('Natation 100 brasse',  0, 'Femme', 'Oui', 'Natation'),
('Escrime fleuret',  0, 'Homme', 'Oui', 'Escrime'),
('Escrime epee',  0, 'Homme', 'Oui', 'Escrime'),
('Escrime epee',  0, 'Femme', 'Oui', 'Escrime'),
('Escrime fleuret',  0, 'Femme', 'Oui', 'Escrime'),
('Natation relais libre',  0, 'Homme', 'Non', 'Natation'),
('Natation relais libre',  0, 'Femme', 'Non', 'Natation'),
('Athletisme relais 400m',  0, 'Homme', 'Non', 'Athletisme'),
('Athletisme relais 400m',  0, 'Femme', 'Non', 'Athletisme'),
('Volley-Ball',  0, 'Homme', 'Non', 'Volley-Ball'),
('Volley-Ball',  0, 'Femme', 'Non', 'Volley-Ball'),
('Handball', 0,  'Homme', 'Non', 'Handball'),
('Handball',  0, 'Femme', 'Non', 'Handball');

  nom varchar(32) DEFAULT NULL,
  nbParticipants int DEFAULT NULL,
  sexeCompetition varchar(5) DEFAULT NULL,
  estIndividuelle varchar(5) DEFAULT NULL,
  nomSport varchar(16) DEFAULT NULL,


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
('Mbappe', 'Kylian', 'Homme', 8, 9, 9, 'France', 1, 1), 
('Nadal', 'Rafael', 'Homme', 7, 9, 8, 'Espagne', 2, NULL),
('James', 'LeBron', 'Homme', 8, 8, 8, 'Etats-Unis', 3, NULL),
('Farrell', 'Owen', 'Homme', 7, 8, 7, 'Royaume-Uni', 4, NULL),
('Karabatic', 'Nikola', 'Homme', 7, 8, 7, 'France', 5, NULL),
('Ngapeth', 'Earvin', 'Homme', 7, 7, 8, 'France', 6, NULL),
('Long', 'Ma', 'Homme', 8, 7, 9, 'Chine', 8, NULL),
('Johnson', 'Dustin', 'Homme', 6, 7, 5, 'Etats-Unis', 9, NULL),
('Phelps', 'Michael', 'Homme', 7, 9, 8, 'Etats-Unis', 10, NULL),
('Griezmann', 'Antoine', 'Homme', 7, 8, 9, 'France', 1, 1);

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
