package modele;
import java.util.ArrayList;
import java.util.List;

public class Pays {
    private String nom;
    private List<Athletes> listAthletes;
    private List<Equipes> listEquipes;

    public Pays(String nom){
        this.nom = nom;
        this.listAthletes = new ArrayList<>();
        this.listEquipes = new ArrayList<>();
    }

    /**
     * Ajoute un athlète à la liste des athlètes du pays
     * @param athlete l'athlète à ajouter
     */
    public void addAthlete(Athletes athlete){
        this.listAthletes.add(athlete);
    }

    /**
     * Ajoute une équipe à la liste des équipes du pays
     * @param equipe l'équipe à ajouter
     */
    public void addEquipe(Equipes equipe){
        this.listEquipes.add(equipe);
    }

    /**
     * Getter du nom du pays
     * @return le nom du pays
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter de la liste des Athletes du Pays
     * @return la liste des Athlètes
     */
    public List<Athletes> getAthletes(){
        return this.listAthletes;
    
    }
    /**
     * Getter de la liste des équipes du Pays
     * @return la liste des Equipes
     */
    public List<Equipes> getEquipes(){
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
        return this.nom + " : " + this.listAthletes.size() + " athletes, " + this.listEquipes.size() + " équipes";
    }
    
}
