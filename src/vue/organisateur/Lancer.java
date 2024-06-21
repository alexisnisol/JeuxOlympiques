package vue.organisateur;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import vue.accueil.Main;
import javafx.scene.control.ProgressIndicator;
import vue.accueil.Navigation;
import controller.orga.ControleurOrganisation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class Lancer extends BorderPane {

    private Main mainPane;
    private Navigation navigation;

    private Button buttonLancer;
    private BorderPane borderPane;

    public Lancer(Navigation navigation, Main mainPane) {
        this.mainPane = mainPane;
        this.setTop(navigation);
        this.borderPane = new BorderPane();
        this.setStyle("-fx-background-color: #FFFFFF;");
        Label label = new Label("Lancer les jeux olympiques");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        this.buttonLancer = new Button();
        buttonLancer.setStyle("-fx-font-size: 20px; -fx-background-color: lightblue; -fx-text-fill: #000000;");
        buttonLancer.setPrefSize(200, 50);
        buttonLancer.setText("GO !");
        buttonLancer.setId("nav-loupe");
        buttonLancer.setOnAction(new ControleurOrganisation(this));
        borderPane.setTop(label);
        borderPane.setCenter(buttonLancer);
        BorderPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        BorderPane.setAlignment(buttonLancer, javafx.geometry.Pos.CENTER);
        this.setCenter(borderPane);

    }

    public void lancerJeuxOlympiques() {
        mainPane.getModele().lancerJeuxOlympiques();
    }

    public void majAffichage() {
        ProgressIndicator progressIndicator = new ProgressIndicator();
        this.borderPane.setCenter(progressIndicator);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            this.borderPane.setCenter(null);
            this.borderPane.setCenter(new Label("Les jeux olympiques ont bien été lancés !"));
        }));
        timeline.play();
    }
}