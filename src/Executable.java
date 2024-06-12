import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import modele.Athletes;
import modele.Classement;
import modele.Competition;
import modele.JeuxOlympiques;
import modele.Participant;
import modele.Pays;
import modele.Sexe;

public class Executable {

    public static void menuAffichage(String title, String header, List<String> options) {
        int lenMax = title.length();
        if (header.length() > lenMax) {
            lenMax = header.length();
        }
        for (String opt : options) {
            if (opt.length() > lenMax) {
                lenMax = opt.length();
            }
        }
        System.out.println("╭" + "─".repeat(lenMax) + "╮");
        System.out.println("│" + " ".repeat((lenMax - title.length()) / 2) + title + " ".repeat((lenMax - title.length() - 1) / 2 - title.length() % 2) + " │");
        if(!header.isEmpty()){
            System.out.println("├" + "─".repeat(lenMax) + "┤");
            System.out.println("│" + header + " ".repeat(lenMax - header.length()) + "│");
        }
        System.out.println("├" + "─".repeat(lenMax) + "┤");
        for (String opt : options) {
            System.out.println("│" + opt + " ".repeat(lenMax - opt.length()) + "│");
        }
        System.out.println("╰" + "─".repeat(lenMax) + "╯");
    }

    public static void main(String[] args) {
        JeuxOlympiques jo = new JeuxOlympiques(2024, 10);

        /*System.out.println("Bienvenue dans le programme de gestion des Jeux Olympiques !");
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

        resCompet = competCollective.jouer();*/


        // System.out.println(resCompet);
        // System.out.println("CLASSEMENT MEDAILLES : " + jo.classementMedailles());
        // System.out.println(JeuxOlympiques.fromCsv("donnees.csv"));

        // 
        Scanner scanner = new Scanner(System.in);
        int choix;
        String header = "";
        //menuAffichage("Menu Jeux Olympiques", Arrays.asList("1. Rentrer les épreuves, les participants manuellement ou par lecture de fichier", "2. Simuler les épreuves", "3. Obtenir les résultats et le classement pour une épreuve donnée", "4. Obtenir le palmares des médailles par pays après chaque journée", "5. Obtenir un classement par nombre total de médailles et par nombre total de médailles d'Or", "0. Quitter"));
        do {
            menuAffichage("Menu Jeux Olympiques", header, Arrays.asList(
                "1. Rentrer les épreuves, les participants manuellement ou par lecture de fichier", 
                "2. Simuler les épreuves",
                "3. Jouer les épreuves et Obtenir les résultats et le classement pour une épreuve donnée",
                "4. Obtenir le palmares des médailles par pays après chaque journée",
                "5. Obtenir un classement par nombre total de médailles et par nombre total de médailles d'Or",
                "0. Quitter"
                ));
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
                        System.out.println("Veuillez entrer le chemin du fichier : ");
                        String path = scanner.next();
                        List<List<String>> liste = JeuxOlympiques.fromCsv(path);
                        System.out.println("Voici le résumé des participants et des compétitions : ");
                        jo = JeuxOlympiques.convertFromArrayCsv(2024, liste); //print automatiquement le nombre de participants et de compétitions
                        header = jo.getNbEpreuves() + " compétitions";
                    }
                    if (choix2.equals("manuel")) {
                        System.out.println("Entrez l'année des Jeux Olympiques : ");
                        int annee = scanner.nextInt();

                        System.out.println("Entrez le nombre de participants : ");
                        int nbParticipants = scanner.nextInt();
                        scanner.nextLine(); // Consomme la ligne restante

                        List<List<String>> liste = new ArrayList<>();

                        for (int i = 0; i < nbParticipants; i++) {
                            System.out.println("Entrez le nom du participant : ");
                            String nom = scanner.nextLine();

                            System.out.println("Entrez le prénom du participant : ");
                            String prenom = scanner.nextLine();

                            System.out.println(
                                    "Entrez le sexe du participant (HOMME pour masculin, FEMME pour féminin) : ");
                            Sexe sexe = Sexe.valueOf(scanner.next());
                            scanner.nextLine(); // Consomme la ligne restante

                            System.out.println("Entrez le pays du participant : ");
                            String pays = scanner.nextLine();

                            System.out.println(
                                    "Entrez l'épreuve du participant (si l'épreuve est l'escime renseigné fleuret ou epee) : ");
                            String epreuve = scanner.nextLine();

                            System.out.println("Entrez la force du participant : ");
                            int force = scanner.nextInt();

                            System.out.println("Entrez l'endurance du participant : ");
                            int endurance = scanner.nextInt();

                            System.out.println("Entrez l'agilité du participant : ");
                            int agilite = scanner.nextInt();
                            scanner.nextLine(); // Consomme la ligne restante

                            List<String> participant = Arrays.asList(nom, prenom, String.valueOf(sexe), pays, epreuve,
                                    String.valueOf(force), String.valueOf(endurance), String.valueOf(agilite));
                            liste.add(participant);
                            System.out.println(liste);
                            System.out.println(participant);
                        }

                        JeuxOlympiques.convertFromArrayCsv(annee, liste);
                        System.out.println(jo);
                        System.out.println(jo.getLesCompetitions());
                        System.out.println(jo.classementMedailles());
                        break;
                    }
                    break;
                case 2:

                    for (Competition competition : jo.getLesCompetitions()) {
                        if (competition.equals(null)) {

                            System.out.println("Aucune compétition n'a été enregistrée");
                        } else {
                            competition.jouer();
                        }
                        System.out.println(competition);
                        System.out.println(jo.classementMedailles());

                    }
                    break;
                case 3:
                    System.out.println("Choisir une épreuve :");
                    int cptSport = 0;
                    for(String sport : JeuxOlympiques.obtenirListeSportsDisponible()){
                        System.out.println(cptSport+". " + sport);
                        cptSport++;
                    }

                    String nomEpreuve = JeuxOlympiques.obtenirListeSportsDisponible().get(scanner.nextInt());
                    try{
                        JeuxOlympiques.getSportFromName(nomEpreuve);
                    } catch(IllegalArgumentException e){
                        System.out.println("Erreur : " + e.getMessage());
                        break;
                    }
                    int cpt = 0;
                    for (Competition competition : jo.getLesCompetitions()) {
                        if (competition.getSport().getNom().equals(nomEpreuve)) {
                            System.out.println(competition);
                            Participant participant;
                            List<Participant> competitionJoue = competition.jouer();
                            for (int i = 0; i < competitionJoue.size(); i++) {
                                participant = competitionJoue.get(i);
                                System.out.println("Place " + (i + 1) + ": " + (participant instanceof Athletes ? "Athlète " : "Equipe ") + participant.obtenirNom() + " "
                                        + competitionJoue.get(i).getPerformance() + " points" + " "
                                        + competitionJoue.get(i).getClassement());
                            }
                        }
                        cpt++;
                    }
                    if(cpt == 0) {
                        System.out.println("Aucune compétition n'a été enregistrée avec ce nom");
                    }
                    break;
                case 4:
                    // Code pour obtenir le palmares des médailles par pays apres chaque journée...
                    LocalDate dateAujourdhui = LocalDate.now();
                    System.out.println("Voici les résultats d'aujourd'hui, le " + dateAujourdhui + " : ");
                    for(Map.Entry<Pays, Classement> entry : jo.classementMedailles().entrySet()){
                        System.out.println(entry.getKey().getNom() + " : " + entry.getValue());
                    }
                    break;
                case 5:
                    // Code pour Obtenir un classement par nombre total de médailles et par nombre
                    // total de médailles d'Or
                    System.out.println("Voici le classement par nombre total de médailles : ");
                    for(Map.Entry<Pays, Classement> entry : jo.classementMedailles().entrySet()){
                        System.out.println(entry.getKey().getNom() + " : " + entry.getValue());
                    }
                    System.out.println(" ");
                    System.out.println("Voici le classement par nombre total de médailles d'Or pour " + jo.getNbEpreuves() + " compétitions :");
                    System.out.println(jo.classementOr());
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
