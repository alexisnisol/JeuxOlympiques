CREATE TABLE ATHLETES (
  idAthletes int primary key,
  nom varchar(16) NOT NULL,
  prenom varchar(16) NOT NULL,
  sexe varchar(5) NOT NULL,
  `force` int DEFAULT NULL,
  endurance int DEFAULT NULL,
  agilite int DEFAULT NULL,
  medaille varchar(10) DEFAULT NULL,
  totalMedailles int DEFAULT NULL,
  idPays int DEFAULT NULL,
  competitionActuelle int DEFAULT NULL,
  idEquipe int DEFAULT NULL,
  CONSTRAINT FKCompetition FOREIGN KEY (competitionActuelle) REFERENCES COMPETITIONS(idCompetition),
  CONSTRAINT FKPays FOREIGN KEY (idPays) REFERENCES PAYS(idPays),
  CONSTRAINT FKEquipe FOREIGN KEY (idEquipe) REFERENCES EQUIPES(idEquipe)
);

CREATE TABLE PAYS (
    idPays int primary key,
    nomPays varchar(23) NOT NULL
);

CREATE TABLE COMPETITIONS (
  idCompetition int primary key,
  nom varchar(16) DEFAULT NULL,
  nbParticipants int DEFAULT NULL,
  sexeCompetition varchar(5) DEFAULT NULL,
  estIndividuelle varchar(5) DEFAULT NULL,
  idSport int DEFAULT NULL,
  CONSTRAINT FKSport FOREIGN KEY (idSport) REFERENCES SPORT(idSport)
);

CREATE TABLE SPORT (
    idSport int primary key,
    nomSport varchar(16) NOT NULL,
    typeSport varchar(16) NOT NULL,
    coeffSport decimal(6,0) DEFAULT NULL,
);

CREATE TABLE EQUIPES (
    idEquipe int primary key,
    nomEquipe varchar(16) NOT NULL,
    tailleMax int DEFAULT NULL,
    enRelais varchar(5) DEFAULT NULL,
    medaille varchar(10) DEFAULT NULL,
    totalMedailles int DEFAULT NULL,
    idPays int DEFAULT NULL,
    idCompetition int DEFAULT NULL,
    CONSTRAINT FKPays FOREIGN KEY (idPays) REFERENCES PAYS(idPays),
    CONSTRAINT FKCompetition FOREIGN KEY (idCompetition) REFERENCES COMPETITIONS(idCompetition)
);