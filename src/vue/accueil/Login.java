package vue.accueil;

import bd.server.Utilisateur;
import controller.accueil.ControleurAccueil;
import controller.sql.ControleurLoginJDBC;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.JeuxOlympiques;
import vue.accueil.Main.ButtonAction;

/**
 * Cette classe est la vue de la page de connexion. Elle permet à l'utilisateur de se connecter à son compte. 
 * Elle contient un formulaire de connexion avec un champ pour le pseudo et un champ pour le mot de passe.
 * Elle contient également un bouton pour se connecter et un bouton pour s'inscrire.
 */

public class Login extends BorderPane {

    /**
     * main est le conteneur principal de l'application
     * modele est le modèle de l'application
     * pseudo est le champ de texte pour le pseudo
     * mdp est le champ de texte pour le mot de passe
     */

    private Main main;
    private JeuxOlympiques modele;
    private TextField pseudo;
    private PasswordField mdp;

    /**
     * Constructeur de la classe Login
     * @param main conteneur principal de l'application
     * @param modele modèle de l'application
     */

    public Login(Main main, JeuxOlympiques modele) {
        this.main = main;
        this.modele = modele;
        this.setStyle("-fx-background-color: white;");
        showTop();
        showLeft();
        showCenter();
        showRight();
        showBottom();
    }

    /**
     * Méthode permettant d'afficher le haut de la page de connexion
     */

    public void showTop() {

        // Création du haut de la page
        HBox top = new HBox(50);
        top.setStyle("-fx-background-color: #36373B; -fx-border-radius: 15; -fx-background-radius: 15;");
        BorderPane.setMargin(top, new Insets(25));
        BorderPane.setAlignment(top, Pos.CENTER);
        top.setPrefWidth(407);
        top.setMaxWidth(407);
        top.setPrefHeight(76);
        top.setAlignment(Pos.CENTER);

        // Création des boutons de connexion et d'inscription
        Button connecter = new Button("Se connecter");    
        connecter.setPrefSize(142, 50);
        connecter.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
        Button inscrire = new Button("S'inscrire");
        inscrire.setCursor(Cursor.HAND);
        inscrire.setOnAction(new ControleurAccueil(main, ButtonAction.REGISTER_PANE));
        inscrire.setPrefSize(142, 50);
        inscrire.setStyle(
                "-fx-text-fill: white; -fx-background-color: #36373B; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
        top.getChildren().addAll(connecter, inscrire);
        this.setTop(top); // On ajoute le haut de la page à la page de connexion
    }

    /**
     * Méthode permettant d'afficher le centre de la page de connexion
     */

    public void showCenter() {

        // Création du formulaire de connexion
        VBox center = new VBox(50);
        center.setAlignment(Pos.CENTER);
        pseudo = new TextField();
        pseudo.setPrefSize(433, 59);
        pseudo.setId("accueil-tf");
        pseudo.setPromptText("Pseudo");
        mdp = new PasswordField();
        mdp.setPrefSize(433, 59);
        mdp.setStyle("-fx-background-color: #414246; -fx-text-fill: white; -fx-padding: 10; -fx-font-weight: bold;");
        mdp.setPromptText("Mot de passe");
        center.getChildren().addAll(pseudo, mdp); 
        this.setCenter(center);
    }

    /**
     * Méthode permettant d'afficher la partie gauche de la page de connexion
     */

    public void showLeft() {
        ImageView joParis = new ImageView("./assets/img/jo_paris.png"); // Image des JO de Paris
        BorderPane.setAlignment(joParis, Pos.CENTER_RIGHT);
        joParis.setFitWidth(253);
        joParis.setFitHeight(173);
        this.setLeft(joParis); // On ajoute l'image à la partie gauche de la page
    }

    /**
     * Méthode permettant d'afficher la partie droite de la page de connexion
     */
    public void showRight() {
        ImageView idCard = new ImageView("./assets/img/id_card.png"); // Image de la carte d'identité
        BorderPane.setAlignment(idCard, Pos.CENTER);
        idCard.setFitWidth(217);
        idCard.setFitHeight(229);
        this.setRight(idCard);
    }

    /**
     * Méthode permettant de récupérer le pseudo
     * @return le champ de texte pour le pseudo
     */

    public TextField getPseudo() {
        return this.pseudo;
    }

    /**
     * Méthode permettant de récupérer le mot de passe
     * @return le champ de texte pour le mot de passe
     */

    public PasswordField getMdp() {
        return this.mdp;
    }

    /**
     * Méthode permettant d'afficher le bas de la page de connexion
     */

    public void showBottom() {
        Button bottom = new Button("Connexion"); // Bouton de connexion
        bottom.setCursor(Cursor.HAND);
        bottom.setOnAction(new ControleurLoginJDBC(this));
        BorderPane.setAlignment(bottom, Pos.CENTER);
        BorderPane.setMargin(bottom, new Insets(0, 0, 20, 0));
        bottom.setPrefSize(314, 70);
        bottom.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-font-size: 18;");
        this.setBottom(bottom);
    }

    /**
     * Méthode permettant d'afficher l'accueil de l'application
     * @param user utilisateur connecté
     */

    public void afficherAccueil(Utilisateur user){
        this.main.getScene().setRoot(new Actualite(this, this.modele, user));
    }

    /**
     * Méthode permettant de réinitialiser les champs de texte
     */

    public void resetTF() {
        this.pseudo.setText("");
        this.mdp.setText("");
    }

    /**
     * Méthode permettant de récupérer le conteneur principal de l'application
     * @return le conteneur principal de l'application
     */

    public Main getMain() {
        return this.main;
    }

}