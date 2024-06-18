package BD;

import java.sql.*;

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

	public void search(String search) throws SQLException {
		PreparedStatement ps = laConnexion.prepareStatement("select * from PAYS where nom like ?");
		ps.setString(1, "%" + search + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("nom"));
		}
		ps.close();
	}
}