package vue.accueil;

import controller.accueil.ControleurAccueil;
import controller.sql.ControleurRegisterJDBC;
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
import vue.accueil.Main.ButtonAction;

public class Register extends BorderPane {

    private Main main;
    private TextField pseudo;
    private PasswordField mdp;
    private TextField nom;
    private TextField prenom;

    /**
     * Constructeur de Register, la page d'inscription
     * @param main le Main
     */
    public Register(Main main) {
        this.main = main;
        this.setStyle("-fx-background-color: white;");
        showTop();
        showLeft();
        showCenter();
        showRight();
        showBottom();
    }

    /**
     * Affiche le haut de la page de connexion, avec les boutons pour se connecter ou s'inscrire
     */
    public void showTop() {
        HBox top = new HBox(50);
        top.setStyle("-fx-background-color: #36373B; -fx-border-radius: 15; -fx-background-radius: 15;");
        BorderPane.setMargin(top, new Insets(25));
        BorderPane.setAlignment(top, Pos.CENTER);
        top.setPrefWidth(407);
        top.setMaxWidth(407);
        top.setPrefHeight(76);

        top.setAlignment(Pos.CENTER);

        //Bouton pour se connecter
        Button connecter = new Button("Se connecter");
        connecter.setCursor(Cursor.HAND);
        connecter.setOnAction(new ControleurAccueil(main, ButtonAction.LOGIN_PANE));
        connecter.setPrefSize(142, 50);
        connecter.setStyle(
                "-fx-text-fill: white; -fx-background-color: #36373B; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");

        //Bouton pour s'inscrire
        Button inscrire = new Button("S'inscrire");
        inscrire.setPrefSize(142, 50);
        inscrire.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
        top.getChildren().addAll(connecter, inscrire);

        this.setTop(top);
    }

    /**
     * Affiche le centre de la page d'inscription, avec les champs à remplir
     */
    public void showCenter() {
        VBox center = new VBox(25);
        center.setAlignment(Pos.CENTER);

        //Champs pour le nom, prénom et pseudo
        nom = new TextField();
        nom.setPromptText("Nom");
        nom.setId("accueil-tf");
        nom.setPrefSize(433, 59);
        prenom = new TextField();
        prenom.setPromptText("Prénom");
        prenom.setId("accueil-tf");
        prenom.setPrefSize(433, 59);
        pseudo = new TextField();
        pseudo.setPromptText("Pseudo");
        pseudo.setId("accueil-tf");
        pseudo.setPrefSize(433, 59);
        center.getChildren().addAll(nom, prenom, pseudo);

        //Champ pour le mot de passe
        mdp = new PasswordField();
        mdp.setPrefSize(433, 59);
        mdp.setId("accueil-tf");
        mdp.setPromptText("Mot de passe");
        center.getChildren().add(mdp);
        this.setCenter(center);
    }

    /**
     * Réinitialise les champs de texte
     */
    public void resetTF() {
        this.nom.setText("");
        this.prenom.setText("");
        this.pseudo.setText("");
        this.mdp.setText("");
    }

    /**
     * Getter du pseudo
     * @return TextField
     */
    public TextField getPseudo() {
        return this.pseudo;
    }

    /**
     * Getter du mot de passe
     * @return PasswordField
     */
    public PasswordField getMdp() {
        return this.mdp;
    }

    /**
     * Getter du nom
     * @return TextField
     */
    public TextField getNom() {
        return this.nom;
    }

    /**
     * Getter du prénom
     * @return TextField
     */
    public TextField getPrenom() {
        return this.prenom;
    }

    /**
     * Affiche l'image de gauche
     */
    public void showLeft() {
        ImageView joParis = new ImageView("./assets/img/jo_paris.png");
        BorderPane.setAlignment(joParis, Pos.CENTER_RIGHT);
        joParis.setFitWidth(253);
        joParis.setFitHeight(173);
        this.setLeft(joParis);
    }

    /**
     * Affiche l'image de droite
     */
    public void showRight() {
        ImageView idCard = new ImageView("./assets/img/id_card.png");
        BorderPane.setAlignment(idCard, Pos.CENTER);
        idCard.setFitWidth(217);
        idCard.setFitHeight(229);
        this.setRight(idCard);
    }

    /**
     * Affiche le bas de la page de connexion, avec un bouton pour s'inscrire
     */
    public void showBottom() {
        Button bottom = new Button("Inscription");
        bottom.setCursor(Cursor.HAND);
        bottom.setOnAction(new ControleurRegisterJDBC(this));

        BorderPane.setAlignment(bottom, Pos.CENTER);
        BorderPane.setMargin(bottom, new Insets(0, 0, 20, 0));

        bottom.setPrefSize(314, 70);
        bottom.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-font-size: 18;");
        this.setBottom(bottom);
    }

    /**
     * Getter du main
     * @return Main
     */
    public Main getMain() {
        return main;
    }
}