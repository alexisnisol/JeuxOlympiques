import java.util.ArrayList;
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
        this.listeAthletes = new ArrayList<>();
    }

    /**
     * Retourne la taille maximale de l'équipe.
     * @return la taille maximale de l'équipe
     */
    public int getTailleMax() {
        return tailleMax;
    }

    /**
     * Définie la taille maximale de l'équipe.
     * @param tailleMax la taille maximale de l'équipe
     */
    public void setTailleMax(int tailleMax) {
        this.tailleMax = tailleMax;
    }

    /**
     * Retourne si l'équipe est une équipe relais.
     * @return l'attribut enRelais
     */
    public boolean isEnRelais() {
        return enRelais;
    }

    /**
     * Définie une équipe comme une équipe relais.
     * @param enRelais l'attribut enRelais
     */
    public void setEnRelais(boolean enRelais) {
        this.enRelais = enRelais;
    }

    /**
     * Getter des médailles de l'équipe
     * @return les médailles de l'équipe
     */
    public String getMedailles() {
        return medailles;
    }

    /**
     * Setter des médailles de l'équipe
     * @param medailles les médailles de l'équipe
     */
    public void setMedailles(String medailles) {
        this.medailles = medailles;
    }

    /**
     * Getter du total de médailles de l'équipe
     * @return le total de médailles de l'équipe
     */
    public int getTotalMedailles() {
        return totalMedailles;
    }

    /**
     * Setter du total de médailles de l'équipe
     * @param totalMedailles le total de médailles de l'équipe
     */
    public void setTotalMedailles(int totalMedailles) {
        this.totalMedailles = totalMedailles;
    }

    /**
     * Getter de la liste des athlètes de l'équipe
     * @return la liste des athlètes de l'équipe
     */
    public List<Athletes> getListeAthletes() {
        return listeAthletes;
    }

    /**
     * Ajoute un athlète à la liste des athlètes de l'équipe
     * @param athlete l'athlète à ajouter
     */
    public void addAthlete(Athletes athlete){
        this.listeAthletes.add(athlete);
    }

    /**
     * Getter du pays de l'équipe
     * @return le pays de l'équipe
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Setter du pays de l'équipe
     * @param pays le pays de l'équipe
     */
    public void setPays(Pays pays) {
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

    /**
     * Retourne le nom de l'équipe.
     * @return le nom de l'équipe
     */
    @Override
    public String obtenirNom() {
        return this.nomEquipe;
    }
}
