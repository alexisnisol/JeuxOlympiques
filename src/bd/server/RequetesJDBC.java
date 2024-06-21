package bd.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.util.Pair;
import modele.JeuxOlympiques;
import modele.Pays;
import modele.Sexe;
import modele.competitions.Competition;
import modele.competitions.CompetitionCollective;
import modele.competitions.CompetitionIndividuelle;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.participants.Participant;
import vue.journaliste.Rechercher.TypeRecherche;

public class RequetesJDBC {
	public static ConnexionMySQL laConnexion = null;
	Statement st;

	public RequetesJDBC() {

	}

	public enum RoleConnexion {
		ADMINISTRATEUR(1),
		JOURNALISTE(3),
		ORGANISATEUR(2);

		private final int id;

		RoleConnexion(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static RoleConnexion valueOf(int id) {
			for (RoleConnexion role : RoleConnexion.values()) {
				if (role.getId() == id) {
					return role;
				}
			}
			return null;
		}
	}

	/**
	 * Connexion à la base de données
	 * @param login Le login de l'utilisateur
	 * @param mdp Le mot de passe de l'utilisateur
	 * @return L'utilisateur connecté
	 * @throws SQLException
	 * @throws NoSuchElementException
	 */
	public static Utilisateur connexion(String login, String mdp) throws SQLException, NoSuchElementException {
		PreparedStatement ps = laConnexion
				.prepareStatement("select * from UTILISATEURS natural join ROLES where pseudo = ? and mdp = ?");

		ps.setString(1, login);
		ps.setString(2, mdp);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String role = rs.getString("nomRole").toUpperCase();
			return new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("pseudo"),
					rs.getString("mdp"), RoleConnexion.valueOf(role));
		}
		ps.close();

