package vue.accueil;

import controller.ControleurAccueil;
import controller.ControleurRegisterJDBC;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public Register(Main main) {
        this.main = main;
        this.setStyle("-fx-background-color: white;");
        showTop();
        showLeft();
        showCenter();
        showRight();
        showBottom();
    }

    public void showTop() {
        HBox top = new HBox(50);
        top.setStyle("-fx-background-color: #36373B; -fx-border-radius: 15; -fx-background-radius: 15;");
        BorderPane.setMargin(top, new Insets(25));
        BorderPane.setAlignment(top, Pos.CENTER);
        top.setPrefWidth(407);
        top.setMaxWidth(407);
        top.setPrefHeight(76);

        top.setAlignment(Pos.CENTER);

        Button connecter = new Button("Se connecter");
        connecter.setOnAction(new ControleurAccueil(main, ButtonAction.LOGIN_PANE));
        connecter.setPrefSize(142, 50);
        connecter.setStyle(
                "-fx-text-fill: white; -fx-background-color: #36373B; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
        Button inscrire = new Button("S'inscrire");
        inscrire.setPrefSize(142, 50);
        inscrire.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-background-radius: 10; -fx-border-radius: 10;");
        top.getChildren().addAll(connecter, inscrire);

        this.setTop(top);
    }

    public void showCenter() {
        VBox center = new VBox(25);
        center.setAlignment(Pos.CENTER);
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

        mdp = new PasswordField();
        mdp.setPrefSize(433, 59);
        mdp.setId("accueil-tf");
        mdp.setPromptText("Mot de passe");
        center.getChildren().add(mdp);
        this.setCenter(center);
    }

    public void resetTF() {
        this.nom.setText("");
        this.prenom.setText("");
        this.pseudo.setText("");
        this.mdp.setText("");
    }

    public TextField getPseudo() {
        return this.pseudo;
    }

    public PasswordField getMdp() {
        return this.mdp;
    }

    public TextField getNom() {
        return this.nom;
    }

    public TextField getPrenom() {
        return this.prenom;
    }

    public void showLeft() {
        ImageView joParis = new ImageView("./assets/img/jo_paris.png");
        BorderPane.setAlignment(joParis, Pos.CENTER_RIGHT);
        joParis.setFitWidth(253);
        joParis.setFitHeight(173);
        this.setLeft(joParis);
    }

    public void showRight() {
        ImageView idCard = new ImageView("./assets/img/id_card.png");
        BorderPane.setAlignment(idCard, Pos.CENTER);
        idCard.setFitWidth(217);
        idCard.setFitHeight(229);
        this.setRight(idCard);
    }

    public void showBottom() {
        Button bottom = new Button("Inscription");

        bottom.setOnAction(new ControleurRegisterJDBC(this));

        BorderPane.setAlignment(bottom, Pos.CENTER);
        BorderPane.setMargin(bottom, new Insets(0, 0, 20, 0));

        bottom.setPrefSize(314, 70);
        bottom.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-font-size: 18;");
        this.setBottom(bottom);
    }

    public Main getMain() {
        return main;
    }
}