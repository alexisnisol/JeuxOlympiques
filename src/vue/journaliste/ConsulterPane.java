package vue.journaliste;

import java.util.List;
import java.util.Map;

import controller.ControleurConsultation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modele.Classement;
import modele.Pays;
import modele.competitions.Competition;
import vue.accueil.Main;
import vue.accueil.Navigation;

public class ConsulterPane extends BorderPane {

    private Navigation navigation;

    private Main main;

    public ConsulterPane(Navigation navigation, Main main) {
        this.main = main;
        this.navigation = navigation;
        this.setStyle("-fx-background-color: white;");
        showTop();
        showCenter();
    }

    public void showTop() {
        this.setTop(this.navigation);
    }

    public void showCenter() {
        HBox boutons = new HBox(70);
        boutons.setAlignment(Pos.CENTER);
        Button medailles = new Button("Medailles");
        medailles.setId("consultBtn");
        medailles.setPrefSize(200, 150);
        medailles.setOnAction(new ControleurConsultation(this, this.main.getModele(), ConsultAction.MEDAILLES));

        Button compet = new Button("Compétitions");
        compet.setId("consultBtn");
        compet.setPrefSize(200, 150);
        compet.setOnAction(new ControleurConsultation(this, this.main.getModele(), ConsultAction.COMPETITIONS));

        // Button pays = new Button("Pays");

        boutons.getChildren().addAll(medailles, compet);// , pays);
        this.setCenter(boutons);
    }

    public void afficherMedailles(Map<Pays, Classement> medailles) {
        this.main.getScene().setRoot(new Medailles(this.navigation, medailles));
    }

    public void afficherCompetitions(List<Competition> competitions) {

        this.main.getScene().setRoot(new CompetitionsPane(this.navigation, competitions));
    }

    public enum ConsultAction {
        MEDAILLES, COMPETITIONS, PAYS
    }
}
