package modele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BD.RequetesJDBC;
import modele.participants.Athlete;
import modele.participants.Equipe;

public class Pays {
    private String nom;
    private List<Athlete> listAthletes;
    private List<Equipe> listEquipes;

    public Pays(String nom){
        this.nom = nom;
        this.listAthletes = new ArrayList<>();
        this.listEquipes = new ArrayList<>();

        saveToBd();
    }

    public void saveToBd(){
        try {
            RequetesJDBC.creerPays(this.nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un athlète à la liste des athlètes du pays
     * @param athlete l'athlète à ajouter
     */
    public void addAthlete(Athlete athlete){
        this.listAthletes.add(athlete);
    }

    /**
     * Ajoute une équipe à la liste des équipes du pays
     * @param equipe l'équipe à ajouter
     */
    public void addEquipe(Equipe equipe){
        this.listEquipes.add(equipe);
    }

    /**
     * Obtient le nom du pays
     * @return le nom du pays
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient la liste des athlètes du pays
     * @return la liste des athlètes
     */
    public List<Athlete> getAthletes(){
        return this.listAthletes;
    }
    
    /**
     * Obtient la liste des équipes du pays
     * @return la liste des équipes
     */
    public List<Equipe> getEquipes(){
        return this.listEquipes;
    }

    @Override
    public boolean equals(Object o) {
        if (o==null){return false;}
        if (o == this) {return true; } 
        if (!(o instanceof Pays)) {return false;}

        Pays pays = (Pays) o;
        return this.nom.equals(pays.nom);
    }

    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }

    @Override
    public String toString() {
        return "Pays : " + this.nom + " avec " + this.listAthletes.size() + " athlètes et " + this.listEquipes.size() + " équipes.";
    }

    public static Pays valueOf(String string) {
        return new Pays(string);
    }
    
}
