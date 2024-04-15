import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeuxOlympiques {

    private int annee;
    private int nbEpreuves;
    private List<Competition> lesCompetitions;

    public JeuxOlympiques(int annee, int nbEpreuves) {
        this.annee = annee;
        this.nbEpreuves = nbEpreuves;
        this.lesCompetitions = new ArrayList<>();
    }

    /**
     * Cette fonction permet de retourner l'année des jeux olympiques
     * @return l'année des jeux olympiques
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Cette fonction permet de retourner le nombre d'épreuves des jeux olympiques
     * @return le nombre d'épreuves des jeux olympiques
     */
    public int getNbEpreuves() {
        return nbEpreuves;
    }

    /**
     * Cette fonction permet de retourner la liste des compétitions des jeux olympiques
     * @return la liste des compétitions des jeux olympiques
     */
    public List<Competition> getLesCompetitions() {
        return lesCompetitions;
    }
    
    /**
     * Cette Fonction permet de demarrer une competition de la liste de compétitions
     * 
     */
    public void demarrerCompetition(){

    }

    /**
     * Cette fonction permet d'enregistrer le résultat d'un participant dans les jeux olympiques
     * @param participant : participant d'une competition ou du jeux olympiques
     */
    public void enregistrerResultat(Participant participant){
        
    }
    
    /**
     * La fonction permet de recuperer le ou les résultats de l'athlète dans toute les compétitions auquelles il a participer
     * @param Participant : le participant que l'on souhaite connaître le résultat 
     * @return on retourne donc ses résultats dans toute ses compétitions
     */
    public int recupererResultat(Participant participant){
        return 0;
    }

    /**
     * Cette fonction permet d'enregistrer une competition dans les jeux olympiques
     * @param competition : competition que l'on souhaite ajouter dans les jeux olympiques 
     */
    public void enregistrerCompetition(Competition competition){
        lesCompetitions.add(competition);
    }

    public void enregistrerParticipant(Participant participant, Competition competition){
        //TODO: throws et throw à faire 
        for (Competition competition1 : lesCompetitions) {
            if (competition.equals(competition1)){ //TODO : equals à faire dans Competition
                competition.getParticipants().add(participant);
            }
        }
    }

    /**
     * Cette fonction permet de retourner le classement des participants par les médailles
     */
    public List<Participant> classementMedailles(){
        return null;
    }

    /**
     * Cette fonction permet de retourner le classement des participants ayant des médailles d'or
     */
    public List<Participant> classementMedaillesOr(){
        return null;
    }

    /**
     * Cette fonction permet de retourner les valeurs du csv en instance de JeuxOlympiques, Compétition et Participant
     * @param path : le chemin du fichier csv
     * @return les valeurs du csv en instance de JeuxOlympiques, Compétition, Participant etc.
     */
    public static JeuxOlympiques convertFromArrayCsv(List<String> records){
        List<Competition> competitions = new ArrayList<>();
        List<Equipes> equipes = new ArrayList<>();
        List<Pays> pays = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();

        return null; //TODO: à faire
    }

    public static List<String> fromCsv(String path) {
        List<String> liste = new ArrayList<String>();
        try {
            Scanner rowScanner = new Scanner(path);
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                liste.add(rowScanner.next());
                System.out.println(rowScanner.next());
            }
            rowScanner.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return liste;
    }

    @Override
    public String toString() {
        return "JeuxOlympiques{" +
                "annee=" + this.annee +
                ", nbEpreuves=" + this.nbEpreuves +
                ", lesCompetitions=" + this.lesCompetitions + "}";
    }
}