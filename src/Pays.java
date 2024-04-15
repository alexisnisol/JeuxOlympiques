import java.util.ArrayList;
import java.util.List;

public class Pays {
    private String nom;
    private List<Athletes> listAthlètes;
    private List<Equipes> listEquipes;

    public Pays(String nom){
        this.listAthlètes = new ArrayList<>();
        this.listEquipes = new ArrayList<>();
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

}
