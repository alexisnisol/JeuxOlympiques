import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAthletes {
    private Athletes athlete;
    private Pays pays;
    private Equipes equipe;

    @Before
    public void setUp() {
        pays = new Pays("France", "FR");
        equipe = new Equipes("Équipe de test", 5, false, "", 0, pays);
        athlete = new Athletes("Doe", "John", Sexe.MASCULIN, 50, 60, 70, "", 0, pays, equipe);
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
        assertEquals(Sexe.MASCULIN, athlete.getSexe());
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
        assertEquals(pays, athlete.getPays());
    }

    @Test
    public void testGetEquipes() {
        assertEquals(equipe, athlete.getEquipes());
    }

    @Test
    public void testObtenirNom() {
        assertEquals("M Doe John", athlete.obtenirNom());
    }
}