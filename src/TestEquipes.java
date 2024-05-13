import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestEquipes {
    private Equipes equipe;
    private Athletes athlete1;
    private Athletes athlete2;
    private Pays pays;

    @Before
    public void setUp() {
        pays = new Pays("France");
        equipe = new Equipes("Équipe de test", 5, false, "", 0, pays);
        athlete1 = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, "", 0, pays);
        athlete2 = new Athletes("DoeDoe", "JohnJohn", Sexe.HOMME, 50, 60, 70, "", 0, pays);
    }

    @Test
    public void testGetTailleMax() {
        assertEquals(5, equipe.getTailleMax());
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
    public void testGetTotalForce() {
        athlete1.rejoindreEquipe(equipe);
        athlete2.rejoindreEquipe(equipe);
        assertEquals(100, equipe.getTotalForce());
    }

    @Test
    public void testGetTotalAgilite() {
        athlete1.rejoindreEquipe(equipe);
        athlete2.rejoindreEquipe(equipe);
        assertEquals(140, equipe.getTotalAgilite());
    }

    @Test
    public void testGetTotalEndurance() {
        athlete1.rejoindreEquipe(equipe);
        athlete2.rejoindreEquipe(equipe);
        assertEquals(120, equipe.getTotalEndurance());
    }

    @Test
    public void testObtenirNom() {
        assertEquals("Équipe de test", equipe.obtenirNom());
    }
}
