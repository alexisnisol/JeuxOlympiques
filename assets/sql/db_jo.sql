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
    idPays int primary key,
    nomPays varchar(23) NOT NULL
);

CREATE TABLE SPORT (
    idSport int primary key,
    nomSport varchar(16) NOT NULL,
    typeSport varchar(16) NOT NULL,
    coeffSport decimal(6,0) DEFAULT NULL);


CREATE TABLE COMPETITIONS (
  idCompetition int primary key,
  nom varchar(16) DEFAULT NULL,
  nbParticipants int DEFAULT NULL,
  sexeCompetition varchar(5) DEFAULT NULL,
  estIndividuelle varchar(5) DEFAULT NULL,
  idSport int DEFAULT NULL,
  CONSTRAINT FKSportC FOREIGN KEY (idSport) REFERENCES SPORT(idSport)
);

CREATE TABLE EQUIPES (
    idEquipe int primary key,
    nomEquipe varchar(16) NOT NULL,
    tailleMax int DEFAULT NULL,
    enRelais varchar(5) DEFAULT NULL,
    idPays int DEFAULT NULL,
    idCompetition int DEFAULT NULL,
    CONSTRAINT FKPaysE FOREIGN KEY (idPays) REFERENCES PAYS(idPays),
    CONSTRAINT FKCompetitionE FOREIGN KEY (idCompetition) REFERENCES COMPETITIONS(idCompetition)
);


CREATE TABLE ATHLETES (
  idAthletes int primary key,
  nom varchar(16) NOT NULL,
  prenom varchar(16) NOT NULL,
  sexe varchar(5) NOT NULL,
  `force` int DEFAULT NULL,
  endurance int DEFAULT NULL,
  agilite int DEFAULT NULL,
  idPays int DEFAULT NULL,
  competitionActuelle int DEFAULT NULL,
  idEquipe int DEFAULT NULL,
  CONSTRAINT FKCompetitionA FOREIGN KEY (competitionActuelle) REFERENCES COMPETITIONS(idCompetition),
  CONSTRAINT FKPaysA FOREIGN KEY (idPays) REFERENCES PAYS(idPays),
  CONSTRAINT FKEquipeA FOREIGN KEY (idEquipe) REFERENCES EQUIPES(idEquipe)
);
