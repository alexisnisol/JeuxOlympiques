package vue.journaliste;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import modele.competitions.Competition;
import modele.participants.Participant;
import vue.accueil.Navigation;
import javafx.scene.layout.GridPane;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompetitionsPane extends BorderPane {

    public CompetitionsPane(Navigation navigation, List<Competition> competitions) {
        this.setStyle("-fx-background-color: #FFFFFF;");
        // TOP
        this.setTop(navigation);

        // CENTER
        GridPane grid = new GridPane();
        // grid.setVgap(20);
        grid.setStyle("-fx-background-color: #D9D9D9;");
        // Configuration des colonnes
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(40);
        col1.setHgrow(Priority.ALWAYS);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(15);
        col2.setHgrow(Priority.ALWAYS);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(15);
        col3.setHgrow(Priority.ALWAYS);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(15);
        col4.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(col1, col2, col3, col4);
        grid.setGridLinesVisible(true);

        // Ajout du titre des colonnes
        Label pays = new Label("Compétitions");
        pays.setAlignment(Pos.CENTER);
        pays.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #AEA6A6; -fx-border-color: black; -fx-border-width: 1px;");
        pays.setPrefSize(800, 100);
        Label or = new Label("Premier");
        or.setAlignment(Pos.CENTER);
        or.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #ffe88e; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px;");
        or.setPrefSize(500, 100);
        Label argent = new Label("Deuxième");
        argent.setAlignment(Pos.CENTER);
        argent.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #ced7de; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px;");
        argent.setPrefSize(500, 100);
        Label bronze = new Label("Troisième");
        bronze.setAlignment(Pos.CENTER);
        bronze.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #ddb086; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px;");
        bronze.setPrefSize(500, 100);
        Label total = new Label("Participants");
        total.setAlignment(Pos.CENTER);
        total.setStyle(
                "-fx-font-size: 14; -fx-font-weight: bold; -fx-background-color: #767171; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 1px;");
        total.setPrefSize(500, 100);
        grid.add(pays, 0, 0);
        grid.add(or, 1, 0);
        grid.add(argent, 2, 0);
        grid.add(bronze, 3, 0);
        grid.add(total, 4, 0);

        // Ajout des données
        int rowI = 1;
        List<Participant> placement;
        for (Competition compet : competitions) {
            Label nomCompet = new Label(compet.affichageVue());
            nomCompet.setStyle("-fx-font-size: 12;");

            grid.add(nomCompet, 0, rowI);
            try {

                placement = compet.getPlacement();

                if (placement.size() > 0) {
                    Participant premier = placement.get(0);
                    Label premierLabel = new Label(premier.obtenirNom());
                    premierLabel.setAlignment(Pos.CENTER);
                    premierLabel.setStyle("-fx-font-size: 12;");
                    grid.add(premierLabel, 1, rowI);
                }

                if (placement.size() > 1) {
                    Participant deuxieme = placement.get(1);
                    Label deuxiemeLabel = new Label(deuxieme.obtenirNom());
                    deuxiemeLabel.setAlignment(Pos.CENTER);
                    deuxiemeLabel.setStyle("-fx-font-size: 12;");
                    grid.add(deuxiemeLabel, 2, rowI);
                }
                if (placement.size() > 2) {
                    Participant troisieme = placement.get(2);
                    Label troisiemeLabel = new Label(troisieme.obtenirNom());
                    troisiemeLabel.setAlignment(Pos.CENTER);
                    troisiemeLabel.setStyle("-fx-font-size: 12;");
                    grid.add(troisiemeLabel, 3, rowI);
                }

            } catch (IllegalStateException e) {
                grid.add(new Label("N'est pas jouée"), 1, rowI);
            }

            Label participants = new Label(compet.getParticipants().size() + "");
            GridPane.setHalignment(participants, HPos.CENTER);
            participants.setAlignment(Pos.CENTER);
            grid.add(participants, 4, rowI);

            rowI++;
        }

        TitledPane tp = new TitledPane("Classement des Jeux IUT'Olympiques",
                grid);
        tp.setStyle("-fx-font-size: 18;");
        tp.setPadding(new Insets(20));
        tp.setCollapsible(false);
        tp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(tp, Pos.BOTTOM_CENTER);
        ScrollPane scrollPane = new ScrollPane(
                tp);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
        this.setCenter(scrollPane);
    }

    public static String getUrlFlag(String pays) throws NoSuchFieldException {
        return getUrlFlag(64, 48, pays);
    }

    public static String getUrlFlag(int width, int height, String pays) throws NoSuchFieldException {
        String codePays = "fr";
        try {
            // Lire le contenu du fichier codes.json
            byte[] bytes = Files.readAllBytes(Paths.get("assets/file/codes.json"));
            String content = new String(bytes);
            // Convertir la chaîne de caractères en objet JSON
            JSONObject jsonObject = new JSONObject(content);
            // Extraire le code du pays
            codePays = jsonObject.getString(pays);
        } catch (Exception e) {
            throw new NoSuchFieldException("Pays non trouvé");
        }
        return "https://flagcdn.com/" + width + "x" + height + "/" + codePays + ".png";
    }
}
