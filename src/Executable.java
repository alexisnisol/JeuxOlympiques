import modele.JeuxOlympiques;

public class Executable {

    public static void main(String[] args) {
        JeuxOlympiques jo = new JeuxOlympiques(2024, 10);
        System.out.println(jo);

        System.out.println(JeuxOlympiques.fromCsv("donnees.csv"));
    }
}
