package BD;

import java.sql.*;

public class RequetesJDBC {
	ConnexionMySQL laConnexion;
	Statement st;
	
    public RequetesJDBC(ConnexionMySQL laConnexion){
		this.laConnexion = laConnexion;
	}


    public boolean connexion(String login, String mdp) throws SQLException{
        PreparedStatement ps = laConnexion.prepareStatement("select * from UTILISATEURS where pseudo = ? and mdp = ?");

        ps.setString(1, login);
        ps.setString(2, mdp);
        ResultSet rs = ps.executeQuery();
        ps.close();
        return rs.next();
    }

	public boolean inscription(String nom, String prenom, String pseudo, String mdp) throws SQLException{

		//On vérifie que le login n'est pas déjà utilisé
		PreparedStatement psVerif = laConnexion.prepareStatement("select * from UTILISATEURS where pseudo = ?");
		psVerif.setString(1, pseudo);
		ResultSet rs = psVerif.executeQuery();
		if(rs.next()){
			return false;
		}
		psVerif.close();

		PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `UTILISATEURS`(nom,prenom,pseudo,mdp) VALUES (?,?,?,?)");
		ps.setString(1, nom);
		ps.setString(2, prenom);
		ps.setString(3, pseudo);
		ps.setString(4, mdp);
		ps.executeUpdate();
		ps.close();
		return true;
	}

/*
	void ajouterDevoir(Devoir d) throws  SQLException{ //idd,titre,numm,annee
		PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `DDEVOIR`(idd,titre,numm,annee) VALUES (?,?,?,?)");
		ps.setInt(1, d.getId());
		ps.setString(2, d.getTitre());
		ps.setInt(3, d.getNumMat());
		ps.setInt(4, d.getAnnee());
		ps.executeUpdate();
		ps.close();
	}
	
	void ajouterNotes(int idDev, String titre, int annee, int numMatiere, ArrayList<Pair<Integer,Integer>> lesNotes) throws SQLException{
		
		//Si des notes ayant pour devoir idDev sont présentent, alors les notes sont supprimées et le devoir aussi
		PreparedStatement psDeleteNote = laConnexion.prepareStatement("delete from DOBTENIR where idd = ?");
		psDeleteNote.setInt(1, idDev);
		psDeleteNote.executeUpdate();
		psDeleteNote.close();
		PreparedStatement psDeleteDevoir = laConnexion.prepareStatement("delete from DDEVOIR where idd = ?");
		psDeleteDevoir.setInt(1, idDev);
		psDeleteDevoir.executeUpdate();
		psDeleteDevoir.close();
		
		//Le devoir n'existe obligatoirement pas, si il est déjà présent, il est supprimé en amont.
		Devoir devoir = new Devoir(idDev, numMatiere, titre, annee);
		ajouterDevoir(devoir);

		int numEtu;
		int noteEtu;
		PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO `DOBTENIR`(nume,idd,note) VALUES (?,?,?)");
		ps.setInt(2, idDev);
		
		for(Pair<Integer,Integer> pair : lesNotes){
			numEtu = pair.getKey();
			noteEtu = pair.getValue();
			ps.setInt(1, numEtu);
			ps.setInt(3, noteEtu);
			ps.executeUpdate();
		}
		ps.close();
	}

	
	String nbNotesParDevoir() throws SQLException{
		String res = "idd;titre;annee;nbNotes\n";
		Statement s = this.laConnexion.createStatement();
		ResultSet r = s.executeQuery("select idd, titre, annee, count(note) as nbNotes from DDEVOIR natural join DOBTENIR group by idd");
		while(r.next()){
			res += r.getInt("idd") + ";" + r.getString("titre") + ";" + r.getInt("annee") + ";" + r.getInt("nbNotes") + "\n";
		}
		s.close();
		return res;
	}

	String bulletinDeNotes() throws SQLException{
		String res = "";
		Statement s = this.laConnexion.createStatement();
		
		ResultSet r = s.executeQuery("select * from DETUDIANT");
		
		while(r.next()){ //CHAQUE ETUDIANT
			res += "Numero " + r.getInt("nume") + " nom " + r.getString("nome") + " prenom " + r.getString("prenome") + "\n";
			int nbNotes = 0;
			PreparedStatement psNotes = laConnexion.prepareStatement("select * from DETUDIANT natural join DOBTENIR natural join DDEVOIR natural join DMATIERE where nume = ? and annee = 2023");
			psNotes.setInt(1, r.getInt("nume"));
			ResultSet rs = psNotes.executeQuery();
			while (rs.next()) {
				nbNotes++;
				res += "  " + rs.getString("nomm") + " " + rs.getInt("note") + "\n";
			}
			res += "nb notes: " + nbNotes + "\n\n";
			psNotes.close();
		}
		s.close();
		return res;
	}
*/


} 