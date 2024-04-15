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
        pays = new Pays("France", "FR");
        equipe = new Equipes("Équipe de test", 5, false, "", 0, pays);
        athlete1 = new Athletes("Doe", "John", "M", 50, 60, 70, "", 0, pays, equipe);
        athlete2 = new Athletes("Athlète 2", 30, 35, 40);
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
        equipe.addAthlete(athlete1);
        equipe.addAthlete(athlete2);
        assertEquals(55, equipe.getTotalForce());
    }

    @Test
    public void testGetTotalAgilite() {
        equipe.addAthlete(athlete1);
        equipe.addAthlete(athlete2);
        assertEquals(65, equipe.getTotalAgilite());
    }

    @Test
    public void testGetTotalEndurance() {
        equipe.addAthlete(athlete1);
        equipe.addAthlete(athlete2);
        assertEquals(75, equipe.getTotalEndurance());
    }

    @Test
    public void testObtenirNom() {
        assertEquals("Équipe de test", equipe.obtenirNom());
    }
}
