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
        athlete1 = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, "", 0, pays, equipe);
        athlete2 = new Athletes("Test", "Test", Sexe.FEMME, 30, 35, 40, "", 0, pays, equipe);
    }

    @Test
    public void testObtenirNom() {
        assertEquals("Équipe de test", equipe.obtenirNom());
    }

    @Test
    public void testObtenirSexe() {
        assertEquals(Sexe.HOMME, equipe.obtenirSexe());
        Equipes equipeFemme = new Equipes("Équipe de test2", 5, false, "", 0, pays);
        equipeFemme.addAthlete(athlete1);
        equipeFemme.addAthlete(athlete2);
        equipeFemme.addAthlete(athlete2);
        assertEquals(Sexe.FEMME, equipeFemme.obtenirSexe());
    }

    @Test
    public void testObtenirPays() {
        assertEquals(pays, equipe.obtenirPays());
        assertEquals(pays, athlete1.obtenirPays());
        Pays usa = new Pays("Etats Unis");
        assertNotEquals(usa, athlete1.obtenirPays());
    }


    @Test
    public void testObtenirEquipe() {
        assertEquals(equipe, equipe.obtenirEquipes());
        assertEquals(equipe, athlete1.obtenirEquipes());
    }
}
