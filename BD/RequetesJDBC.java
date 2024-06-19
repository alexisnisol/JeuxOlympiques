package BD;

import static org.junit.Assert.assertThrows;

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
import modele.participants.Athlete;
import modele.JeuxOlympiques;
import modele.participants.Equipe;
import modele.participants.Participant;
import modele.sports.Sport;
import vue.Recherche.Rechercher.TypeRecherche;

public class RequetesJDBC {
	public static ConnexionMySQL laConnexion = null;
	Statement st;


	public RequetesJDBC(){
		
	}

	public enum RoleConnexion {
		JOURNALISTE,
		ADMINISTRATEUR,
		ORGANISATEUR,
		INVALIDE;

		public static RoleConnexion getRoleFromString(String str) {
			for (RoleConnexion val : values()) {
				if (str.equalsIgnoreCase(val.name())) {
					return val;
				}
			}
			return INVALIDE;
		}
	}

	public static RoleConnexion connexion(String login, String mdp) throws SQLException {
		PreparedStatement ps = laConnexion
				.prepareStatement("select * from UTILISATEURS natural join ROLES where pseudo = ? and mdp = ?");

		ps.setString(1, login);
		ps.setString(2, mdp);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String role = rs.getString("nomRole");
			return RoleConnexion.getRoleFromString(role);
		}
		ps.close();
		return RoleConnexion.INVALIDE;

	}

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


	// ATHLETES
	public static void creerAthlete(Athlete athlete) throws SQLException {
		PreparedStatement ps2 = laConnexion.prepareStatement("select * from ATHLETES where nom = ? and prenom = ? and sexe = ? and `force` = ? and endurance = ? and agilite = ? and nomPays = ?");
		ps2.setString(1, athlete.getNom());
		ps2.setString(2, athlete.getPrenom());
		ps2.setString(3, athlete.obtenirSexe().name());
		ps2.setInt(4, athlete.getForce());
		ps2.setInt(5, athlete.getEndurance());
		ps2.setInt(6, athlete.getAgilite());
		ps2.setString(7, athlete.obtenirPays().getNom());
		
		ResultSet rs = ps2.executeQuery();
		if (!rs.next()) {
			
			PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `ATHLETES`(nom,prenom,sexe,`force`,endurance,agilite,nomPays) VALUES (?,?,?,?,?,?,?)");
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

	public static int getAthlete(Athlete athlete) throws SQLException, NoSuchElementException{
		try {
			PreparedStatement ps = laConnexion.prepareStatement("select * from ATHLETES where nom = ? and prenom = ? and sexe = ? and `force` = ? and endurance = ? and agilite = ? and nomPays = ?");
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

	//Appelé par le modèle (rejoindreEquipe)
	public static void setEquipeAthlete(Athlete athlete, Equipe equipe) throws SQLException {
		PreparedStatement ps = laConnexion.prepareStatement("UPDATE `ATHLETES` SET idEquipe = ? WHERE idAthletes = ?");
		ps.setInt(1, getEquipe(equipe));
		ps.setInt(2, getAthlete(athlete));
		ps.executeUpdate();
		ps.close();
	}

	//Appelé par le modèle (setCompetitionActuelle)
	public static void setCompetAthlete(Athlete athlete, Competition competition) throws SQLException{
		PreparedStatement ps = laConnexion.prepareStatement("UPDATE `ATHLETES` SET idCompetition = ? WHERE idAthletes = ?");
		ps.setInt(1, getCompetition(competition));
		ps.setInt(2, getAthlete(athlete));
		ps.executeUpdate();
		ps.close();
	}



	// EQUIPES
	public static void creerEquipe(Equipe equipe) throws SQLException {
		PreparedStatement ps2 = laConnexion.prepareStatement("select * from EQUIPES where nomEquipe = ? and tailleMax = ? and nomPays = ?");
		ps2.setString(1, equipe.getNom());
		ps2.setInt(2, equipe.getTailleMax());
		ps2.setString(3, equipe.obtenirPays().getNom());
		ResultSet rs = ps2.executeQuery();
		if (!rs.next()) {
			PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `EQUIPES`(nomEquipe, tailleMax, nomPays) VALUES (?,?,?)");
			ps.setString(1, equipe.getNom());
			ps.setInt(2, equipe.getTailleMax());
			ps.setString(3, equipe.obtenirPays().getNom());
			ps.executeUpdate();
			ps.close();
		}
	}

	//Appelé par le modèle (setCompetitionActuelle)
	public static void setCompetEquipe(Equipe equipe, Competition competition) throws SQLException{
		PreparedStatement ps = laConnexion.prepareStatement("UPDATE `EQUIPES` SET idCompetition = ? WHERE idEquipe = ?");
		ps.setInt(1, getCompetition(competition));
		ps.setInt(2, getEquipe(equipe));
		ps.executeUpdate();
		ps.close();
	}

	public static int getEquipe(Equipe equipe) throws SQLException, NoSuchElementException{
		try {
			PreparedStatement ps = laConnexion.prepareStatement("select * from EQUIPES where nomEquipe = ? and tailleMax = ? and nomPays = ?");
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
	
	public static void creerSport() throws SQLException{
		Statement ps2 = laConnexion.createStatement();
		ResultSet rs = ps2.executeQuery("select * from SPORT");
		if (!rs.next()) {
			String requetes = "INSERT INTO SPORT (nomSport, typeSport, coeffAgilite, coeffEndurance, coeffForce) VALUES "+
			"('Natation', 6, 9, 7),"+
			"('Athletisme', 7, 8, 6),"+
			"('Escrime', 8, 7, 6),"+
			"('Volley-Ball', 8, 6, 5),"+
			"('Handball', 8, 7, 6);";
			Statement ps = laConnexion.createStatement();
			ps.executeUpdate(requetes);
			ps.close();
		}
		ps2.close();
	}


	//---


	public static void creerCompet(Competition competition) throws SQLException {
		PreparedStatement ps2 = laConnexion.prepareStatement("select * from COMPETITIONS where nom = ? and sexeCompetition = ? and estIndividuelle = ?");
		ps2.setString(1, competition.getSport().getNom());
		ps2.setString(2, competition.getSexe().name());
		ps2.setBoolean(3, (competition instanceof CompetitionIndividuelle));
		ResultSet rs = ps2.executeQuery();
		if (!rs.next()) {
			PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `COMPETITIONS`(nom, nbParticipants, sexeCompetition, estIndividuelle, nomSport) VALUES (?, ?, ?, ?, ?)");

			ps.setString(1, competition.getSport().getNom());
			ps.setInt(2, 0);
			ps.setString(3, competition.getSexe().name());
			ps.setBoolean(4, (competition instanceof CompetitionIndividuelle));
			ps.setString(5, competition.getSport().getTypeSport());
			ps.executeUpdate();
			ps.close();
		}
		ps2.close();
	}
	

	public static int getCompetition(Competition competition) throws SQLException, NoSuchElementException{
		try {
			PreparedStatement ps = laConnexion.prepareStatement("select * from COMPETITIONS where nom = ? and sexeCompetition = ? and estIndividuelle = ?");
			ps.setString(1, competition.getSport().getNom());
			ps.setString(2, competition.getSexe().name());
			ps.setBoolean(3, (competition instanceof CompetitionIndividuelle));
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


	public static Pair<TypeRecherche, List<String>> search(String search, JeuxOlympiques modele)
			throws NoSuchElementException {
		List<String> liste = new ArrayList<>();

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

}