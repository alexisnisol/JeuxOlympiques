import java.util.List;

public class Equipes implements Participant{
    
    private String nomEquipe;
    private int tailleMax;
    private boolean enRelais;
    private String medailles;
    private int totalMedailles;
    private List<Athletes> listeAthletes;
    private Pays pays;
    
    public Equipes(String nomEquipe, int tailleMax, boolean enRelais, String medailles, int totalMedailles, Pays pays) {
        this.nomEquipe = nomEquipe;
        this.tailleMax = tailleMax;
        this.enRelais = enRelais;
        this.medailles = medailles;
        this.totalMedailles = totalMedailles;
        this.pays = pays;
    }

    /**
     * Calcul la force totale de l'équipe : somme des forces de tous les athlètes
     * @return la force totale de l'équipe
     */
    public int getTotalForce(){
        int totalForce = 0;
        for (Athletes ath : this.listeAthletes) {
            totalForce += ath.getForce();
        }
        return totalForce;
    }

    /**
     * Calcul l'agilité totale de l'équipe : somme des agilités de tous les athlètes
     * @return l'agilité totale de l'équipe
     */
    public int getTotalAgilite(){
        int totalAgilite = 0;
        for (Athletes ath : this.listeAthletes) {
            totalAgilite += ath.getAgilite();
        }
        return totalAgilite;
    }

    /**
     * Calcul l'endurance totale de l'équipe : somme des endurances de tous les athlètes
     * @return l'endurance totale de l'équipe
     */
    public int getTotalEndurance(){
        int totalEndurance = 0;
        for (Athletes ath : this.listeAthletes) {
            totalEndurance += ath.getEndurance();
        }
        return totalEndurance;
    }

    @Override
    public void participer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'participer'");
    }

    @Override
    public String obtenirNom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenirNom'");
    }
}
