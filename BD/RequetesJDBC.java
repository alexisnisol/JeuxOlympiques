package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javafx.util.Pair;
import modele.JeuxOlympiques;
import modele.Pays;
import modele.Sexe;
import modele.competitions.Competition;
import modele.competitions.CompetitionIndividuelle;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.participants.Participant;
import modele.sports.Sport;
import vue.Recherche.Rechercher.TypeRecherche;

public class RequetesJDBC {
	ConnexionMySQL laConnexion;
	Statement st;

	public RequetesJDBC(ConnexionMySQL laConnexion) {
		this.laConnexion = laConnexion;
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

	public RoleConnexion connexion(String login, String mdp) throws SQLException {
		PreparedStatement ps = laConnexion
				.prepareStatement("select * from UTILISATEURS natural join ROLES where pseudo = ? and mdp = ?");

		ps.setString(1, login);
		ps.setString(2, mdp);
		ResultSet rs = ps.executeQuery();
		ps.close();
		if (rs.next()) {
			String role = rs.getString("nomRole");
			return RoleConnexion.getRoleFromString(role);
		}
		return RoleConnexion.INVALIDE;

	}

	public boolean inscription(String nom, String prenom, String pseudo, String mdp) throws SQLException {

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

	public List<Competition> getCompetitions() throws SQLException {
		List<Competition> liste = new ArrayList<>();
		try {
			PreparedStatement ps = laConnexion.prepareStatement("select * from COMPETITIONS");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("estIndividuelle").equals("Oui")) {
					liste.add(new CompetitionIndividuelle((Sexe).getString("sexeCompetition"), rs.getString("sport")));
				} else {
					liste.add(new CompetitionCollective((Sexe).getString("sexeCompetition"), rs.getString("sport")));
				}

			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

	public Pair<TypeRecherche, List<String>> search(String search, JeuxOlympiques modele)
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