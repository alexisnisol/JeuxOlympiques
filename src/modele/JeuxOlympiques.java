package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import modele.sports.Athletisme;
import modele.sports.VolleyBall;
import modele.sports.Sport;
import modele.exceptions.EquipePleineException;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.sports.Athletisme;
import modele.sports.Escrime;
import modele.sports.HandBall;
import modele.sports.Natation;
import modele.sports.Sport;
import modele.sports.TypeEscrime;
import modele.sports.VolleyBall;

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
        // return participant.getResultat();
        return participant.getClassement();
    }

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
     * Cette fonction permet de retourner le classement des participants par les
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
     * Cette fonction permet de convertir les valeurs du csv en jeux olympiques
     * 
     * @param annee   : l'année des jeux olympiques
     * @param records : les valeurs du csv en liste de liste de string
     * @return les jeux olympiques
     */
    public static JeuxOlympiques convertFromArrayCsv(int annee, List<List<String>> records) {
        List<Competition> competitions = new ArrayList<>();
        List<Equipes> listeEquipes = new ArrayList<>();
        List<Pays> listePays = new ArrayList<>();
        List<Participant> participants = new ArrayList<>();

        Map<Athletes, Sport> athletesSport = new HashMap<>();

        Athletes athlete;
        Pays paysJoueur;
        for (List<String> joueur : records) {

            /*
             * On crée un pays pour l'athlète
             * On crée un athlète
             */
            paysJoueur = new Pays(joueur.get(3));
            athlete = new Athletes(joueur.get(0), joueur.get(1), Sexe.valueOf(joueur.get(2)),
                    Integer.parseInt(joueur.get(5)), Integer.parseInt(joueur.get(6)), Integer.parseInt(joueur.get(7)),
                    paysJoueur);
            if (!listePays.contains(paysJoueur)) {
                listePays.add(paysJoueur);
            } else {
                int indexPays = listePays.indexOf(paysJoueur);
                paysJoueur = listePays.get(indexPays);
                paysJoueur.addAthlete(athlete);
                listePays.set(indexPays, paysJoueur);
            }
            participants.add(athlete);

            String sportStr = joueur.get(4);
            Sport sport = getSportFromName(sportStr);
            if (sport == null) {
                throw new IllegalArgumentException("Le sport " + sportStr + " n'existe pas");
            }

            /**
             * Si le sport est en équipe, on ajoute l'athlète à une équipe
             */
            if (sport.isEnEquipe()) {
                Equipes equipe = getEquipeDispo(listePays, sport, athlete);
                if (equipe == null) {
                    equipe = new Equipes(athlete.getNom() + athlete.getPrenom(), sport, sport.getTaille(), false,
                            paysJoueur);
                    try {
                        equipe.addAthlete(athlete);
                    } catch (EquipePleineException | ParticipantDejaPresentException e) {
                        e.printStackTrace();
                    }
                    listeEquipes.add(equipe);
                } else {
                    try {
                        equipe.addAthlete(athlete);
                    } catch (EquipePleineException e) {
                        System.out.println("L'équipe " + equipe + " est pleine");
                    } catch (ParticipantDejaPresentException e) {
                        System.out.println("L'athlète " + athlete + " est déjà dans l'équipe " + equipe);
                    }
                }
            }

            athletesSport.put(athlete, sport);
        }

        /**
         * On crée les compétitions individuelles en fonction des sports des athlètes
         * dans le jeu de données
         */
        for (Map.Entry<Athletes, Sport> entry : athletesSport.entrySet()) {
            Athletes athlete1 = entry.getKey();
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
        for (Equipes equipe : listeEquipes) {
            CompetitionCollective competition = new CompetitionCollective(equipe.obtenirSexe(), equipe.getSport());
            genererCompetition(competition, equipe, competitions);
        }

        System.out.println("Il y a " + participants.size() + " participants");

        JeuxOlympiques jeuxOlympiques = new JeuxOlympiques(annee, competitions.size());

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
    public static Equipes getEquipeDispo(List<Pays> listePays, Sport sport, Athletes athlete) {
        Pays pays = athlete.obtenirPays();
        int indexPays = listePays.indexOf(pays);
        pays = listePays.get(indexPays);
        List<Equipes> equipes = pays.getEquipes();
        for (Equipes equipe : equipes) {
            if (equipe.getSport().equals(sport) && !equipe.estPleine()) {
                return equipe;
            }
        }
        return null;
    }

    /**
     * Cette fonction permet de retourner un sport en fonction de son nom
     * 
     * @param sport : le nom du sport
     * @return un sport en fonction de son nom
     */
    public static Sport getSportFromName(String sport) {
        switch (sport) {
            case "Athletisme 110 haies":
                return new Athletisme(sport, false, 100, -1);
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
                return null;
        }
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