package vue.administrateur;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Sexe;
import modele.sports.Sport;
import vue.accueil.Navigation;

public class Ajouter extends BorderPane {

    public Ajouter(Navigation navigation) {

        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setTop(navigation);
        HBox main = new HBox();
        main.setStyle("-fx-background-color: #FFFFFF;");
        VBox Ajout_Athlete = new VBox();
        Label athletelabel = new Label("Ajouter un Athlete");
        Ajout_Athlete.setStyle("-fx-background-color: #FFFFFF;");
        TextField nom = new TextField();
        nom.setPromptText("Nom");
        TextField prenom = new TextField();
        prenom.setPromptText("Prenom");
        TextField Equipe = new TextField();
        Equipe.setPromptText("Equipe");
        ComboBox<Sexe> sexe = new ComboBox<>();
        sexe.getItems().addAll(Sexe.HOMME, Sexe.FEMME);
        sexe.setPromptText("Sexe");
        TextField pays = new TextField();
        pays.setPromptText("Pays");
        TextField epreuve = new TextField();
        epreuve.setPromptText("Epreuve");
        Button ajouter_athlete = new Button("Ajouter Athlete");
        Ajout_Athlete.getChildren().addAll(athletelabel, nom, prenom, Equipe, sexe, pays, epreuve, ajouter_athlete);
        Ajout_Athlete.setSpacing(10);
        Ajout_Athlete.setAlignment(javafx.geometry.Pos.CENTER);
        BorderPane.setAlignment(Ajout_Athlete, javafx.geometry.Pos.CENTER_LEFT);
        BorderPane.setAlignment(main, javafx.geometry.Pos.CENTER);
        VBox Ajout_Equipe = new VBox();
        Label equipeLabel = new Label("Ajouter une Equipe");
        Ajout_Equipe.setStyle("-fx-background-color: #FFFFFF;");
        TextField nom_equipe = new TextField();
        nom_equipe.setPromptText("Nom de l'equipe");
        TextField taille_max = new TextField();
        taille_max.setPromptText("Taille max");
        TextField sport = new TextField();
        sport.setPromptText("Sport");
        ComboBox<String> en_relais = new ComboBox<>();
        en_relais.getItems().addAll("Oui", "Non");
        en_relais.setPromptText("En relais");
        TextField pays_equipe = new TextField();
        pays_equipe.setPromptText("Pays");
        Button ajouter_equipe = new Button("Ajouter Equipe");
        Ajout_Equipe.getChildren().addAll(equipeLabel, nom_equipe, taille_max, sport, en_relais, pays_equipe,
                ajouter_equipe);
        Ajout_Equipe.setSpacing(10);
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        Ajout_Equipe.setAlignment(javafx.geometry.Pos.CENTER);
        main.getChildren().addAll(Ajout_Athlete, separator, Ajout_Equipe);
        main.setSpacing(10);
        BorderPane.setAlignment(Ajout_Equipe, javafx.geometry.Pos.CENTER_LEFT);
        main.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        this.setCenter(main);
    }

    public enum Ajout {
        Ajout_Athlete, Ajout_Equipe
    }

}