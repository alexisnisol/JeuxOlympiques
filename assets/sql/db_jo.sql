DROP TABLE IF EXISTS ATHLETES;
DROP TABLE IF EXISTS EQUIPES;
DROP TABLE IF EXISTS COMPETITIONS;
DROP TABLE IF EXISTS SPORT;
DROP TABLE IF EXISTS PAYS;


DROP TABLE IF EXISTS UTILISATEURS;
DROP TABLE IF EXISTS ROLES;

CREATE TABLE ROLES (
  idRole int primary key,
  nomRole varchar(16)
);


CREATE TABLE UTILISATEURS (
  idUtilisateur int primary key AUTO_INCREMENT,
  nom varchar(16),
  prenom varchar(16),
  pseudo varchar(16),
  mdp varchar(64),
  idRole int DEFAULT NULL,
  CONSTRAINT FKRoles FOREIGN KEY (idRole) REFERENCES ROLES(idRole)
);


CREATE TABLE PAYS (
    nomPays varchar(23) primary key
);

CREATE TABLE SPORT (
    nomSport varchar(16) primary key,
    coeffAgilite decimal(6,0) DEFAULT NULL, 
    coeffEndurance decimal(6,0) DEFAULT NULL,
    coeffForce decimal(6,0) DEFAULT NULL);



CREATE TABLE COMPETITIONS (
  idCompetition int primary key AUTO_INCREMENT,
  nom varchar(32) DEFAULT NULL,
  sexeCompetition varchar(5) DEFAULT NULL,
  estIndividuelle varchar(5) DEFAULT NULL,
  nomSport varchar(16),
  CONSTRAINT FKSportC FOREIGN KEY (nomSport) REFERENCES SPORT(nomSport)
);

CREATE TABLE EQUIPES (
    idEquipe int primary key AUTO_INCREMENT,
    nomEquipe varchar(32) NOT NULL,
    tailleMax int DEFAULT NULL,
    nomPays varchar(16) DEFAULT NULL,
    idCompetition int DEFAULT NULL,
    CONSTRAINT FKPaysE FOREIGN KEY (nomPays) REFERENCES PAYS(nomPays),
    CONSTRAINT FKCompetitionE FOREIGN KEY (idCompetition) REFERENCES COMPETITIONS(idCompetition)
);


CREATE TABLE ATHLETES (
  idAthletes int primary key AUTO_INCREMENT,
  nom varchar(16) NOT NULL,
  prenom varchar(16) NOT NULL,
  sexe varchar(5) NOT NULL,
  `force` int DEFAULT NULL,
  endurance int DEFAULT NULL,
  agilite int DEFAULT NULL,
  nomPays varchar(16) DEFAULT NULL,
  idCompetition int DEFAULT NULL,
  idEquipe int DEFAULT NULL,
  CONSTRAINT FKCompetitionA FOREIGN KEY (idCompetition) REFERENCES COMPETITIONS(idCompetition),
  CONSTRAINT FKPaysA FOREIGN KEY (nomPays) REFERENCES PAYS(nomPays),
  CONSTRAINT FKEquipeA FOREIGN KEY (idEquipe) REFERENCES EQUIPES(idEquipe)
);



INSERT INTO ROLES VALUES 
(1, "administrateur"),
(2, "organisateur"),
(3, "journaliste");

INSERT INTO UTILISATEURS VALUES (1, "a", "a", "a", "a", 1),
(2, "orga", "orga", "orga", "orga", 2),
(3, "aa", "aa", "aa", "aa", 3);
