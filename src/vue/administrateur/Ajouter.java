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

    /**
     * Constructeur de la classe Ajouter
     * @param navigation
     * @param mainPane
     */

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

    /**
     * Affichage de l'ajout d'un athlète
     */

    public void afficherAthlete() {

        // Création de la VBox pour l'ajout d'un athlète
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

        // Création de la HBox pour les statistiques de l'athlète
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


        // Remplie la combobox des compétitions individuelles pour les athlètes
        this.epreuveAthlete = new ComboBox<>();
        this.epreuveAthlete.setPromptText("Aucune compétition");

        this.dansEquipe = new CheckBox("Dans une équipe");
        this.dansEquipe.selectedProperty().addListener(new ControleurCheckBoxAddAthlete(this));
        

        this.equipe = new ComboBox<>();

        // Remplie la combobox des équipes
        this.equipe.setPromptText("Equipe");
        this.equipe.setVisible(false);

        HBox btnAthletes = new HBox(20);
        this.ajouterAthlete = new Button("Ajouter Athlete");
        this.ajouterAthlete.setOnAction(new ControleurAjouter(modele, this, TypeAjout.AjoutAthlete));
        
        // Bouton pour générer des athlètes depuis un fichier CSV
        Button genererCSV = new Button("Générer depuis CSV");
        genererCSV.setOnAction(new ControleurAddCsv(this));

        btnAthletes.getChildren().addAll(this.ajouterAthlete, genererCSV);
        ajoutBox.getChildren().addAll(athletelabel, this.nom, this.prenom, this.sexe, stat, this.pays, this.epreuveAthlete, dansEquipe, this.equipe, btnAthletes);

        ajoutBox.setPrefWidth(500);
        ajoutBox.setSpacing(10);
        
        BorderPane.setAlignment(ajoutBox, javafx.geometry.Pos.CENTER_LEFT);

        this.main.getChildren().add(ajoutBox);

        // Création d'un séparateur
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        main.getChildren().add(separator);
    }

    /**
     * Affichage de l'ajout d'une équipe
     */
    public void afficherEquipe() {
        VBox ajoutBox = new VBox();
        Label equipeLabel = new Label("Ajouter une Equipe");
        equipeLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        ajoutBox.setStyle("-fx-background-color: #FFFFFF;");
        this.nomEquipe = new TextField();
        this.nomEquipe.setId("accueil-tf");
        this.nomEquipe.setPromptText("Nom de l'equipe");

        // Remplie la combobox des compétitions collectives pour les équipes
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


    /**
     * Getter de la checkbox pour savoir si l'athlète est dans une équipe ou non
     * @return le bouton
     */
    public boolean athleteDansUneEquipe() {
        return this.dansEquipe.isSelected();
    }

    /**
     * Réinitialise les champs de texte
     */
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
    
    /**
     * Popup d'ajout d'un athlète/équipe
     * @param content le message à afficher
     * @return l'alert
     */
    public Alert addPopup(String content){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Administration");
        alert.setHeaderText(content);
        return alert;
    }

    /**
     * Popup d'erreur lors de l'ajout d'un athlète/équipe
     * @param content le message à afficher
     * @return l'alert
     */
    public Alert errorPopupMissingData(String content){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de la récupération des données");
        alert.setContentText(content);
        return alert;
    }

    /**
     * Enumération des types d'ajout possibles pour le contrôleur
     */
    public enum TypeAjout {
        AjoutAthlete,
        AjoutEquipe
    }

    /**
     * Retourne le modèle
     * @return le modèle
     */
    public JeuxOlympiques getModele() {
        return this.modele;
    }

    /**
     * Retourne la ComboBox des équipes
     * @return la ComboBox des équipes
     */
    public ComboBox<Equipe> getEquipesBox() {
        return this.equipe;
    }

    /**
     * Retourne la ComboBox des compétitions disponibles pour l'athlète
     * @return la ComboBox
     */
    public ComboBox<Competition> getEpreuvesAthletesBox() {
        return this.epreuveAthlete;
    }

    /**
     * Retourne la compétition sélectionnée pour l'athlète
     * @return la compétition sélectionnée
     */
    public Competition getEpreuveAthlete() {
        return this.epreuveAthlete.getValue();
    }

    /**
     * Retourne la ComboBox des compétitions disponibles pour les équipes
     * @return la ComboBox
     */
    public ComboBox<Competition> getEpreuvesEquipesBox() {
        return this.epreuveEquipes;
    }

    /**
     * Retourne la compétition sélectionnée pour les équipes
     * @return la compétition sélectionnée
     */
    public Competition getEpreuveEquipes() {
        return this.epreuveEquipes.getValue();
    }

    /**
     * Retourne le nom de l'athlète
     * @return
     */
    public TextField getNom() {
        return nom;
    }

    /**
     * Retourne le prénom de l'athlète saisie
     * @return le prénom de l'athlète
     */
    public TextField getPrenom() {
        return prenom;
    }

    /**
     * Retourne le sexe de l'athlète
     * @return le sexe de l'athlète
     */
    public Sexe getSexe() {
        return sexe.getValue();
    }

    /**
     * Retourne le pays de l'athlète
     * @return le pays de l'athlète
     */
    public TextField getPays() {
        return this.pays;
    }

    /**
     * Retourne la force de l'athlète
     * @return la force de l'athlète
     */
    public int getForce() {
        return Integer.parseInt(force.getText());
    }

    /**
     * Retourne l'endurance de l'athlète
     * @return l'endurance de l'athlète
     */
    public int getEndurance() {
        return Integer.parseInt(endurance.getText());
    }

    /**
     * Retourne l'agilité de l'athlète
     * @return l'agilité de l'athlète
     */
    public int getAgilite() {
        return Integer.parseInt(agilite.getText());
    }

    /**
     * Getter du bouton pour ajouter un athlète
     * @return le bouton
     */
    public Button getAjouterAthlete() {
        return ajouterAthlete;
    }

    /**
     * Retourne le nom de l'équipe saisie
     * @return le nom de l'équipe
     */
    public TextField getNomEquipe() {
        return nomEquipe;
    }

    /**
     * Retourne le pays de l'équipe saisie
     * @return le pays de l'équipe
     */
    public TextField getPaysEquipe() {
        return paysEquipe;
    }

    /**
     * Getter du bouton pour ajouter une équipe
     * @return le bouton
     */
    public Button getAjouterEquipe() {
        return ajouterEquipe;
    }

    /**
     * Getter du Main
     * @return Main
     */
    public Main getMain() {
        return this.mainPane;
    }
}