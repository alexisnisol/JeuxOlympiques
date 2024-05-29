package tests;

import org.junit.Before;
import org.junit.Test;
import modele.Athletes;
import modele.Equipes;
import modele.Pays;
import modele.Sexe;
import static org.junit.Assert.*;



public class TestAthletes {
    private Athletes athlete;
    private Pays pays;
    private Equipes equipe;

    @Before
    public void setUp() {
        pays = new Pays("France"); // Crée un objet Pays avec le nom "France"
        equipe = new Equipes("Équipe de test", 5, false, pays); // Crée un objet Equipes avec le nom "Équipe de test", 5 membres, pas de capitaine et le pays "France"
        athlete = new Athletes("Doe", "John", Sexe.HOMME, 50, 60, 70, pays); // Crée un objet Athletes avec le nom "Doe", le prénom "John", le sexe "HOMME", la force 50, l'endurance 60, l'agilité 70 et le pays "France"
        athlete.rejoindreEquipe(equipe); // Ajoute l'objet Equipes à l'objet Athletes
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
