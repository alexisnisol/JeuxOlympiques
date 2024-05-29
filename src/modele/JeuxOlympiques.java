package modele;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import modele.sports.Athletisme;
import modele.sports.VolleyBall;
import modele.sports.Sport;

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
     * La fonction permet de recuperer le ou les résultats de l'athlète dans toute les compétitions auquelles il a participer
     * @param Participant : le participant que l'on souhaite connaître le résultat 
     * @return on retourne donc ses résultats dans toute ses compétitions
     */
    public Classement recupererResultat(Participant participant){
        //return participant.getResultat();
        return participant.getClassement();
    }

    public void lancerJeuxOlympiques(){
        for(Competition competition : lesCompetitions){
            competition.jouer();
        }
    }

    /**
     * Cette fonction permet d'enregistrer une competition dans les jeux olympiques
     * @param competition : competition que l'on souhaite ajouter dans les jeux olympiques 
     */
    public void enregistrerCompetition(Competition competition){
        lesCompetitions.add(competition);
    }

    /**
     * Cette fonction permet de retourner le classement des participants par les médailles
     */
    public Map<Pays, Classement> classementMedailles(){
        Map<Pays, Classement> classementParPays = new HashMap<>();
        Classement classementPays;
        for (Competition competition : lesCompetitions){
            for (Participant participant : competition.getParticipants()){
                Pays pays = participant.obtenirPays();
                if(!classementParPays.containsKey(pays)){
                    classementPays = new Classement();
                    classementParPays.put(pays, classementPays);
                }

                classementPays = classementParPays.get(pays);
                classementPays.ajouterMedailleOr(participant.getClassement().getMedaillesOr());
                classementPays.ajouterMedailleArgent(participant.getClassement().getMedaillesArgent());
                classementPays.ajouterMedailleBronze(participant.getClassement().getMedaillesBronze());
                classementParPays.put(pays, classementPays);
            }
        }
        return classementParPays;
    }


    /**
     * Cette fonction permet de retourner les valeurs du csv en instance de JeuxOlympiques, Compétition et Participant
     * @param path : le chemin du fichier csv
     * @return les valeurs du csv en instance de JeuxOlympiques, Compétition, Participant etc.
     */
    public static JeuxOlympiques convertFromArrayCsv(String path){
        List<String> liste = fromCsv(path);
        Athletes athlete = new Athletes(liste.get(0), liste.get(1), Sexe.valueOf(liste.get(2)), Integer.parseInt(liste.get(3)), Integer.parseInt(liste.get(4)), Integer.parseInt(liste.get(5)), new Pays(liste.get(6)));
        return null;
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
        return "Les jeux olympiques de " + this.annee + " ont " + this.nbEpreuves + " épreuves." + "\n" + "Les compétitions sont : " + this.lesCompetitions + "\n";
    }
}