		throw new NoSuchElementException();

	}

	/**
	 * Inscription d'un utilisateur
	 * @param nom le nom
	 * @param prenom le prénom
	 * @param pseudo le pseudo
	 * @param mdp  l
	 * @return
	 * @throws SQLException
	 */
	public static boolean inscription(String nom, String prenom, String pseudo, String mdp) throws SQLException {

		if (nom.isEmpty() ||
				prenom.isEmpty() ||
				pseudo.isEmpty() ||
				mdp.isEmpty()) {
			return false;
		}

		// On vérifie que le login n'est pas déjà utilisé
		PreparedStatement psVerif = laConnexion.prepareStatement("select * from UTILISATEURS where pseudo = ?");
		psVerif.setString(1, pseudo);
		ResultSet rs = psVerif.executeQuery();
		if (rs.next()) {
			return false;
		}
		psVerif.close();

		PreparedStatement ps = laConnexion
				.prepareStatement("INSERT INTO `UTILISATEURS`(nom,prenom,pseudo,mdp, idRole) VALUES (?,?,?,?,?)");
		ps.setString(1, nom);
		ps.setString(2, prenom);
		ps.setString(3, pseudo);
		ps.setString(4, mdp);
		ps.setInt(5, 3); // 1=ADMIN,2=ORGANISATEUR,3=JOURNALISTE
		ps.executeUpdate();
		ps.close();
		return true;
	}

	public static List<Utilisateur> getUtilisateurs() throws SQLException {
		List<Utilisateur> liste = new ArrayList<>();
		PreparedStatement ps = laConnexion.prepareStatement("select * from UTILISATEURS natural join ROLES");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			liste.add(new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("pseudo"),
					rs.getString("mdp"), RoleConnexion.valueOf(rs.getString("nomRole").toUpperCase())));
		}
		ps.close();
		return liste;
	}

	/**
	 * Permet de mettre à jour le rôle d'un utilisateur
	 * @param user L'utilisateur à modifie
	 * @param role Le nouveau rôle
	 * @throws SQLException
	 */
	public static void updateUserRole(Utilisateur user, RoleConnexion role) throws SQLException {
		PreparedStatement ps = laConnexion.prepareStatement("UPDATE `UTILISATEURS` SET idRole = ? WHERE pseudo = ?");
		ps.setInt(1, role.getId());
		ps.setString(2, user.getPseudo());
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * Permet de récupérer le nombre de lignes d'une table
	 * @param table La table
	 * @return Le nombre de lignes
	 * @throws SQLException
	 */
	public static int getNbRows(String table) throws SQLException {
		PreparedStatement ps = laConnexion.prepareStatement("select count(*) from " + table);
		ResultSet rs = ps.executeQuery();
		int nb = rs.next() ? rs.getInt(1) : 0;
		rs.close();
		ps.close();
		return nb;
	}

	// ATHLETES
	
	/**
	 * Permet de créer un athlète
	 * @param athlete L'athlète à créer
	 * @throws SQLException
	 */
	public static void creerAthlete(Athlete athlete) throws SQLException {
		PreparedStatement ps2 = laConnexion.prepareStatement(
				"select * from ATHLETES where nom = ? and prenom = ? and sexe = ? and `force` = ? and endurance = ? and agilite = ? and nomPays = ?");
		ps2.setString(1, athlete.getNom());
		ps2.setString(2, athlete.getPrenom());
		ps2.setString(3, athlete.obtenirSexe().name());
		ps2.setInt(4, athlete.getForce());
		ps2.setInt(5, athlete.getEndurance());
		ps2.setInt(6, athlete.getAgilite());
		ps2.setString(7, athlete.obtenirPays().getNom());

		ResultSet rs = ps2.executeQuery();
		
		// Si l'athlète n'existe pas, on le crée
		if (!rs.next()) {

			PreparedStatement ps = laConnexion.prepareStatement(
					"INSERT INTO `ATHLETES`(nom,prenom,sexe,`force`,endurance,agilite,nomPays) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, athlete.getNom());
			ps.setString(2, athlete.getPrenom());
			ps.setString(3, athlete.obtenirSexe().name());
			ps.setInt(4, athlete.getForce());
			ps.setInt(5, athlete.getEndurance());
			ps.setInt(6, athlete.getAgilite());
			ps.setString(7, athlete.obtenirPays().getNom());
			ps.executeUpdate();
			ps.close();
		}
	}

	/**
	 * Permet de récupérer l'identifiant d'un athlète
	 * @param athlete L'athlète
	 * @return L'identifiant de l'athlète
	 */
	public static int getAthlete(Athlete athlete) throws SQLException, NoSuchElementException {
		try {
			PreparedStatement ps = laConnexion.prepareStatement(
					"select * from ATHLETES where nom = ? and prenom = ? and sexe = ? and `force` = ? and endurance = ? and agilite = ? and nomPays = ?");
			ps.setString(1, athlete.getNom());
			ps.setString(2, athlete.getPrenom());
			ps.setString(3, athlete.obtenirSexe().name());
			ps.setInt(4, athlete.getForce());
			ps.setInt(5, athlete.getEndurance());
			ps.setInt(6, athlete.getAgilite());
			ps.setString(7, athlete.obtenirPays().getNom());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("idAthletes");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new NoSuchElementException();
	}

	/**
	 * Permet de mettre à jour l'équipe d'un athlète
	 * Appelé par le modèle (rejoindreEquipe)
	 * 
	 * @param athlete L'athlète
	 * @param equipe L'équipe
	 * @throws SQLException
	 */
	public static void setEquipeAthlete(Athlete athlete, Equipe equipe) throws SQLException {
		PreparedStatement ps = laConnexion.prepareStatement("UPDATE `ATHLETES` SET idEquipe = ? WHERE idAthletes = ?");
		ps.setInt(1, getEquipe(equipe));
		ps.setInt(2, getAthlete(athlete));
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * Permet de mettre à jour la compétition d'un athlète
	 * Appelé par le modèle (setCompetitionActuelle)
	 * @param athlete L'athlète
	 * @param competition La compétition
	 * @throws SQLException
	 */
	public static void setCompetAthlete(Athlete athlete, Competition competition) throws SQLException {
		PreparedStatement ps = laConnexion
				.prepareStatement("UPDATE `ATHLETES` SET idCompetition = ? WHERE idAthletes = ?");
		ps.setInt(1, getCompetition(competition));
		ps.setInt(2, getAthlete(athlete));
		ps.executeUpdate();
		ps.close();
	}

	// EQUIPES
	/**
	 * Permet de créer une équipe dans la BD
	 * @param equipe L'équipe à créer
	 * @throws SQLException
	 */
	public static void creerEquipe(Equipe equipe) throws SQLException {
		PreparedStatement ps2 = laConnexion
				.prepareStatement("select * from EQUIPES where nomEquipe = ? and tailleMax = ? and nomPays = ?");
		ps2.setString(1, equipe.getNom());
		ps2.setInt(2, equipe.getTailleMax());
		ps2.setString(3, equipe.obtenirPays().getNom());
		ResultSet rs = ps2.executeQuery();
		if (!rs.next()) {
			PreparedStatement ps = laConnexion
					.prepareStatement("INSERT INTO `EQUIPES`(nomEquipe, tailleMax, nomPays) VALUES (?,?,?)");
			ps.setString(1, equipe.getNom());
			ps.setInt(2, equipe.getTailleMax());
			ps.setString(3, equipe.obtenirPays().getNom());
			ps.executeUpdate();
			ps.close();
		}
	}

	// Appelé par le modèle (setCompetitionActuelle)
	/**
	 * Permet de mettre à jour la compétition d'une équipe dans la BD
	 * @param equipe L'équipe
	 * @param competition La compétition
	 * @throws SQLException
	 */
	public static void setCompetEquipe(Equipe equipe, Competition competition) throws SQLException {
		PreparedStatement ps = laConnexion
				.prepareStatement("UPDATE `EQUIPES` SET idCompetition = ? WHERE idEquipe = ?");
		ps.setInt(1, getCompetition(competition));
		ps.setInt(2, getEquipe(equipe));
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * Permet de récupérer l'identifiant d'une équipe
	 * @param equipe L'équipe
	 * @return L'identifiant de l'équipe
	 * @throws SQLException
	 * @throws NoSuchElementException
	 */
	public static int getEquipe(Equipe equipe) throws SQLException, NoSuchElementException {
		try {
			PreparedStatement ps = laConnexion
					.prepareStatement("select * from EQUIPES where nomEquipe = ? and tailleMax = ? and nomPays = ?");
			ps.setString(1, equipe.getNom());
			ps.setInt(2, equipe.getTailleMax());
			ps.setString(3, equipe.obtenirPays().getNom());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("idEquipe");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new NoSuchElementException();
	}

	/**
	 * Permet de créer les sports de base
	 * @throws SQLException
	 */
	public static void creerSport() throws SQLException {
		if (getNbRows("SPORT") != 5) {
			String requetes = "INSERT INTO SPORT (nomSport, coeffAgilite, coeffEndurance, coeffForce) VALUES " +
					"('Natation', 6, 9, 7)," +
					"('Athletisme', 7, 8, 6)," +
					"('Escrime', 8, 7, 6)," +
					"('Volley-Ball', 8, 6, 5)," +
					"('Handball', 8, 7, 6)";
			Statement ps = laConnexion.createStatement();
			ps.executeUpdate(requetes);
			ps.close();
		}
	}

	/**
	 * Permet de créer les compétitions prédéfinies
	 */
	public static boolean creerCompetitions() throws SQLException {
		if (getNbRows("COMPETITIONS") < 16) {

			String requetes = "INSERT INTO COMPETITIONS (nom, sexeCompetition, estIndividuelle, nomSport) VALUES " +
					"('Athletisme 110 haies', 'HOMME', 'Oui', 'Athletisme')," +
					"('Athletisme 110 haies', 'FEMME', 'Oui', 'Athletisme')," +
					"('Natation 100 brasse', 'HOMME', 'Oui', 'Natation')," +
					"('Natation 100 brasse', 'FEMME', 'Oui', 'Natation')," +
					"('Escrime fleuret', 'HOMME', 'Oui', 'Escrime')," +
					"('Escrime epee', 'HOMME', 'Oui', 'Escrime')," +
					"('Escrime epee', 'FEMME', 'Oui', 'Escrime')," +
					"('Escrime fleuret', 'FEMME', 'Oui', 'Escrime')," +
					"('Natation relais libre', 'HOMME', 'Non', 'Natation')," +
					"('Natation relais libre', 'FEMME', 'Non', 'Natation')," +
					"('Athletisme relais 400m', 'HOMME', 'Non', 'Athletisme')," +
					"('Athletisme relais 400m', 'FEMME', 'Non', 'Athletisme')," +
					"('Volley-Ball', 'HOMME', 'Non', 'Volley-Ball')," +
					"('Volley-Ball', 'FEMME', 'Non', 'Volley-Ball')," +
					"('Handball', 'HOMME', 'Non', 'Handball')," +
					"('Handball', 'FEMME', 'Non', 'Handball')";
			Statement ps = laConnexion.createStatement();
			ps.executeUpdate(requetes);
			ps.close();
			System.out.println("Competitions créées");
			return true;
		}
		return false;
	}

	/**
	 * Permet de créer une compétition dans la BD
	 * @param competition La compétition à créer
	 * @throws SQLException
	 */
	public static void creerCompet(Competition competition) throws SQLException {
		PreparedStatement ps2 = laConnexion.prepareStatement(
				"select count(*) from COMPETITIONS where nom = ? and sexeCompetition = ? and estIndividuelle = ?");
		ps2.setString(1, competition.getSport().getNom());
		ps2.setString(2, competition.getSexe().name());
		ps2.setString(3, competition.getSport().isEnEquipe() ? "Non" : "Oui");
		ResultSet rs = ps2.executeQuery();
		if (rs.next() && rs.getInt(1) == 0) {
			System.out.println("Aucune correspondance -> Création de " + competition.getSport().getNom() + " "
					+ competition.getSexe().name() + " "
					+ (competition.getSport().isEnEquipe() ? "Non" : "Oui"));
			PreparedStatement ps = laConnexion.prepareStatement(
					"INSERT INTO `COMPETITIONS`(nom, sexeCompetition, estIndividuelle, nomSport) VALUES (?, ?, ?, ?)");

			ps.setString(1, competition.getSport().getNom());
			ps.setString(2, competition.getSexe().name());
			ps.setString(3, competition.getSport().isEnEquipe() ? "Non" : "Oui");
			ps.setString(4, competition.getSport().getTypeSport());
			ps.executeUpdate();
			ps.close();
		}
		ps2.close();
	}
	
	/**
	 * Permet de récupérer l'identifiant d'une compétition
	 * @param competition La compétition
	 * @return L'identifiant de la compétition
	 * @throws SQLException
	 * @throws NoSuchElementException
	 */
	public static int getCompetition(Competition competition) throws SQLException, NoSuchElementException {
		try {
			PreparedStatement ps = laConnexion.prepareStatement(
					"select * from COMPETITIONS where nom = ? and sexeCompetition = ? and estIndividuelle = ?");
			ps.setString(1, competition.getSport().getNom());
			ps.setString(2, competition.getSexe().name());
			ps.setString(3, competition.getSport().isEnEquipe() ? "Non" : "Oui");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("idCompetition");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		throw new NoSuchElementException();

	}

	/**
	 * Permet de créer un pays dans la BD
	 * @param nom Le nom du pays
	 * @throws SQLException
	 */
	public static void creerPays(String nom) throws SQLException {
		PreparedStatement ps2 = laConnexion.prepareStatement("select * from PAYS where nomPays = ?");
		ps2.setString(1, nom);
		ResultSet rs = ps2.executeQuery();
		if (!rs.next()) {
			PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `PAYS`(nomPays) VALUES (?)");
			ps.setString(1, nom);
			ps.executeUpdate();
			ps.close();
		}
		ps2.close();
	}

	/**
	 * Gère la recherche d'un élément dans la BD, pour un pays ou un athlète
	 * @param search
	 * @param modele
	 * @return
	 * @throws NoSuchElementException
	 */
	public static Pair<TypeRecherche, List<String>> search(String search, JeuxOlympiques modele)
			throws NoSuchElementException {
		List<String> liste = new ArrayList<>();

		/**
		 * Vérifie si la recherche est un pays
		 */
		for (Pays pays : modele.obtenirPays()) {
			if (pays.getNom().equalsIgnoreCase(search)) {
				liste.add(pays.getNom());
				liste.add(pays.getAthletes().size() + "");
				liste.add(pays.getEquipes().size() + "");
				int cptCompet = 0;
				for (Competition competition : modele.getLesCompetitions()) {
					for (Participant participant : competition.getParticipants()) {
						if (participant.obtenirPays().equals(pays)) {
							cptCompet++;
						}
					}
				}
				liste.add(cptCompet + "");
				liste.add(modele.classementMedailles().get(pays).shortString());

				return new Pair<>(TypeRecherche.PAYS, liste);
			}
		}

		/**
		 * Vérifie si la recherche est un athlète
		 */
		String resParticipant;
		boolean joueEnEquipe = false;
		String place;
		for (Athlete participant : modele.obtenirAthletes()) {

			Athlete athlete = (Athlete) participant;
			resParticipant = athlete.getPrenom() + " " + athlete.getNom();

			joueEnEquipe = athlete.obtenirEquipes() != null;

			if (search.equalsIgnoreCase(resParticipant)) {
				liste.add(participant.obtenirNom() + " (" + participant.obtenirPays().getNom() + ")");
				liste.add(
						(!joueEnEquipe ? "Aucune" : participant.obtenirEquipes().getNom()));

				if (joueEnEquipe) {
					Equipe equipe = participant.obtenirEquipes();
					liste.add(equipe.getClassement().shortString());
					liste.add(equipe.getCompetitionActuelle().toString());

					try {
						place = equipe.getCompetitionActuelle()
								.getPlacementParticipant(equipe) + "/"
								+ equipe.getCompetitionActuelle().getParticipants().size();
					} catch (IllegalStateException exception) {
						place = "Non jouée";
					}

					liste.add(equipe.getPerformance() + " (" + place + ")");

				} else {
					liste.add(participant.getClassement().shortString());
					liste.add(participant.getCompetitionActuelle().toString());

					try {
						place = participant.getCompetitionActuelle().getPlacementParticipant(participant) + "/"
								+ participant.getCompetitionActuelle().getParticipants().size();
					} catch (IllegalStateException exception) {
						place = "Non jouée";
					}

					liste.add(participant.getPerformance() + " (" + place + ")");
				}
				return new Pair<>(TypeRecherche.ATHLETE, liste);

			}

		}
		throw new NoSuchElementException("Aucune donnée trouvée");
	}

	/**
	 * Permet de charger les compétitions dans notre modèle depuis une BD
	 * @return La liste des compétitions
	 * @throws SQLException
	 */
	public static List<Competition> loadCompetitions() throws SQLException {
		List<Competition> liste = new ArrayList<>();
		PreparedStatement ps = laConnexion.prepareStatement("select * from COMPETITIONS");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString("estIndividuelle").equals("Oui")) { // CompetitionIndividuelle
				try {
					liste.add(new CompetitionIndividuelle(Sexe.valueOf(rs.getString("sexeCompetition")),
							JeuxOlympiques.getSportFromName(rs.getString("nom"))));
				} catch (NoSuchElementException exception) {
					System.out.println("Le sport est invalide");
				}
			} else { // CompetitionCollective
				try {
					liste.add(new CompetitionCollective(Sexe.valueOf(rs.getString("sexeCompetition")),
							JeuxOlympiques.getSportFromName(rs.getString("nom"))));
				} catch (NoSuchElementException exception) {
					System.out.println("Le sport est invalide");
				}
			}
		}
		ps.close();
		return liste;
	}

	/**
	 * Permet de charger les pays dans notre modèle depuis une BD
	 * @return La liste des pays
	 * @throws SQLException
	 */
	public static List<Pays> getPays() throws SQLException {
		List<Pays> liste = new ArrayList<>();
		PreparedStatement ps = laConnexion.prepareStatement("select * from PAYS");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			liste.add(new Pays(rs.getString("nomPays")));
		}
		ps.close();
		return liste;
	}

	/**
	 * Permet de récupérer un pays dans une liste de pays
	 * @param listePays La liste des pays
	 * @param nomPays 	Le nom du pays
	 * @return Le pays
	 */
	public static Pays getUnPays(List<Pays> listePays, String nomPays) {
		for (Pays pays : listePays) {
			if (pays.getNom().equals(nomPays)) {
				return pays;
			}
		}
		return null;

	}

	/**
	 * Permet de charger les athlètes dans notre modèle depuis une BD
	 * @param listePays La liste des pays
	 * @param listeEquipes la liste des équipes
	 * @param listeCompetitions La liste des compétitions
	 * @return la liste des athlètes
	 * @throws SQLException
	 */
	public static List<Athlete> loadAthletes(List<Pays> listePays, List<Equipe> listeEquipes,
			List<Competition> listeCompetitions) throws SQLException {
		List<Athlete> liste = new ArrayList<>();
		PreparedStatement ps = laConnexion.prepareStatement(
				"select ATHLETES.nom, prenom, sexe, `force`, endurance, agilite, ATHLETES.nomPays, IFNULL(ATHLETES.idCompetition, -1) as idCompetitionAth, IFNULL(ATHLETES.idEquipe, -1) as idEquipeAth, EQUIPES.*, COMPETITIONS.*, COMPETATH.* from ATHLETES left join EQUIPES on ATHLETES.idEquipe = EQUIPES.idEquipe left join COMPETITIONS on EQUIPES.idCompetition = COMPETITIONS.idCompetition left join COMPETITIONS as COMPETATH on ATHLETES.idCompetition = COMPETATH.idCompetition");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Athlete athlete = new Athlete(rs.getString("nom"), rs.getString("prenom"),
					Sexe.valueOf(rs.getString("sexe")),
					rs.getInt("force"), rs.getInt("endurance"), rs.getInt("agilite"),
					getUnPays(listePays, rs.getString("nomPays")));

			if (rs.getInt("idEquipeAth") != -1) { // Athlète a une équipe, donc n'a pas de compétition

				for (Equipe equipe : listeEquipes) {
					if (equipe.getNom().equals(rs.getString("nomEquipe")) &&
							equipe.getTailleMax() == rs.getInt("tailleMax") &&
							equipe.obtenirPays().getNom().equals(rs.getString("EQUIPES.nomPays")) &&
							equipe.getSport().getNom().equals(rs.getString("COMPETITIONS.nom"))) {
						athlete.rejoindreEquipe(equipe);
						break;
					}
				}

			} else { // Athlète n'a pas d'équipe, donc il a une compétition

				for (Competition competition : listeCompetitions) {
					if (competition.getSport().getNom().equals(rs.getString("COMPETATH.nom")) &&
							competition.getSexe().name().equals(rs.getString("COMPETATH.sexeCompetition"))) {
						try {
							competition.enregistrerParticipant(athlete);
						} catch (SexeCompetitionException e) {
							e.printStackTrace();
						} catch (ParticipantDejaPresentException e) {
							e.printStackTrace();
						} catch (ParticipantOccupeException e) {
							e.printStackTrace();
						} catch (MauvaisParticipantException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			liste.add(athlete);
		}
		ps.close();
		return liste;
	}
	/**
	 * Permet de charger les équipes dans notre modèle depuis une BD
	 * @param listepays
	 * @param listeCompetitions
	 * @throws SQLException
	 */
	public static List<Equipe> loadEquipes(List<Pays> listepays, List<Competition> listeCompetitions) throws SQLException {
		List<Equipe> liste = new ArrayList<>();
		PreparedStatement ps = laConnexion.prepareStatement(
				"select * from EQUIPES left join COMPETITIONS on EQUIPES.idCompetition = COMPETITIONS.idCompetition");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Equipe equipe = new Equipe(rs.getString("nomEquipe"), Sexe.valueOf(rs.getString("sexeCompetition")),
					JeuxOlympiques.getSportFromName(rs.getString("nom")),
					rs.getInt("tailleMax"), getUnPays(listepays, rs.getString("nomPays")));

			// rejoindre équipe dans Compétition
			for (Competition competition : listeCompetitions) {
				if (competition.getSport().getNom().equals(rs.getString("nom")) &&
						competition.getSexe().name().equals(rs.getString("sexeCompetition"))) {

					try {
						competition.enregistrerParticipant(equipe);
					} catch (SexeCompetitionException e) {
						e.printStackTrace();
					} catch (ParticipantDejaPresentException e) {
						e.printStackTrace();
					} catch (ParticipantOccupeException e) {
						e.printStackTrace();
					} catch (MauvaisParticipantException e) {
						e.printStackTrace();
					}
					break;
				}
			}

			liste.add(equipe);
		}
		ps.close();
		return liste;
	}

}