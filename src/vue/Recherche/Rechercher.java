package vue.Recherche;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.NavigableMap;

import controller.ControleurActionRecherche;
import controller.ControleurKeyEventRecherche;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
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

    public Rechercher(Navigation navigation) {

        this.setOnKeyPressed(new ControleurKeyEventRecherche(this));

        this.setStyle("-fx-background-color: #FFFFFF;");
        // haut de la fenêtre

        this.setTop(navigation);

        // centre de la fenêtre

        this.center = new BorderPane();
        this.setCenter(center);
        this.hbox = new HBox();
        ImageView loupe = new ImageView(new Image("file:assets/img/loupe_recherche.png"));
        Button rechercher = new Button();
        rechercher.setOnAction(new ControleurActionRecherche(this));
        rechercher.setGraphic(loupe);
        rechercher.setId("nav-loupe");
        loupe.setPreserveRatio(true);
        loupe.setFitHeight(25);
        loupe.setFitWidth(25);
        TextField recherche = new TextField();
        recherche.setPromptText("Rechercher par Sports, Athtlétes, Compétition");
        recherche.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(recherche, Priority.ALWAYS);
        recherche.setStyle("-fx-font-size: 12.5 px; -fx-text-weight: bold;");
        hbox.getChildren().addAll(recherche, rechercher);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(0, 20, 0, 20));
        center.setCenter(hbox);

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

    public void majAffichage() {
        // Faire changer l'affichage pour mettre l'athlète etc
        this.center.setCenter(null);
        this.center.setTop(hbox);
        hbox.setPadding(new Insets(20, 20, 20, 20));
        VBox vbox = new VBox(40);
        this.text = new Text("Nom, Prénom de l'athlète : ");
        this.text2 = new Text("Équipe : ");
        this.text3 = new Text("Nombre de médailles d'or : ");
        this.text4 = new Text("Performance à la dérnière compétition : ");
        vbox.getChildren().addAll(text, text2, text3, text4);
        vbox.setStyle("-fx-font-size: 20 px; -fx-text-weight: bold;");
        VBox vbox2 = new VBox();
        ImageView image = new ImageView(new Image("file:assets/img/bonhomme.png"));
        image.setFitHeight(200);
        image.setFitWidth(175);
        vbox2.getChildren().add(image);
        vbox2.setAlignment(Pos.CENTER);
        this.center.setCenter(vbox);
        this.center.setLeft(vbox2);
        this.center.setPadding(new Insets(0, 0, 20, 0));
        this.setBottom(footer);
    }

}
