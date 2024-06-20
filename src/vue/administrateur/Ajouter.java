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
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controller.admin.ControleurAddCsv;
import controller.admin.ControleurAjouter;
import controller.admin.observables.ControleurBonnesEquipes;
import controller.admin.observables.ControleurCheckBoxAddAthlete;
import controller.admin.observables.ObservableAjoutAthlete;

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
    private Button ajouterAthlete;
    private TextField nomEquipe;
    private TextField paysEquipe;
    private Button ajouterEquipe;

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
        athletelabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        ajoutBox.setStyle("-fx-background-color: #FFFFFF;");
        this.nom = new TextField();
        this.nom.setId("accueil-tf");
        this.nom.setPromptText("Nom");
        this.prenom = new TextField();
        this.prenom.setId("accueil-tf");
        this.prenom.setPromptText("Prenom");
        this.sexe = new ComboBox<>();
        this.sexe.getItems().addAll(Sexe.HOMME, Sexe.FEMME);
        this.sexe.valueProperty().addListener(new ObservableAjoutAthlete(this));
        this.sexe.setPromptText("Sexe");

        HBox stat = new HBox();
        stat.setSpacing(10);
        this.force = new TextField();
        this.force.setId("accueil-tf");
        this.force.setPromptText("Force");
        this.endurance = new TextField();
        this.endurance.setId("accueil-tf");
        this.endurance.setPromptText("Endurance");
        this.agilite = new TextField();
        this.agilite.setId("accueil-tf");
        this.agilite.setPromptText("Agilite");

        stat.getChildren().addAll(this.force, this.endurance, this.agilite);

        this.pays = new TextField();
        this.pays.setId("accueil-tf");
        this.pays.setPromptText("Pays");
        this.pays.textProperty().addListener(new ControleurBonnesEquipes(this));

        this.epreuveAthlete = new ComboBox<>();
        this.epreuveAthlete.setPromptText("Aucune compétition");

        this.dansEquipe = new CheckBox("Dans une équipe");
        this.dansEquipe.selectedProperty().addListener(new ControleurCheckBoxAddAthlete(this));
        

        this.equipe = new ComboBox<>();

        this.equipe.setPromptText("Equipe");
        this.equipe.setVisible(false);

        HBox btnAthletes = new HBox(20);
        this.ajouterAthlete = new Button("Ajouter Athlete");
        this.ajouterAthlete.setOnAction(new ControleurAjouter(modele, this, TypeAjout.AjoutAthlete));
        
        Button genererCSV = new Button("Générer depuis CSV");
        genererCSV.setOnAction(new ControleurAddCsv(this));

        btnAthletes.getChildren().addAll(this.ajouterAthlete, genererCSV);

        ajoutBox.getChildren().addAll(athletelabel, this.nom, this.prenom, this.sexe, stat, this.pays, this.epreuveAthlete, dansEquipe, this.equipe, btnAthletes);

        ajoutBox.setPrefWidth(500);
        ajoutBox.setSpacing(10);
        
        BorderPane.setAlignment(ajoutBox, javafx.geometry.Pos.CENTER_LEFT);

        this.main.getChildren().add(ajoutBox);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        main.getChildren().add(separator);
    }

    public void afficherEquipe() {
        VBox ajoutBox = new VBox();
        Label equipeLabel = new Label("Ajouter une Equipe");
        equipeLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        ajoutBox.setStyle("-fx-background-color: #FFFFFF;");
        this.nomEquipe = new TextField();
        this.nomEquipe.setId("accueil-tf");
        this.nomEquipe.setPromptText("Nom de l'equipe");

        this.epreuveEquipes = new ComboBox<>();
        for(Competition compet : this.modele.getLesCompetitions()){
            if(compet instanceof CompetitionCollective){
                this.epreuveEquipes.getItems().add(compet);
            }
        }
        this.epreuveEquipes.setPromptText("Aucune compétition");

        this.paysEquipe = new TextField();
        this.paysEquipe.setId("accueil-tf");
        this.paysEquipe.setPromptText("Pays");
        this.ajouterEquipe = new Button("Ajouter Equipe");
        this.ajouterEquipe.setOnAction(new ControleurAjouter(modele, this, TypeAjout.AjoutEquipe));
        ajoutBox.getChildren().addAll(equipeLabel, this.nomEquipe,
                this.paysEquipe, this.epreuveEquipes,
                this.ajouterEquipe);
        ajoutBox.setSpacing(10);

        this.main.getChildren().add(ajoutBox);
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
        this.nomEquipe.setText("");
        this.paysEquipe.setText("");
    }

    public Alert addPopup(String content){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Administration");
        alert.setHeaderText(content);
        return alert;
    }

    public Alert errorPopupMissingData(String content){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de la récupération des données");
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

    public void setModele(JeuxOlympiques modele) {
        this.modele = modele;
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
        return ajouterAthlete;
    }

    public TextField getNomEquipe() {
        return nomEquipe;
    }

    public TextField getPaysEquipe() {
        return paysEquipe;
    }

    public Button getAjouterEquipe() {
        return ajouterEquipe;
    }

    public Main getMain() {
        return this.mainPane;
    }
}