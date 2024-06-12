import java.sql.*;
import java.util.ArrayList;

import modele.Athletes;
import modele.Pays;
import modele.Sexe;

public class JoueurBD {
	ConnexionMySQL laConnexion;
	Statement st;
	JoueurBD(ConnexionMySQL laConnexion){
		this.laConnexion=laConnexion;
	}

	public Athletes getAthletes(int idAthletes) throws SQLException {
		ResultSet rs = st.executeQuery("SELECT * FROM Athletes where idAthletes= ?");
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		Sexe sexe = Sexe.valueOf(rs.getString("sexe"));
		int force = rs.getInt("force");
		int endurance = rs.getInt("endurance");
		int agilite = rs.getInt("agilite");
		Pays pays = Pays.valueOf(rs.getString("pays"));
	
		Athletes athlete = new Athletes(nom, prenom, sexe, force, endurance, agilite, pays);
		return athlete;
	
	}
}
