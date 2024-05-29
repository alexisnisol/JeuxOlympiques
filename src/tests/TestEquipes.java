package tests;
import org.junit.Before;
import org.junit.Test;

import modele.Athletes;
import modele.Equipes;
import modele.Pays;
import modele.Sexe;
import modele.sports.VolleyBall;

import static org.junit.Assert.*;

public class TestEquipes {
    private Equipes equipe;
    private Athletes athlete1;
    private Athletes athlete1bis;
    private Athletes athlete2;
    private Athletes athlete3;
    private Pays pays;

    @Before
    public void setUp() {
        pays = new Pays("France");
<<<<<<< HEAD
        equipe = new Equipes("Équipe de test", 2, false, pays);
=======
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe = new Equipes("Équipe de test", sport, 6, false, pays);
>>>>>>> 505a9788af72db63b868c487446a8f2699a84abf
        athlete1 = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, pays);
        athlete1bis = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, pays);
        athlete2 = new Athletes("DoeDoe", "JohnJohn", Sexe.HOMME, 50, 60, 70, pays);
        athlete3 = new Athletes("DoeDoeDoe", "JohnJohnJohn", Sexe.HOMME, 50, 60, 70, pays);
    }

    @Test
    public void testGetTailleMax() {
        assertEquals(2, equipe.getTailleMax());
    }

    @Test
    public void testSetTailleMax() {
        equipe.setTailleMax(7);
        assertEquals(7, equipe.getTailleMax());
    }

    @Test
    public void testIsEnRelais() {
        assertFalse(equipe.isEnRelais());
    }

    @Test
    public void testSetEnRelais() {
        equipe.setEnRelais(true);
        assertTrue(equipe.isEnRelais());
    }

    @Test
    public void testRejoindreEquipe() {
        assertTrue(athlete1.rejoindreEquipe(equipe));
        assertFalse(athlete1.rejoindreEquipe(equipe)); // ParticipantDejaPresentException
        assertFalse(athlete1bis.rejoindreEquipe(equipe)); // ParticipantDejaPresentException : même si l'objet est différent, il est considéré comme identique à l'aide de la méthode equals.
        assertTrue(athlete2.rejoindreEquipe(equipe));
        assertFalse(athlete3.rejoindreEquipe(equipe)); // EquipePleineException
    }

    @Test
    public void testGetTotalForce() {
        athlete1.rejoindreEquipe(equipe);
        athlete2.rejoindreEquipe(equipe);
        assertEquals(100, equipe.getForce());
    }

    @Test
    public void testGetTotalAgilite() {
        athlete1.rejoindreEquipe(equipe);
        athlete2.rejoindreEquipe(equipe);
        assertEquals(140, equipe.getAgilite());
    }

    @Test
    public void testGetTotalEndurance() {
        athlete1.rejoindreEquipe(equipe);
        athlete2.rejoindreEquipe(equipe);
        assertEquals(120, equipe.getEndurance());
    }

    @Test
    public void testObtenirNom() {
        assertEquals("Équipe de test", equipe.obtenirNom());
    }
}
