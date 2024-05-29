package tests;
import org.junit.Before;
import org.junit.Test;

import modele.Athletes;
import modele.Equipes;
import modele.Pays;
import modele.Sexe;
import modele.sports.VolleyBall;

import static org.junit.Assert.*;

public class TestParticipants {
    private Equipes equipe;
    private Athletes athlete1;
    private Athletes athlete2;
    private Athletes athlete3;
    private Pays pays;

    @Before
    public void setUp() {
        pays = new Pays("France");
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        equipe = new Equipes("Équipe de test", sport, 6, false, pays);
        athlete1 = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70,  pays);
        athlete2 = new Athletes("Test", "Test", Sexe.FEMME, 30, 35, 40, pays);
        athlete3 = new Athletes("Test2", "Test2", Sexe.FEMME, 30, 35, 40, pays);
    }

    @Test
    public void testObtenirNom() {
        assertEquals("Équipe de test", equipe.obtenirNom());
    }

    @Test
    public void testObtenirSexe() {
        assertEquals(Sexe.HOMME, equipe.obtenirSexe());
        VolleyBall sport = new VolleyBall("Volley-Ball", true, 6);
        Equipes equipeFemme = new Equipes("Équipe de test2", sport, 6, false,  pays);

        athlete1.rejoindreEquipe(equipeFemme); // HOMME
        assertEquals(Sexe.HOMME, equipeFemme.obtenirSexe());
        athlete2.rejoindreEquipe(equipeFemme); // FEMME
        assertEquals(Sexe.HOMME, equipeFemme.obtenirSexe()); // Si il y a autant de femme que d'homme, alors le sexe de l'équipe est masculin.
        athlete3.rejoindreEquipe(equipeFemme); // FEMME
        assertEquals(Sexe.FEMME, equipeFemme.obtenirSexe()); // Si il y a plus de femme que d'homme, on récupère le sexe le plus présent dans l'équipe pour définir le sexe de l'équipe.
    }

    /*
     * Permet de tester la méthode obtenirPays de la classe Equipes.
     * On vérifie que le pays de l'équipe est bien celui attendu.
     * On vérifie que le pays de l'athlète est bien celui attendu.
     */
    @Test
    public void testObtenirPays() {
        assertEquals(pays, equipe.obtenirPays());
        assertEquals(pays, athlete1.obtenirPays());
        Pays usa = new Pays("Etats Unis");
        assertNotEquals(usa, athlete1.obtenirPays());
    }


    /*
     * Permet de tester la méthode obtenirEquipes de la classe Athletes.
     * On vérifie que l'équipe de l'athlète est bien celle attendue.
     * On vérifie que l'équipe de l'équipe est bien celle attendue.
     */
    @Test
    public void testObtenirEquipe() {
        assertEquals(equipe, equipe.obtenirEquipes());
        assertEquals(equipe, athlete1.obtenirEquipes());
    }
}
