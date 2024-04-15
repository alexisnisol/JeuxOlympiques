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

    public String getNom() {
        return nom;
    }

}
