INSERT INTO PAYS VALUES 
(1, 'France'),
(2, 'Italie'),
(3, 'Espagne'),
(4, 'Allemagne'),
(5, 'Royaume-Uni'),
(6, 'Russie'),
(7, 'Chine'),
(8, 'Japon'),
(9, 'Australie'),
(10, 'Canada'),
(11, 'Etats-Unis'),
(12, 'Mexique'),
(13, 'Argentine'),
(14, 'Bresil'),
(15, 'Afrique du Sud'),
(16, 'Egypte'),
(17, 'Maroc'),
(18, 'Tunisie'),
(19, 'Algerie'),
(20, 'Nigeria');

INSERT INTO SPORT VALUES 
(1, 'Natation', 'Individuel', 1),
(2, 'Natation', 'Relais', 2),
(3, 'Athletisme', 'Individuel', 3),
(4, 'Athletisme', 'Relais', 1),
(5, 'Gymnastique', 'Individuel', 4),
(6, 'Gymnastique', 'Equipe', 1),
(7, 'Tennis', 'Individuel', 5),
(8, 'Tennis', 'Equipe', 6),
(9, 'Football', 'Equipe', 2),
(10, 'Basketball', 'Equipe', 1),
(11, 'Volleyball', 'Equipe', 7),
(12, 'Handball', 'Equipe', 2),
(13, 'Rugby', 'Equipe', 1),
(14, 'Cyclisme', 'Individuel', 3),
(15, 'Cyclisme', 'Equipe', 2),
(16, 'Boxe', 'Individuel', 5),
(17, 'Boxe', 'Equipe', 5),
(18, 'Judo', 'Individuel', 4),
(19, 'Judo', 'Equipe', 4),
(20, 'Escalade', 'Individuel', 2);

INSERT INTO COMPETITIONS VALUES 
(1, 'Natation', 8, 'Femme', 'Oui', 1),
(2, 'Natation', 8, 'Homme', 'Oui', 1),
(3, 'Athletisme', 24, 'Femme', 'Oui', 3),
(4, 'Athletisme', 24, 'Homme', 'Oui', 3),
(5, 'Gymnastique', 6, 'Femme', 'Oui', 5),
(6, 'Gymnastique', 3, 'Homme', 'Oui', 5),
(7, 'Tennis', 2, 'Femme', 'Oui', 7),
(8, 'Tennis', 2, 'Homme', 'Oui', 7),
(9, 'Football', 11, 'Femme', 'Non', 9),
(10, 'Football', 11, 'Homme', 'Non', 9),
(11, 'Basketball', 5, 'Femme', 'Non', 10),
(12, 'Basketball', 5, 'Homme', 'Non', 10),
(13, 'Volleyball', 8, 'Femme', 'Non', 11),
(14, 'Volleyball', 8, 'Homme', 'Non', 11),
(15, 'Handball', 10, 'Femme', 'Non', 12),
(16, 'Handball', 10, 'Homme', 'Non', 12),
(17, 'Rugby', 11, 'Femme', 'Non', 13),
(18, 'Rugby', 11, 'Homme', 'Non', 13),
(19, 'Cyclisme', 64, 'Femme', 'Oui', 14),
(20, 'Cyclisme', 64, 'Homme', 'Oui', 14);

INSERT INTO EQUIPES VALUES 
(1, 'Equipe de France', 8, 'Oui', 'Or', 1, 1),
(2, 'Equipe d''Italie', 8, 'Oui', 'Argent', 2, 1),
(3, 'Equipe d''Espagne', 8, 'Oui', 'Bronze', 3, 1),
(4, 'Equipe d''Allemagne', 8, 'Oui', 'Or', 4, 1),
(5, 'Equipe du Royaume-Uni', 8, 'Oui', 'Argent', 5, 1),
(6, 'Equipe de Russie', 8, 'Oui', 'Bronze', 6, 1),
(7, 'Equipe de Chine', 8, 'Oui', 'Or', 7, 1),
(8, 'Equipe du Japon', 8, 'Oui', 'Argent', 8, 1),
(9, 'Equipe d''Australie', 8, 'Oui', 'Bronze', 9, 1),
(10, 'Equipe du Canada', 8, 'Oui', 'Or', 10, 1),
(11, 'Equipe des Etats-Unis', 8, 'Oui', 'Argent', 11, 1),
(12, 'Equipe du Mexique', 8, 'Oui', 'Bronze', 12, 1),
(13, 'Equipe d''Argentine', 8, 'Oui', 'Or', 13, 1),
(14, 'Equipe du Bresil', 8, 'Oui', 'Argent', 14, 1),
(15, 'Equipe d''Afrique du Sud', 8, 'Oui', 'Bronze', 15, 1),
(16, 'Equipe d''Egypte', 8, 'Oui', 'Or', 16, 1),
(17, 'Equipe du Maroc', 8, 'Oui', 'Argent', 17, 1),
(18, 'Equipe de Tunisie', 8, 'Oui', 'Bronze', 18);

INSERT INTO ATHLETES VALUES 
(1, 'Dupont', 'Jean', 'Homme', 10, 10, 10, 'Or', 1, 1, 1),
(2, 'Durand', 'Marie', 'Femme', 10, 10, 10, 'Argent', 1, 1, 1),
(3, 'Martin', 'Pierre', 'Homme', 10, 10, 10, 'Bronze', 1, 1, 1),
(4, 'Bernard', 'Sophie', 'Femme', 10, 10, 10, 'Or', 1, 1, 1),
(5, 'Petit', 'Paul', 'Homme', 10, 10, 10, 'Argent', 1, 1, 1),
(6, 'Lefevre', 'Julie', 'Femme', 10, 10, 10, 'Bronze', 1, 1, 1),
(7, 'Leroy', 'Jacques', 'Homme', 10, 10, 10, 'Or', 1, 1, 1),
(8, 'Leroux', 'Marine', 'Femme', 10, 10, 10, 'Argent', 1, 1, 1),
(9, 'Lefevre', 'Julien', 'Homme', 10, 10, 10, 'Bronze', 1, 1, 1),
(10, 'Dupret', 'Pierre','Homme',10,10,10,'Or',1,1,1);



Select * from PAYS;
Select * from SPORT;
Select * from COMPETITIONS;
Select * from EQUIPES;
Select * from ATHLETES;

SELECT EPREUVE, COUNT(*) AS RESULTATS, EQUIPE, MEDAILLE
FROM ATHLETES
WHERE EPREUVE = 'Natation'
GROUP BY EPREUVE, EQUIPE, MEDAILLE
ORDER BY RESULTATS DESC;

SELECT nom, prenom, totalMedailles FROM ATHLETES WHERE totalMedailles > 0 ORDER BY totalMedailles DESC;

SELECT nom, prenom, COUNT(*) as NombreMedaillesOr FROM ATHLETES WHERE idAthletes in (SELECT idAthletes FROM ATHLETES WHERE medaille = 'Or') GROUP BY nom, prenom ORDER BY NombreMedaillesOr asc;



