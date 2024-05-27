package tests;
import org.junit.Before;
import org.junit.Test;

import modele.Athletes;
import modele.Equipes;
import modele.Pays;
import modele.Sexe;
import modele.sports.Sport;
import modele.sports.VolleyBall;

import static org.junit.Assert.*;

public class TestAthletes {
    private Athletes athlete;
    private Pays pays;
    private Equipes equipe;

    @Before
    public void setUp() {
        pays = new Pays("France");
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe = new Equipes("Équipe de test", sport, 5, false, pays);
        athlete = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, pays);
        athlete.rejoindreEquipe(equipe);
    }

    @Test
    public void testGetNom() {
        assertEquals("Doe", athlete.getNom());
    }

    @Test
    public void testGetPrenom() {
        assertEquals("John", athlete.getPrenom());
    }

    @Test
    public void testGetSexe() {
        assertEquals(Sexe.HOMME, athlete.obtenirSexe());
    }

    @Test
    public void testGetForce() {
        assertEquals(50, athlete.getForce());
    }

    @Test
    public void testGetEndurance() {
        assertEquals(60, athlete.getEndurance());
    }

    @Test
    public void testGetAgilite() {
        assertEquals(70, athlete.getAgilite());
    }

    @Test
    public void testGetPays() {
        assertEquals(pays, athlete.obtenirPays());
    }

    @Test
    public void testGetEquipes() {
        assertEquals(equipe, athlete.obtenirEquipes());
    }

    @Test
    public void testObtenirNom() {
        assertEquals("M. Doe John", athlete.obtenirNom());
    }
}