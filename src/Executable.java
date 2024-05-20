import java.util.List;
import java.util.Map;

import modele.Athletes;
import modele.Competition;
import modele.CompetitionCollective;
import modele.CompetitionIndividuelle;
import modele.Equipes;
import modele.JeuxOlympiques;
import modele.Participant;
import modele.Pays;
import modele.Sexe;
import modele.sports.Athletisme;
import modele.sports.VolleyBall;

public class Executable {

    public static void main(String[] args) {
        JeuxOlympiques jo = new JeuxOlympiques(2024, 10);
        System.out.println(jo);

        Pays france = new Pays("France");
        Pays usa = new Pays("USA");

        Athletes a = new Athletes("Jean", "Dupont", Sexe.HOMME, 10, 10, 10, france);
        Athletes b = new Athletes("John", "Dupont", Sexe.HOMME, 5, 12, 30, france);
        Athletes c = new Athletes("John", "Doe", Sexe.HOMME, 8, 20, 13, usa);
        Athletes d = new Athletes("AA", "BB", Sexe.HOMME, 10, 8, 10, usa);
        
        Equipes e1 = new Equipes("Equipe1", 3, false, france);
        a.rejoindreEquipe(e1);
        b.rejoindreEquipe(e1);
        Equipes e2 = new Equipes("Equipe2", 3, false, france);
        c.rejoindreEquipe(e2);
        d.rejoindreEquipe(e2);

        Competition compet = new CompetitionIndividuelle(3, Sexe.HOMME, new Athletisme(5, 100));
        jo.enregistrerCompetition(compet);
        try {
            compet.enregistrerParticipant(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            compet.enregistrerParticipant(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            compet.enregistrerParticipant(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Participant> resCompet = compet.jouer();
        System.out.println(resCompet);
        
        Competition competCollective = new CompetitionCollective(2, Sexe.HOMME, new VolleyBall(2));
        jo.enregistrerCompetition(competCollective);
        try {
            competCollective.enregistrerParticipant(e1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            competCollective.enregistrerParticipant(e2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resCompet = competCollective.jouer();
        System.out.println(resCompet);

        System.out.println("CLASSEMENT MEDAILLES : " + jo.classementMedailles());

        

        //System.out.println(JeuxOlympiques.fromCsv("donnees.csv"));
    }
}
