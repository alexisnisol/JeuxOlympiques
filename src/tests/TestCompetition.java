package tests;

import org.junit.Before;
import org.junit.Test;

import modele.exceptions.MauvaisParticipantException;
import modele.sports.Natation;
import modele.sports.VolleyBall;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.Pays;
import modele.Sexe;
import modele.competitions.Competition;
import modele.competitions.CompetitionCollective;
import modele.competitions.CompetitionIndividuelle;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.exceptions.EquipePleineException;

import static org.junit.Assert.*;

public class TestCompetition {
    private Equipe equipe1;
    private Equipe equipe2;
    private Equipe equipe3;
    private Equipe equipe4;
    private Athlete athlete1;
    private Athlete athlete2;
    private Athlete athlete3;
    private Athlete athlete4;
    private Pays france;
    private Pays usa;
    private Competition competition1;
    private Competition competition2;
    private Athlete athlete5;
    private Athlete athlete6;
    private Athlete athlete7;

    @Before
    public void setUp() {
        france = new Pays("France");
        usa = new Pays("Etats Unis");
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe1 = new Equipe("Équipe de test", Sexe.HOMME, sport, 5, france);
        equipe2 = new Equipe("Équipe de test 2", Sexe.HOMME, sport, 10, france);
        equipe3 = new Equipe("Équipe de test 3", Sexe.HOMME, sport, 5, france);
        equipe4 = new Equipe("Équipe de test 4", Sexe.FEMME, sport, 10, usa);
        athlete1 = new Athlete("Doe", "John", Sexe.HOMME, 50, 60, 70, france);
        athlete1.rejoindreEquipe(equipe1);
        athlete2 = new Athlete("Test", "Test", Sexe.FEMME, 30, 35, 40, usa);
        athlete2.rejoindreEquipe(equipe4);

        athlete3 = new Athlete("DoeDoe", "JohnJohn", Sexe.HOMME, 50, 60, 70, france);
        athlete3.rejoindreEquipe(equipe1);
        athlete4 = new Athlete("DoeDoeDoe", "JohnJohnJohn", Sexe.HOMME, 50, 60, 70, france);
        athlete4.rejoindreEquipe(equipe1);
        athlete5 = new Athlete("DoeDoeDoeDoe", "JohnJohnJohnJohn", Sexe.HOMME, 50, 60, 70, france);
        athlete5.rejoindreEquipe(equipe1);
        athlete6 = new Athlete("DoeDoeDoeDoeDoe", "JohnJohnJohnJohnJohn", Sexe.HOMME, 50, 60, 70, france);
        athlete6.rejoindreEquipe(equipe1);
        athlete7 = new Athlete("DoeDoeDoeDoeDoeDoe", "JohnJohnJohnJohnJohnJohn", Sexe.HOMME, 50, 60, 70, france);

        competition1 = new CompetitionCollective(Sexe.HOMME, sport);
        competition2 = new CompetitionIndividuelle(Sexe.HOMME, new Natation("Natation 100 brasse", false, 100, -1));
    }

    /**
     * Teste la méthode enregistrerParticipant de la classe Competition.
     * Vérifie si les exceptions sont correctement gérées.
     */
    @Test
    public void testEnregistrerParticipant() throws MauvaisParticipantException {
        boolean thrown;
        try {
            this.competition2.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (Exception e) {
            thrown = true;
        }
        assertFalse(thrown);

        try {
            this.competition2.enregistrerParticipant(athlete3);
            thrown = false;
        } catch (Exception e) {
            thrown = true;
        }
        assertFalse(thrown);

        try {
            this.competition2.enregistrerParticipant(athlete2);
            thrown = false;
        } catch (ParticipantOccupeException e) {
        } catch (SexeCompetitionException e) {
            thrown = true;
        } catch (ParticipantDejaPresentException e) {
        }
        assertTrue(thrown);

        try {
            this.competition2.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (ParticipantOccupeException e) {
        } catch (SexeCompetitionException e) {
        } catch (ParticipantDejaPresentException e) {
            thrown = true;
        }
        assertTrue(thrown);

        try {
            this.competition2.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (ParticipantOccupeException e) {
            thrown = true;
        } catch (SexeCompetitionException e) {
        } catch (ParticipantDejaPresentException e) {
        }
        assertTrue(thrown);

        try {
            this.competition2.enregistrerParticipant(athlete1);
            thrown = false;
        } catch (ParticipantOccupeException e) {
            thrown = true;
        } catch (SexeCompetitionException e) {
        } catch (ParticipantDejaPresentException e) {
        }

        assertTrue(thrown);

    }

}
