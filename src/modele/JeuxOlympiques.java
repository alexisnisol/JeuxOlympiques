package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import BD.RequetesJDBC;
import modele.sports.Athletisme;
import modele.sports.VolleyBall;
import modele.sports.Sport;
import modele.competitions.Competition;
import modele.competitions.CompetitionCollective;
import modele.competitions.CompetitionIndividuelle;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.participants.Participant;
import modele.sports.Escrime;
import modele.sports.HandBall;
import modele.sports.Natation;
import modele.sports.TypeEscrime;

public class JeuxOlympiques {

    private int annee;
    private int nbEpreuves;
    private List<Competition> lesCompetitions;

    public JeuxOlympiques(int annee, int nbEpreuves) {
        this.annee = annee;
        this.nbEpreuves = nbEpreuves;
        this.lesCompetitions = new ArrayList<>();
        try{
            RequetesJDBC.creerSport();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Cette fonction permet de retourner l'année des jeux olympiques
     * 
     * @return l'année des jeux olympiques
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Cette fonction permet de retourner le nombre d'épreuves des jeux olympiques
     * 
     * @return le nombre d'épreuves des jeux olympiques
     */
    public int getNbEpreuves() {
        return nbEpreuves;
    }

    /**
     * Cette fonction permet de retourner la liste des compétitions des jeux
     * olympiques
     * 
     * @return la liste des compétitions des jeux olympiques
     */
    public List<Competition> getLesCompetitions() {
        return lesCompetitions;
    }

    /**
     * La fonction permet de recuperer le ou les résultats de l'athlète dans toute
     * les compétitions auquelles il a participer
     * 
     * @param Participant : le participant que l'on souhaite connaître le résultat
     * @return on retourne donc ses résultats dans toute ses compétitions
     */
    public Classement recupererResultat(Participant participant) {
        return participant.getClassement();
    }

    /**
     * Cette fonction permet de lancer les jeux olympiques, c'est à dire de jouer
     * chaque compétition
     */
    public void lancerJeuxOlympiques() {
        for (Competition competition : lesCompetitions) {
            competition.jouer();
        }
    }

    /**
     * Cette fonction permet d'enregistrer une competition dans les jeux olympiques
     * 
     * @param competition : competition que l'on souhaite ajouter dans les jeux
     *                    olympiques
     */
    public void enregistrerCompetition(Competition competition) {
        lesCompetitions.add(competition);
    }

    /**
     * Cette fonction permet de retourner le classement des pays par les
     * médailles
     */
    public Map<Pays, Classement> classementMedailles() {
        Map<Pays, Classement> classementParPays = new HashMap<>();
        Classement classementPays;
        for (Competition competition : lesCompetitions) {
            for (Participant participant : competition.getParticipants()) {
                Pays pays = participant.obtenirPays();
                if (!classementParPays.containsKey(pays)) {
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
     * Renvoie la liste des pays des athlètes présents dans une compétition
     * 
     * @return la liste des pays
     */
    public List<Pays> obtenirPays() {
        List<Pays> listePays = new ArrayList<>();
        for (Competition competition : lesCompetitions) {
            for (Participant participant : competition.getParticipants()) {
                Pays pays = participant.obtenirPays();
                if (!listePays.contains(pays)) {
                    listePays.add(pays);
                }
            }
        }
        return listePays;
    }

    public List<Athlete> obtenirAthletes() {
        List<Athlete> listeParticipants = new ArrayList<>();
        for (Competition competition : lesCompetitions) {
            for (Participant participant : competition.getParticipants()) {
                if (participant instanceof Equipe) {
                    listeParticipants.addAll(((Equipe) participant).getListeAthletes());
                } else {
                    listeParticipants.add((Athlete) participant);
                }
            }
        }

        return listeParticipants;
    }


    public List<Equipe> obtenirEquipes(){
        List<Equipe> liste = new ArrayList<>();
        for (Competition competition : lesCompetitions) {
            for (Participant participant : competition.getParticipants()) {
                if (participant instanceof Equipe) {
                    liste.add((Equipe)participant);
                }
            }
        }
        return liste;
    }

    /**
     * Cette fonction permet de retourner le classement des pays par les
     * médailles d'or
     * 
     * @return le classement des pays par les médailles d'or
     */
    public String classementOr() {
        Map<Pays, Classement> classementParPays = classementMedailles();
        String classementOr = "";
        for (Map.Entry<Pays, Classement> entry : classementParPays.entrySet()) {
            Pays pays = entry.getKey();
            Classement classement = entry.getValue();
            classementOr += pays + " : " + classement.getMedaillesOr() + " medailles d'or\n";
        }
        return classementOr.toString();

    }

    /**
     * Cette fonction permet de convertir les valeurs du csv en jeux olympiques
     * 
     * @param annee   : l'année des jeux olympiques
     * @param records : les valeurs du csv en liste de liste de string
     * @return les jeux olympiques
     */
    public static JeuxOlympiques convertFromArrayCsv(int annee, List<List<String>> records) {
        List<Competition> competitions = new ArrayList<>();
        List<Equipe> listeEquipes = new ArrayList<>();
        List<Pays> listePays = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();

        Map<Athlete, Sport> athletesSport = new HashMap<>();

        Athlete athlete;
        Pays paysJoueur;
        Pays tempPays;
        for (List<String> joueur : records) {

            /*
             * On crée un pays pour l'athlète
             * On crée un athlète
             */
            tempPays = new Pays(joueur.get(3));
            if (listePays.contains(tempPays)) {
                int indexPays = listePays.indexOf(tempPays);
                paysJoueur = listePays.get(indexPays);
            } else {
                paysJoueur = tempPays;
                listePays.add(paysJoueur);
            }

            athlete = new Athlete(joueur.get(0), joueur.get(1), Sexe.valueOf(joueur.get(2)),
                    Integer.parseInt(joueur.get(5)), Integer.parseInt(joueur.get(6)), Integer.parseInt(joueur.get(7)),
                    paysJoueur);

            participants.add(athlete);

            String sportStr = joueur.get(4);
            try {
                Sport sport = getSportFromName(sportStr);

                /**
                 * Si le sport est en équipe, on ajoute l'athlète à une équipe
                 */
                if (sport.isEnEquipe()) {
                    Equipe equipe = getEquipeDispo(listePays, sport, athlete); // Une équipe est disponible si l'équipe
                                                                               // n'est pas pleine, si le joueur a le
                                                                               // même pays et si le sport du joueur
                                                                               // est le même que celui de l'équipe
                    // Si aucune équipe n'est disponible, on ajoute une nouvelle équipe, et
                    // l'athlète rejoint l'équipe.
                    if (equipe == null) {
                        equipe = new Equipe(athlete.getNom() + athlete.getPrenom(), sport, sport.getTaille(),
                                paysJoueur);

                        athlete.rejoindreEquipe(equipe);

                        listeEquipes.add(equipe);
                    } else { // Une équipe est disponible, alors l'athlète rejoint l'équipe.
                        athlete.rejoindreEquipe(equipe);
                    }
                }
                athletesSport.put(athlete, sport);
            } catch (NoSuchElementException exception) {
                System.out.println(exception);
            }
        }

        /**
         * On crée les compétitions individuelles en fonction des sports des athlètes
         * dans le jeu de données
         */
        for (Map.Entry<Athlete, Sport> entry : athletesSport.entrySet()) {
            Athlete athlete1 = entry.getKey();
            Sport sport = entry.getValue();
            if (!sport.isEnEquipe()) {
                CompetitionIndividuelle competition = new CompetitionIndividuelle(athlete1.obtenirSexe(), sport);
                genererCompetition(competition, athlete1, competitions);
            }
        }

        /**
         * On crée les compétitions collectives en fonction des équipes générées en
         * amont, à la lecture du csv.
         */
        for (Equipe equipe : listeEquipes) {
            CompetitionCollective competition = new CompetitionCollective(equipe.obtenirSexe(), equipe.getSport());
            genererCompetition(competition, equipe, competitions);
        }

        System.out.println("Il y a " + participants.size() + " participants");

        JeuxOlympiques jeuxOlympiques = new JeuxOlympiques(annee, competitions.size());

        jeuxOlympiques.lesCompetitions = competitions;
        System.out.println("Il y a " + jeuxOlympiques.getNbEpreuves() + " compétitions");

        return jeuxOlympiques;
    }

    /**
     * Cette fonction permet de générer une compétition en fonction d'un participant
     * 
     * @param competition  : la compétition à générer
     * @param participant  : le participant à ajouter à la compétition
     * @param competitions : la liste des compétitions
     */
    private static void genererCompetition(Competition competition, Participant participant,
            List<Competition> competitions) {
        try {
            if (!competitions.contains(competition)) {
                competition.enregistrerParticipant(participant);
                competitions.add(competition);
            } else {
                int index = competitions.indexOf(competition);
                competition = competitions.get(index);
                competition.enregistrerParticipant(participant);
                competitions.set(index, competition);
            }
        } catch (SexeCompetitionException | ParticipantDejaPresentException | ParticipantOccupeException
                | MauvaisParticipantException e) {
            System.out
                    .println("Impossible d'ajouter le participant " + participant + " à la compétition " + competition);
        }
    }

    /**
     * Cette fonction permet de retourner une équipe disponible pour un sport donné,
     * un pays donné et un athlète donné
     * 
     * @param listePays : la liste des pays
     * @param sport     : le sport
     * @param athlete   : l'athlète, membre d'un pays
     * @return une équipe disponible pour un sport donné, un pays donné et un
     *         athlète donné, null si aucune équipe n'est disponible
     */
    public static Equipe getEquipeDispo(List<Pays> listePays, Sport sport, Athlete athlete) {
        Pays pays = athlete.obtenirPays();
        int indexPays = listePays.indexOf(pays);
        pays = listePays.get(indexPays);
        List<Equipe> equipes = pays.getEquipes();
        for (Equipe equipe : equipes) {
            if (equipe.getSport().equals(sport) && !equipe.estPleine()) {
                return equipe;
            }
        }
        return null;
    }

    /**
     * Renvoie le pays à partir d'une chaine de caractère, si aucun pays n'est trouvé, alors il est créé
     * @param pays
     * @return le pays
     */
    public Pays getPaysFromString(String pays){
        for(Competition compet : this.lesCompetitions){
            for(Participant part : compet.getParticipants()){
                if(part.obtenirPays().getNom().equalsIgnoreCase(pays)){
                    return part.obtenirPays();
                }
            }
        }
        return new Pays(pays);
    }

    /**
     * Cette fonction permet de retourner un sport en fonction de son nom
     * 
     * @param sport : le nom du sport
     * @return un sport en fonction de son nom
     */
    public static Sport getSportFromName(String sport) throws NoSuchElementException {
        switch (sport) {
            case "Athletisme 110 haies":
                return new Athletisme(sport, false, 110, -1);
            case "Natation relais libre":
                return new Natation(sport, true, 400, 4);
            case "Natation 100 brasse":
                return new Natation(sport, false, 100, -1);
            case "Escrime fleuret":
                return new Escrime(sport, false, TypeEscrime.FLEURET, -1);
            case "Escrime epee":
                return new Escrime(sport, false, TypeEscrime.EPEE, -1);
            case "Volley-Ball":
                return new VolleyBall(sport, true, 6);
            case "Handball":
                return new HandBall(sport, true, 7);
            case "Athletisme relais 400m":
                return new Athletisme(sport, true, 400, 4);
            default:
                throw new NoSuchElementException("Le sport " + sport + " n'existe pas");
        }
    }

    /**
     * Cette fonction permet de retourner la liste des sports disponibles
     * 
     * @return la liste des sports disponibles
     */
    public static List<String> obtenirListeSportsDisponible() {
        List<String> sports = new ArrayList<>();
        sports.add("Athletisme 110 haies");
        sports.add("Natation relais libre");
        sports.add("Natation 100 brasse");
        sports.add("Escrime fleuret");
        sports.add("Escrime epee");
        sports.add("Volley-Ball");
        sports.add("Handball");
        sports.add("Athletisme relais 400m");
        return sports;
    }

    /**
     * Cette fonction permet de retourner les valeurs du csv en liste de liste de
     * string, exploitable pour la création des instances
     * 
     * @param path : le chemin du fichier csv
     * @return les valeurs du csv en liste de liste de string
     */
    public static List<List<String>> fromCsv(String path) {
        List<List<String>> records = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return records;
    }

    public static void main(String[] args) {
        convertFromArrayCsv(2024, fromCsv("src/big_data.csv"));
    }

    @Override
    public String toString() {
        return "Les jeux olympiques de " + this.annee + " ont " + this.nbEpreuves + " épreuves." + "\n"
                + "Les compétitions sont : " + this.lesCompetitions + "\n";
    }
}