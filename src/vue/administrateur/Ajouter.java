package vue.administrateur;

import vue.accueil.*;
import modele.*;
import modele.competitions.Competition;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controller.admin.ControleurAjouter;
import controller.admin.ObservableAjoutAthlete;
import controller.admin.ControleurCheckBoxAddAthlete;

public class Ajouter extends BorderPane {

    private ComboBox<Competition> epreuve;
    private TextField nom;
    private TextField prenom;
    private TextField equipe;
    private ComboBox<Sexe> sexe;
    private TextField pays;
    private TextField force;
    private TextField endurance;
    private TextField agilite;
    private Button ajouter_athlete;
    private TextField nom_equipe;
    private TextField taille_max;
    private TextField sport_equipe;
    private ComboBox<String> en_relais;
    private TextField pays_equipe;
    private Button ajouter_equipe;

    private HBox main;

    private Main mainPane;
    private JeuxOlympiques modele;

    public Ajouter(Navigation navigation, Main mainPane) {
        this.mainPane = mainPane;
        this.modele = mainPane.getModele();
        this.setStyle("-fx-background-color: #FFFFFF;");
        this.setTop(navigation);

        this.main = new HBox();
        this.main.setStyle("-fx-background-color: #FFFFFF;");
        this.main.setSpacing(10);
        BorderPane.setAlignment(this.main, Pos.CENTER);
        this.main.setPadding(new Insets(20, 20, 20, 20));
        this.setCenter(this.main);

        this.afficherAthlete();
        this.afficherEquipe();

    }

    public void afficherAthlete() {
        VBox ajoutBox = new VBox();
        Label athletelabel = new Label("Ajouter un Athlete");
        ajoutBox.setStyle("-fx-background-color: #FFFFFF;");
        this.nom = new TextField();
        this.nom.setPromptText("Nom");
        this.prenom = new TextField();
        this.prenom.setPromptText("Prenom");
        this.sexe = new ComboBox<>();
        this.sexe.getItems().addAll(Sexe.HOMME, Sexe.FEMME);
        this.sexe.valueProperty().addListener(new ObservableAjoutAthlete(this));
        this.sexe.setPromptText("Sexe");

        this.force = new TextField();
        this.force.setPromptText("Force");
        this.endurance = new TextField();
        this.endurance.setPromptText("Endurance");
        this.agilite = new TextField();
        this.agilite.setPromptText("Agilite");

        this.pays = new TextField();
        this.pays.setPromptText("Pays");

        this.epreuve = new ComboBox<>();

        CheckBox dansEquipe = new CheckBox("Dans une équipe");
        dansEquipe.selectedProperty().addListener(new ControleurCheckBoxAddAthlete(this));

        this.epreuve.setPromptText("Aucune compétition");

        this.equipe = new TextField();
        this.equipe.setPromptText("Equipe");
        this.equipe.setVisible(false);

        this.ajouter_athlete = new Button("Ajouter Athlete");
        ajoutBox.getChildren().addAll(athletelabel, this.nom, this.prenom, this.sexe, this.force, this.endurance,
                this.agilite, this.pays, this.epreuve, dansEquipe, this.equipe, this.ajouter_athlete);

        ajoutBox.setSpacing(10);
        ajoutBox.setAlignment(javafx.geometry.Pos.CENTER);
        BorderPane.setAlignment(ajoutBox, javafx.geometry.Pos.CENTER_LEFT);

        this.main.getChildren().add(ajoutBox);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        main.getChildren().add(separator);
    }

    public void afficherEquipe() {
        VBox ajoutBox = new VBox();
        Label equipeLabel = new Label("Ajouter une Equipe");
        ajoutBox.setStyle("-fx-background-color: #FFFFFF;");
        this.nom_equipe = new TextField();
        this.nom_equipe.setPromptText("Nom de l'equipe");
        this.taille_max = new TextField();
        this.taille_max.setPromptText("Taille max");
        this.sport_equipe = new TextField();
        this.sport_equipe.setPromptText("Sport");
        this.en_relais = new ComboBox<>();
        this.en_relais.getItems().addAll("Oui", "Non");
        this.en_relais.setPromptText("En relais");
        this.pays_equipe = new TextField();
        this.pays_equipe.setPromptText("Pays");
        this.ajouter_equipe = new Button("Ajouter Equipe");
        this.ajouter_athlete.setOnAction(new ControleurAjouter(modele, this, TypeAjout.AjoutAthlete));
        ajoutBox.getChildren().addAll(equipeLabel, this.nom_equipe, this.taille_max, this.sport_equipe,
                this.en_relais,
                this.pays_equipe,
                this.ajouter_equipe);
        ajoutBox.setSpacing(10);

        ajoutBox.setAlignment(javafx.geometry.Pos.CENTER);

        this.main.getChildren().add(ajoutBox);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        main.getChildren().add(separator);
    }

    public enum TypeAjout {
        AjoutAthlete,
        AjoutEquipe
    }

    public JeuxOlympiques getModele() {
        return this.modele;
    }

    public ComboBox<Competition> getEpreuveBox() {
        return this.epreuve;
    }

    public Competition getEpreuve() {
        return this.epreuve.getValue();
    }

    public TextField getNom() {
        return nom;
    }

    public TextField getPrenom() {
        return prenom;
    }

    public TextField getEquipe() {
        return equipe;
    }

    public Sexe getSexe() {
        return sexe.getValue();
    }

    public Pays getPays() {
        return Pays.valueOf(pays.getText());
    }

    public int getForce() {
        return Integer.parseInt(force.getText());
    }

    public int getEndurance() {
        return Integer.parseInt(endurance.getText());
    }

    public int getAgilite() {
        return Integer.parseInt(agilite.getText());
    }

    public Button getAjouterAthlete() {
        return ajouter_athlete;
    }

    public TextField getNomEquipe() {
        return nom_equipe;
    }

    public int getTailleMax() {
        return Integer.parseInt(taille_max.getText());
    }

    public String getSport() {
        return sport_equipe.getText();
    }

    public boolean getEnRelais() {
        return en_relais.getValue().equals("Oui");
    }

    public Pays getPaysEquipe() {
        return Pays.valueOf(pays_equipe.getText());
    }

    public Button getAjouterEquipe() {
        return ajouter_equipe;
    }

    public Main getMain() {
        return this.mainPane;
    }
}