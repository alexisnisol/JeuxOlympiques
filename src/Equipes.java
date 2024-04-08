import java.util.List;

public class Equipes {
    
    private String nomEquipe;
    private int tailleMax;
    private boolean enRelais;
    private String medailles;
    private int totalMedailles;
    private List<Athlètes> listeAthlètes;
    private Pays pays;
    
    public Equipes(String nomEquipe, int tailleMax, boolean enRelais, String medailles, int totalMedailles, Pays pays) {
        this.nomEquipe = nomEquipe;
        this.tailleMax = tailleMax;
        this.enRelais = enRelais;
        this.medailles = medailles;
        this.totalMedailles = totalMedailles;
        this.pays = pays;
    }

    public int getTotalForce(){
        int totalForce = 0;
        for (Athlètes ath : listeAthlètes) {
            totalForce += ath.getForce();
        }
        return totalForce;
    }

    public int getTotalAgilite(){
        int totalAgilite = 0;
        for (Athlètes ath : listeAthlètes) {
            totalAgilite += ath.getAgilite();
        }
        return totalAgilite;
    }

    public int getTotalEndurance(){
        int totalEndurance = 0;
        for (Athlètes ath : listeAthlètes) {
            totalEndurance += ath.getEndurance();
        }
        return totalEndurance;
    }
}
