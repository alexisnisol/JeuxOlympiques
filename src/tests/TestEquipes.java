package tests;
import org.junit.Before;
import org.junit.Test;

import modele.Pays;
import modele.Sexe;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.sports.VolleyBall;

import static org.junit.Assert.*;

public class TestEquipes {
    private Equipe equipe;
    private Athlete athlete1;
    private Athlete athlete1bis;
    private Athlete athlete2;
    private Athlete athlete3;
    private Pays pays;

    @Before
    public void setUp() {
        pays = new Pays("France");
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe = new Equipe("Équipe de test", sport, 2, pays);
        athlete1 = new Athlete("Doe", "John", Sexe.HOMME, 50, 60, 70, pays);
        athlete1bis = new Athlete("Doe", "John", Sexe.HOMME, 50, 60, 70, pays);
        athlete2 = new Athlete("DoeDoe", "JohnJohn", Sexe.HOMME, 50, 60, 70, pays);
        athlete3 = new Athlete("DoeDoeDoe", "JohnJohnJohn", Sexe.HOMME, 50, 60, 70, pays);
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
    public void testRejoindreEquipe() {
        assertTrue(athlete1.rejoindreEquipe(equipe));
        assertFalse(athlete1.rejoindreEquipe(equipe)); // ParticipantDejaPresentException
        assertFalse(athlete1bis.rejoindreEquipe(equipe)); // ParticipantDejaPresentException : même si l'objet est différent, il est considéré comme identique à l'aide de la méthode equals.
        assertTrue(athlete2.rejoindreEquipe(equipe));
        equipe.setTailleMax(2);
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
