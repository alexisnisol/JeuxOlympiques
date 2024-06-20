package tests;

import org.junit.Before;
import org.junit.Test;

import modele.Pays;
import modele.Sexe;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.sports.VolleyBall;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestPays {
    private Equipe equipe1;
    private Equipe equipe2;
    private Equipe equipe3;
    private Equipe equipe4;
    private Athlete athlete1;
    private Athlete athlete2;
    private Pays france;
    private Pays usa;

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
    }

    @Test
    public void testEquipes() {
        assertEquals(Arrays.asList(equipe1, equipe2, equipe3), france.getEquipes());
        assertEquals(Arrays.asList(equipe4), usa.getEquipes());
    }

    @Test
    public void testAthletes() {
        assertEquals(Arrays.asList(athlete1), france.getAthletes());
        assertEquals(Arrays.asList(athlete2), usa.getAthletes());
    }

    @Test
    public void testObtenirEquipe() {
    }
}
