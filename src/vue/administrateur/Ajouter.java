package vue.administrateur;

import vue.accueil.*;
import modele.*;
import modele.competitions.Competition;
import modele.competitions.CompetitionCollective;
import modele.participants.Equipe;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import controller.admin.ControleurAjouter;
import controller.admin.ControleurBonnesEquipes;
import controller.admin.ObservableAjoutAthlete;
import controller.admin.ControleurCheckBoxAddAthlete;

public class Ajouter extends BorderPane {

    private ComboBox<Competition> epreuveAthlete;
    private ComboBox<Competition> epreuveEquipes;
    private ComboBox<Equipe> equipe;
    private TextField nom;
    private TextField prenom;
    private ComboBox<Sexe> sexe;
    private TextField pays;
    private TextField force;
    private TextField endurance;
    private TextField agilite;
    private Button ajouter_athlete;
    private TextField nom_equipe;
    private TextField pays_equipe;
    private Button ajouter_equipe;

    private CheckBox dansEquipe;
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
        this.pays.textProperty().addListener(new ControleurBonnesEquipes(this));

        this.epreuveAthlete = new ComboBox<>();
        this.epreuveAthlete.setPromptText("Aucune compétition");

        this.dansEquipe = new CheckBox("Dans une équipe");
        this.dansEquipe.selectedProperty().addListener(new ControleurCheckBoxAddAthlete(this));
        

        this.equipe = new ComboBox<>();

        this.equipe.setPromptText("Equipe");
        this.equipe.setVisible(false);

        this.ajouter_athlete = new Button("Ajouter Athlete");
        this.ajouter_athlete.setOnAction(new ControleurAjouter(modele, this, TypeAjout.AjoutAthlete));
        ajoutBox.getChildren().addAll(athletelabel, this.nom, this.prenom, this.sexe, this.force, this.endurance,
                this.agilite, this.pays, this.epreuveAthlete, dansEquipe, this.equipe, this.ajouter_athlete);

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

        this.epreuveEquipes = new ComboBox<>();
        for(Competition compet : this.modele.getLesCompetitions()){
            if(compet instanceof CompetitionCollective){
                this.epreuveEquipes.getItems().add(compet);
            }
        }
        this.epreuveEquipes.setPromptText("Aucune compétition");

        this.pays_equipe = new TextField();
        this.pays_equipe.setPromptText("Pays");
        this.ajouter_equipe = new Button("Ajouter Equipe");
        this.ajouter_equipe.setOnAction(new ControleurAjouter(modele, this, TypeAjout.AjoutEquipe));
        ajoutBox.getChildren().addAll(equipeLabel, this.nom_equipe,
                this.pays_equipe, this.epreuveEquipes,
                this.ajouter_equipe);
        ajoutBox.setSpacing(10);

        ajoutBox.setAlignment(javafx.geometry.Pos.CENTER);

        this.main.getChildren().add(ajoutBox);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        main.getChildren().add(separator);
    }


    public boolean athleteDansUneEquipe() {
        return this.dansEquipe.isSelected();
    }

    public void resetTF(){
        this.nom.setText("");
        this.prenom.setText("");
        this.epreuveAthlete.setValue(null);
        this.epreuveEquipes.setValue(null);
        this.equipe.setValue(null);
        this.sexe.setValue(null);
        this.pays.setText("");
        this.force.setText("");
        this.endurance.setText("");
        this.agilite.setText("");
        this.nom_equipe.setText("");
        this.pays_equipe.setText("");
    }

    public Alert addPopup(String content){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Administration");
        alert.setContentText(content);
        return alert;
    }

    public enum TypeAjout {
        AjoutAthlete,
        AjoutEquipe
    }

    public JeuxOlympiques getModele() {
        return this.modele;
    }

    public ComboBox<Equipe> getEquipesBox() {
        return this.equipe;
    }

    public ComboBox<Competition> getEpreuvesAthletesBox() {
        return this.epreuveAthlete;
    }

    public Competition getEpreuveAthlete() {
        return this.epreuveAthlete.getValue();
    }
    
    public ComboBox<Competition> getEpreuvesEquipesBox() {
        return this.epreuveEquipes;
    }

    public Competition getEpreuveEquipes() {
        return this.epreuveEquipes.getValue();
    }

    public TextField getNom() {
        return nom;
    }

    public TextField getPrenom() {
        return prenom;
    }

    public Sexe getSexe() {
        return sexe.getValue();
    }

    public TextField getPays() {
        return this.pays;
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

    public TextField getPaysEquipe() {
        return pays_equipe;
    }

    public Button getAjouterEquipe() {
        return ajouter_equipe;
    }

    public Main getMain() {
        return this.mainPane;
    }
}