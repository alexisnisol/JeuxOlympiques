import java.util.List;
import java.util.Scanner;
import modele.Athletes;
import modele.Competition;
import modele.CompetitionCollective;
import modele.CompetitionIndividuelle;
import modele.Equipes;
import modele.JeuxOlympiques;
import modele.Participant;
import modele.Pays;
import modele.Sexe;
import modele.sports.Athletisme;
import modele.sports.Escrime;
import modele.sports.HandBall;
import modele.sports.Natation;
import modele.sports.Sport;
import modele.sports.TypeEscrime;
import modele.sports.VolleyBall;

public class Executable {
    public static void main(String[] args) {
        JeuxOlympiques jo = new JeuxOlympiques(2024, 10);
        

        Pays france = new Pays("France");
        Pays usa = new Pays("USA");

        Athletes a = new Athletes("Jean", "Dupont", Sexe.HOMME, 10, 10, 10, france);
        Athletes b = new Athletes("John", "Dupont", Sexe.HOMME, 5, 12, 30, france);
        Athletes c = new Athletes("John", "Doe", Sexe.HOMME, 8, 20, 13, usa);
        Athletes d = new Athletes("AA", "BB", Sexe.HOMME, 10, 8, 10, usa);

        VolleyBall sport = new VolleyBall("VolleyBall", false, 6);
        Equipes e1 = new Equipes("Equipe1", sport, 3, false, france);
        a.rejoindreEquipe(e1);
        b.rejoindreEquipe(e1);
        // System.out.println(e1);
        Equipes e2 = new Equipes("Equipe2", sport, 3, false, france);
        c.rejoindreEquipe(e2);
        d.rejoindreEquipe(e2);

        Competition compet = new CompetitionIndividuelle(Sexe.HOMME, new Athletisme("Athletisme", false, 100, -1));
        jo.enregistrerCompetition(compet);
        // System.out.println(jo);
        try {
            compet.enregistrerParticipant(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            compet.enregistrerParticipant(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            compet.enregistrerParticipant(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Participant> resCompet = compet.jouer();
        // System.out.println(resCompet);

        Competition competCollective = new CompetitionCollective(Sexe.HOMME, sport);
        jo.enregistrerCompetition(competCollective);
        try {
            competCollective.enregistrerParticipant(e1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            competCollective.enregistrerParticipant(e2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resCompet = competCollective.jouer();
        // System.out.println(resCompet);
        // System.out.println("CLASSEMENT MEDAILLES : " + jo.classementMedailles());
        // System.out.println(JeuxOlympiques.fromCsv("donnees.csv"));

        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Rentrer les épreuves, les participants manuellement ou par lecture de fichier");
            System.out.println("2. Simuler les épreuves");
            System.out.println("3. Obtenir les résultats et le classement pour une épreuve donnée");
            System.out.println("4. Obtenir le palmares des médailles par pays après chaque journée");
            System.out.println(
                    "5. Obtenir un classement par nombre total de médailles et par nombre total de médailles d’Or");
            System.out.println("0. Quitter");
            System.out.print("Veuillez entrer votre choix : ");

            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                choix = -1;
                scanner.nextLine();
            }

            switch (choix) {
                case 1:
                    System.out.println(
                            "Voulez vous rentrer les épreuves et les participants manuellement ou par lecture de fichier ? (manuel/fichier) :");
                    String choix2 = scanner.next();
                    if (choix2.equals("fichier")) {
                        System.out.println("Veillez entrer le chemin du fichier : ");
                        String path = scanner.next();
                        List<List<String>> liste = JeuxOlympiques.fromCsv(path);
                        jo = JeuxOlympiques.convertFromArrayCsv(2024, liste);
                        System.out.println(liste);
                    }
                    if (choix2.equals("manuel")) {
                        System.out.println("Veuillez entrer le nombre de compétitions : ");
                        int nbCompet = scanner.nextInt();
                        for (int i = 0; i < nbCompet; i++) {
                            System.out.println("Veuillez entrer le type de compétition (individuelle/collective) : ");
                            String typeCompet = scanner.next();
                            System.out.println("Veuillez entrer le sexe de la compétition (homme/femme) : ");
                            Sexe sexe = Sexe.valueOf(scanner.next().toUpperCase());
                            System.out.println("Veuillez entrer le nom du sport : ");
                            String nomSport = scanner.next();
                            System.out.println(
                                    "Si votre Sport est l'escime veuillez indiquez le type d'épee(fleuret/epee): ");
                            String typeEpee = scanner.next();
                            System.out.println("Veuillez entrer le nombre de participants : ");
                            int nbParticipants = scanner.nextInt();
                            System.out.println(
                                    "Veuillez entrer la distance de la course (si le sport est un sport d'athlétisme sinon mettez 0) :  ");
                            int distance = scanner.nextInt();
                            System.out.println(
                                    "Veuillez entrer le nombre de joueurs par équipe (si le sport est un sport collectif sinon mettez 1) : ");
                            int nbJoueurs = scanner.nextInt();
                            System.out.println("Veuillez entrer le nom du pays : ");
                            String nomPays = scanner.next();
                            Pays pays = new Pays(nomPays);
                            System.out.println("Veuillez entrer le nom du participant : ");
                            String nomParticipant = scanner.next();
                            System.out.println("Veuillez entrer le prénom du participant : ");
                            String prenomParticipant = scanner.next();
                            System.out.println("Veuillez entrer le sexe du participant (homme/femme) : ");
                            Sexe sexeParticipant = Sexe.valueOf(scanner.next().toUpperCase());
                            System.out.println("Veuillez entrer le nombre de médailles d'or du participant : ");
                            int nbOr = scanner.nextInt();
                            System.out.println("Veuillez entrer le nombre de médailles d'argent du participant : ");
                            int nbArgent = scanner.nextInt();
                            System.out.println("Veuillez entrer le nombre de médailles de bronze du participant : ");
                            int nbBronze = scanner.nextInt();
                            Athletes participant = new Athletes(nomParticipant, prenomParticipant, sexeParticipant,
                                    nbOr, nbArgent, nbBronze, pays);
                            if (typeCompet.equals("individuelle") && nomSport.equals("athletisme")) {
                                Competition compet2 = new CompetitionIndividuelle(sexe,
                                        new Athletisme(nomSport, false, distance, -1));
                                jo.enregistrerCompetition(compet);
                                try {
                                    compet2.enregistrerParticipant(participant);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (typeCompet.equals("collective") && nomSport.equals("volleyball")) {
                                VolleyBall sport2 = new VolleyBall(nomSport, false, nbJoueurs);
                                Equipes equipe = new Equipes("Equipe1", sport2, nbJoueurs, false, pays);
                                participant.rejoindreEquipe(equipe);
                                Competition compet3 = new CompetitionCollective(sexe, sport2);
                                jo.enregistrerCompetition(compet3);
                                try {
                                    compet3.enregistrerParticipant(equipe);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (typeCompet.equals("collective") && nomSport.equals("handball")) {
                                HandBall sport3 = new HandBall(nomSport, false, nbJoueurs);
                                Equipes equipe = new Equipes("Equipe2", sport3, nbJoueurs, false, pays);
                                participant.rejoindreEquipe(equipe);
                                Competition compet4 = new CompetitionCollective(sexe, sport3);
                                jo.enregistrerCompetition(compet4);
                                try {
                                    compet4.enregistrerParticipant(equipe);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            Escrime sport4 = null;
                            if (typeCompet.equals("individuelle") && nomSport.equals("escrime")) {
                                if (typeEpee.equals("fleuret")) {
                                    sport4 = new Escrime(nomSport, false, TypeEscrime.FLEURET, nbJoueurs);
                                }
                                if (typeEpee.equals("epee")) {
                                    sport4 = new Escrime(nomSport, false, TypeEscrime.EPEE, nbJoueurs);
                                }
                                Competition compet5 = new CompetitionCollective(sexe, sport4);
                                jo.enregistrerCompetition(compet5);
                                try {
                                    compet5.enregistrerParticipant(participant);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (typeCompet.equals("individuelle") && nomSport.equals("natation")) {
                                Competition compet6 = new CompetitionCollective(sexe,
                                        new Natation(nomSport, false, distance, -1));
                                jo.enregistrerCompetition(compet6);
                                try {
                                    compet6.enregistrerParticipant(participant);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            System.out.println(jo);

                        }
                        break;
                    }
                case 2:
                    // Code pour simuler les épreuves...
                    break;
                case 3:
                    // Code pour obtenir les résultats et le classement...
                    break;
                case 4:
                    // Code pour obtenir le palmares des médailles...
                    break;
                case 5:
                    // Code pour obtenir un classement par nombre total de médailles...
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez réessayer.");
            }
        } while (choix != 0);

        scanner.close();
    }
}
