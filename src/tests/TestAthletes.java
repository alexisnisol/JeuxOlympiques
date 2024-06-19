package tests;

import org.junit.Before;
import org.junit.Test;

import modele.Pays;
import modele.Sexe;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.sports.Sport;
import modele.sports.VolleyBall;

import static org.junit.Assert.*;



public class TestAthletes {
    private Athlete athlete;
    private Pays pays;
    private Equipe equipe;

    @Before
    public void setUp() {
        pays = new Pays("France");
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe = new Equipe("Équipe de test", sport, 5, pays);
        athlete = new Athlete("Doe", "John", Sexe.HOMME, 50, 60, 70, pays);
        athlete.rejoindreEquipe(equipe);
    }

    @Test
    public void testGetNom() {
        assertEquals("Doe", athlete.getNom()); // Vérifie si la méthode getNom() retourne "Doe"
    }

    @Test
    public void testGetPrenom() {
        assertEquals("John", athlete.getPrenom()); // Vérifie si la méthode getPrenom() retourne "John"
    }

    @Test
    public void testGetSexe() {
        assertEquals(Sexe.HOMME, athlete.obtenirSexe()); // Vérifie si la méthode obtenirSexe() retourne Sexe.HOMME
    }

    @Test
    public void testGetForce() {
        assertEquals(50, athlete.getForce()); // Vérifie si la méthode getForce() retourne 50
    }

    @Test
    public void testGetEndurance() {
        assertEquals(60, athlete.getEndurance()); // Vérifie si la méthode getEndurance() retourne 60
    }

    @Test
    public void testGetAgilite() {
        assertEquals(70, athlete.getAgilite()); // Vérifie si la méthode getAgilite() retourne 70
    }

    @Test
    public void testGetPays() {
        assertEquals(pays, athlete.obtenirPays()); // Vérifie si la méthode obtenirPays() retourne l'objet Pays créé précédemment
    }

    @Test
    public void testGetEquipes() {
        assertEquals(equipe, athlete.obtenirEquipes()); // Vérifie si la méthode obtenirEquipes() retourne l'objet Equipes créé précédemment
    }

    @Test
    public void testObtenirNom() {
        assertEquals("M. Doe John", athlete.obtenirNom()); // Vérifie si la méthode obtenirNom() retourne "M. Doe John"
    }
}
