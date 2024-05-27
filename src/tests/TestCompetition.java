package tests;
import org.junit.Before;
import org.junit.Test;

import modele.Athletes;
import modele.sports.Athletisme;
import modele.Competition;
import modele.CompetitionCollective;
import modele.CompetitionIndividuelle;
import modele.exceptions.MauvaisParticipantException;
import modele.Equipes;
import modele.sports.Natation;
import modele.sports.VolleyBall;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.Pays;
import modele.Sexe;
import modele.exceptions.SexeCompetitionException;

import static org.junit.Assert.*;

public class TestCompetition {
    private Equipes equipe1;
    private Equipes equipe2;
    private Equipes equipe3;
    private Equipes equipe4;
    private Athletes athlete1;
    private Athletes athlete2;
    private Athletes athlete3;
    private Athletes athlete4;
    private Pays france;
    private Pays usa;
    private Competition competition1;
    private Competition competition2;

    @Before
    public void setUp() {
        france = new Pays("France");
        usa = new Pays("Etats Unis");

        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe1 = new Equipes("Équipe de test", sport, 5, false,france);
        equipe2 = new Equipes("Équipe de test 2", sport, 10, true,  france);
        equipe3 = new Equipes("Équipe de test 3", sport, 5, false, france);
        equipe4 = new Equipes("Équipe de test 4", sport, 10, true,  usa);
        athlete1 = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, france);
        athlete1.rejoindreEquipe(equipe1);
        athlete2 = new Athletes("Test", "Test", Sexe.FEMME, 30, 35, 40,  usa);
        athlete2.rejoindreEquipe(equipe4);
        
        athlete3 = new Athletes("DoeDoe", "JohnJohn", Sexe.HOMME, 50, 60, 70,france);
        athlete3.rejoindreEquipe(equipe1);
        athlete4 = new Athletes("DoeDoeDoe", "JohnJohnJohn", Sexe.HOMME, 50, 60, 70, france);
        athlete4.rejoindreEquipe(equipe1);

        competition1 = new CompetitionCollective(Sexe.HOMME, sport);
        competition2 = new CompetitionIndividuelle(Sexe.HOMME, new Natation("Natation 100 brasse", false, 100, -1));
    }


    @Test
    public void testEnregistrerParticipant() {
        boolean thrown;
        try {
            this.competition1.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (Exception e) {
            thrown = true;
        }
        assertFalse(thrown);

        try {
            this.competition1.enregistrerParticipant(athlete3);
            thrown = false;
        } catch (Exception e) {
            System.out.println(e);
            thrown = true;
        }
        assertFalse(thrown);

        try {
            this.competition1.enregistrerParticipant(athlete2);
            thrown = false;
        }catch (ParticipantOccupeException e) {}
        catch (SexeCompetitionException e) {
            thrown = true;
        }
        catch (ParticipantDejaPresentException e) {} 
        catch (MauvaisParticipantException e) {}
        assertTrue(thrown);


        try {
            this.competition1.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (ParticipantOccupeException e) {}
        catch (SexeCompetitionException e) {}
        catch (MauvaisParticipantException e) {}
        catch (ParticipantDejaPresentException e) {
            thrown = true;
        } 
        assertTrue(thrown);
        

        try {
            this.competition2.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (ParticipantOccupeException e) {
            thrown = true;
        }
        catch (SexeCompetitionException e) {}
        catch (MauvaisParticipantException e) {}
        catch (ParticipantDejaPresentException e) {} 

        assertTrue(thrown);


        try {
            this.competition2.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (ParticipantOccupeException e) {
            thrown = true;
        }
        catch (SexeCompetitionException e) {}
        catch (MauvaisParticipantException e) {}
        catch (ParticipantDejaPresentException e) {} 

        assertTrue(thrown);

    }

}
