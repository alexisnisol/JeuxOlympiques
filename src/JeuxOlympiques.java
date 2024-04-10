import java.util.ArrayList;
import java.util.List;

public class JeuxOlympiques {

    private int annee, nbEpreuves;
    private List<Competition> lesCompetitions;

    public JeuxOlympiques(int annee, int nbEpreuves) {
        this.annee = annee;
        this.nbEpreuves = nbEpreuves;
        this.lesCompetitions = new ArrayList<>();
    }
    public int getAnnee() {
        return annee;
    }

    public int getNbEpreuves() {
        return nbEpreuves;
    }

    public List<Competition> getLesCompetitions() {
        return lesCompetitions;
    }
    

    public void demarrerCompetition(){
    /**
     * Cette Fonction permet de demarrer une competition de la liste de compétitions
     * 
     */
    }

    
    public void enregistrerResultat(Participant participant){
        /**
         * Cette fonction permet d'enregistrer le résultat d'un participant dans les jeux olympiques
         * 
         * <p>
         * @param participant : participant d'une competition ou du jeux olympiques
         */
    }
    
    
    public int recupererResultat(Participant participant){
        /**
         * La fonction permet de recuperer le ou les résultats de l'athlète dans toute les compétitions auquelles il a participer
         * @param Participant : le participant que l'on souhaite connaître le résultat 
         * @return on retourne donc ses résultats dans toute ses compétitions
         */
        return 0;

    }

    public void enregistrerCompetition(Competition competition){
        /**
         * Cette fonction permet d'enregistrer une competition dans les jeux olympiques
         * @param competition : competition que l'on souhaite ajouter dans les jeux olympiques 
         */

         lesCompetitions.add(competition);
    }

    public void enregistrerParticipant(Participant participant, Competition competition){
        // throws et throw à faire 
        for (Competition competition1 : lesCompetitions) {
            if (competition == competition1){
                competition.getParticipants().add(participant);
            }
        }
    }

    public List<Participant> classementMedailles(){
        return null;
        
    }

    public List<Participant> classementMedaillesOr(){
        return null;
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JeuxOlympiques)) {
            return false;
        }

        JeuxOlympiques jeux = (JeuxOlympiques) obj;
        
        return jeux.annee == this.annee && jeux.nbEpreuves == this.nbEpreuves && jeux.lesCompetitions == this.lesCompetitions;

    }


    
}