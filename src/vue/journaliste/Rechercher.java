package vue.journaliste;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.util.List;
import controller.recherche.ControleurKeyEventRecherche;
import controller.recherche.ControleurActionRecherche;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import vue.accueil.Main;
import vue.accueil.Navigation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Rechercher extends BorderPane {

    private BorderPane center;
    private HBox hbox;
    private HBox footer;
    private Text text;
    private Text text2;
    private Text text3;
    private Text text4;
    private TextField recherche;

    public Rechercher(Navigation navigation, Main main) {

        this.setOnKeyPressed(new ControleurKeyEventRecherche(this, main));

        this.setStyle("-fx-background-color: #FFFFFF;");
        // haut de la fenêtre

        this.setTop(navigation);

        // centre de la fenêtre

        this.center = new BorderPane();


        VBox infoGenerales = new VBox(20);
        infoGenerales.setPadding(new Insets(20, 20, 20, 20));
        infoGenerales.setAlignment(Pos.CENTER);
        Text nbCompet = new Text("Nombre de compétitions : " + main.getModele().getLesCompetitions().size());
        nbCompet.setStyle("-fx-font-size: 20 px;");
        Text nbPart = new Text("Nombre de participants : " + main.getModele().obtenirAthletes().size());
        nbPart.setStyle("-fx-font-size: 20 px;");
        Text nbPays = new Text("Nombre de pays : " + main.getModele().obtenirPays().size());
        nbPays.setStyle("-fx-font-size: 20 px;");
        infoGenerales.getChildren().addAll(nbCompet, nbPart, nbPays);
        this.center.setTop(infoGenerales);

        this.hbox = new HBox();
        ImageView loupe = new ImageView(new Image("file:assets/img/loupe_recherche.png"));
        Button rechercher = new Button();
        rechercher.setOnAction(new ControleurActionRecherche(this, main));
        rechercher.setGraphic(loupe);
        rechercher.setId("nav-loupe");
        loupe.setPreserveRatio(true);
        loupe.setFitHeight(25);
        loupe.setFitWidth(25);
        this.recherche = new TextField();
        this.recherche.setPromptText("Rechercher par Athtlètes (Prénom Nom), Pays");
        this.recherche.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(this.recherche, Priority.ALWAYS);
        this.recherche.setStyle("-fx-font-size: 12.5 px; -fx-text-weight: bold;");
        hbox.getChildren().addAll(this.recherche, rechercher);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(0, 20, 0, 20));
        center.setCenter(hbox);

        
        this.setCenter(center);

        // bas de la fenêtre
        ImageView iut = new ImageView(new Image("file:assets/img/iut.png"));
        iut.setFitHeight(100);
        iut.setFitWidth(200);
        this.footer = new HBox(20);
        HBox.setHgrow(footer, Priority.ALWAYS);
        HBox iutBox = new HBox();
        iutBox.getChildren().add(iut);
        HBox textiut = new HBox();
        TextFlow text = new TextFlow();
        text.setTextAlignment(TextAlignment.RIGHT);
        Text text2 = new Text("Projet réalisé par les étudiants de l'IUT d'Orléans" + "\n" + "\n" + "Alexis Nisol"
                + "\n" + "\n" + "Enzo Familiar-Marais");
        text2.setStyle(" -fx-font-size: 12.5 px; -fx-text-weight: bold;");
        text.getChildren().add(text2);
        textiut.getChildren().add(text);
        textiut.setAlignment(Pos.CENTER_RIGHT);
        footer.getChildren().addAll(iutBox, textiut);
        HBox.setMargin(iutBox, new Insets(20, 20, 0, 20));
        HBox.setMargin(textiut, new Insets(20, 20, 0, 20));
        HBox.setHgrow(textiut, Priority.ALWAYS);
        footer.setPrefSize(900, 100);
        footer.setStyle("-fx-background-color: lightgray;");
        center.setBottom(footer);
    }

    public String getRecherche() {
        return this.recherche.getText();
    }

    public void majParAthlete(List<String> listeData) {
        this.center.setCenter(null);
        this.center.setTop(hbox);
        hbox.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(25);
        this.text = new Text("Nom, Prénom de l'athlète : " + listeData.get(0));
        this.text2 = new Text("Équipe : " + listeData.get(1));
        this.text3 = new Text("Nombre de médailles : " + listeData.get(2));
        this.text4 = new Text("Participant de " + listeData.get(3));
        this.text4.setStyle("-fx-font-size: 14 px;");
        Text text5 = new Text("Performance à la dernière compétition : " + listeData.get(4));
        vbox.getChildren().addAll(text, text2, text3, text4, text5);
        vbox.setStyle("-fx-font-size: 20 px; -fx-text-weight: bold;");
        VBox vbox2 = new VBox();
        ImageView image = new ImageView(new Image("file:assets/img/bonhomme.png"));
        image.setFitHeight(200);
        image.setFitWidth(175);
        vbox2.getChildren().add(image);
        vbox2.setAlignment(Pos.CENTER);
        this.center.setCenter(vbox);
        this.center.setLeft(vbox2);
    }

    /**
     * Met à jour la vue en fonction des données de la compétition
     * 
     * Affiche : - le type de la compétition (individuelle/collective), - le sport
     * de la compétition, - le sexe de la compétition, - le nb de participant, -est
     * jouee,
     * 
     * @param listeData
     * 
     */
    public void majParPays(List<String> listeData) {
        String nomPays = listeData.get(0);
        this.center.setCenter(null);
        this.center.setTop(hbox);
        hbox.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(25);
        this.text = new Text("Pays : " + nomPays);
        this.text.setStyle("-fx-font-size: 20 px; -fx-text-weight: bold;");
        this.text2 = new Text("Nombre d'Athlètes : " + listeData.get(1));
        this.text3 = new Text("Nombre d'Equipes : " + listeData.get(2));
        this.text4 = new Text("Participe à " + listeData.get(3) + " Compétitions");
        Text text5 = new Text("Performance global : " + listeData.get(4));
        vbox.getChildren().addAll(text, text2, text3, text4, text5);
        vbox.setStyle("-fx-font-size: 20 px; -fx-text-weight: bold;");
        VBox vbox2 = new VBox();

        try {
            String url = CompetitionsPane.getUrlFlag(120, 90, nomPays);
            ImageView paysImage = new ImageView(url);
            paysImage.setFitWidth(140);
            paysImage.setFitHeight(90);
            VBox.setMargin(paysImage, new Insets(50));
            vbox2.getChildren().add(paysImage);
        } catch (NoSuchFieldException e) {
            System.out.println(e);
        }

        vbox2.setAlignment(Pos.CENTER);
        this.center.setCenter(vbox);
        this.center.setLeft(vbox2);
    }

    /**
     * Affiche une fenêtre popup lié à la recherche
     * 
     * @return la fenêtre popup
     */
    public Alert getPopup(AlertType type, String text) {
        Alert alert = new Alert(type, text, ButtonType.OK);
        alert.setTitle("Recherche");
        return alert;
    }

    public enum TypeRecherche {
        ATHLETE,
        PAYS
    }

}
