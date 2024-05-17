package tests;

import org.junit.Before;
import org.junit.Test;
import modele.Athletes;
import modele.Equipes;
import modele.Pays;
import modele.Sexe;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestPays {
    private Equipes equipe1;
    private Equipes equipe2;
    private Equipes equipe3;
    private Equipes equipe4;
    private Athletes athlete1;
    private Athletes athlete2;
    private Pays france;
    private Pays usa;

    @Before
    public void setUp() {
        france = new Pays("France");
        usa = new Pays("Etats Unis");

        equipe1 = new Equipes("Équipe de test", 5, false, "", 0, france);
        equipe2 = new Equipes("Équipe de test 2", 10, true, "", 0, france);
        equipe3 = new Equipes("Équipe de test 3", 5, false, "", 0, france);
        equipe4 = new Equipes("Équipe de test 4", 10, true, "", 0, usa);
        athlete1 = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, "", 0, france);
        athlete1.rejoindreEquipe(equipe1);
        athlete2 = new Athletes("Test", "Test", Sexe.FEMME, 30, 35, 40, "", 0, usa);
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
