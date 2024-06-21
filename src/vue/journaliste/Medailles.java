package vue.journaliste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import modele.Classement;
import modele.Pays;
import vue.accueil.Navigation;
import javafx.scene.layout.GridPane;

public class Medailles extends BorderPane {

    /**
     * Constructeur de la classe Medailles, qui affiche le classement des médailles des pays
     * @param navigation la barre de navigation
     * @param classementMedailles le classement des médailles des pays, du modèle
     */
    public Medailles(Navigation navigation, Map<Pays, Classement> classementMedailles) {
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
        Label pays = new Label("Pays");
        pays.setAlignment(Pos.CENTER);
        pays.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #AEA6A6; -fx-border-color: black; -fx-border-width: 1px;");
        pays.setPrefSize(800, 100);
        Label or = new Label("Or");
        or.setAlignment(Pos.CENTER);
        or.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #ffe88e; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px;");
        or.setPrefSize(500, 100);
        Label argent = new Label("Argent");
        argent.setAlignment(Pos.CENTER);
        argent.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #ced7de; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px;");
        argent.setPrefSize(500, 100);
        Label bronze = new Label("Bronze");
        bronze.setAlignment(Pos.CENTER);
        bronze.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #ddb086; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px;");
        bronze.setPrefSize(500, 100);
        Label total = new Label("Total");
        total.setAlignment(Pos.CENTER);
        total.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #767171; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 1px;");
        total.setPrefSize(500, 100);
        grid.add(pays, 0, 0);
        grid.add(or, 1, 0);
        grid.add(argent, 2, 0);
        grid.add(bronze, 3, 0);
        grid.add(total, 4, 0);

        // Ajout des données
        int rowI = 1;
        HBox paysBox;
        ImageView paysImage;
        Pays paysData;
        Classement classementData;

        // TRIER LE CLASSEMENT

        List<Map.Entry<Pays, Classement>> list = new ArrayList<>(classementMedailles.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Pays, Classement>>() {
            public int compare(Map.Entry<Pays, Classement> o1, Map.Entry<Pays, Classement> o2) {
                return Integer.compare(o2.getValue().getTotal(), o1.getValue().getTotal());
            }
        });

        //AFFICHER LES DONNEES
        for (Map.Entry<Pays, Classement> data : list) {
            paysData = data.getKey();
            classementData = data.getValue();
            String paysNom = paysData.getNom();

            paysBox = new HBox(10);
            paysBox.setPadding(new Insets(10, 10, 10, 10));
            Label place = new Label(rowI + "");
            place.setAlignment(Pos.CENTER);
            paysBox.getChildren().add(place);

            try {
                String url = CompetitionsPane.getUrlFlag(paysNom);
                paysImage = new ImageView(url);
                paysImage.setFitHeight(28);
                paysImage.setFitWidth(34);
                paysBox.getChildren().add(paysImage);
            } catch (NoSuchFieldException e) {
                System.out.println(e);
            }

            paysBox.getChildren().add(new Label(paysNom));

            GridPane.setValignment(place, VPos.BOTTOM);

            grid.add(paysBox, 0, rowI);
            Label orLabel = new Label(classementData.getMedaillesOr() + "");
            orLabel.setPrefWidth(500);
            orLabel.setAlignment(Pos.CENTER);
            Label argentLabel = new Label(classementData.getMedaillesArgent() + "");
            argentLabel.setPrefWidth(500);
            argentLabel.setAlignment(Pos.CENTER);
            Label bronzeLabel = new Label(classementData.getMedaillesBronze() + "");
            bronzeLabel.setPrefWidth(500);
            bronzeLabel.setAlignment(Pos.CENTER);
            Label totalLabel = new Label(classementData.getTotal() + "");
            totalLabel.setPrefWidth(500);
            totalLabel.setAlignment(Pos.CENTER);
            grid.add(orLabel, 1, rowI);
            grid.add(argentLabel, 2, rowI);
            grid.add(bronzeLabel, 3, rowI);
            grid.add(totalLabel, 4, rowI);
            rowI++;
        }

        TitledPane tp = new TitledPane("Classement des Jeux IUT'Olympiques", grid);
        tp.setStyle("-fx-font-size: 18;");
        tp.setPadding(new Insets(20));
        tp.setCollapsible(false);
        tp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(tp, Pos.BOTTOM_CENTER);
        ScrollPane scrollPane = new ScrollPane(tp);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
        this.setCenter(scrollPane);
    }
}
