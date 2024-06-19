package vue.accueil;

import controller.ControleurAccueil;
import controller.ControleurLoginJDBC;
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
import vue.accueil.accueilbase.Actualite;

public class Login extends BorderPane {

    private Main main;
    private JeuxOlympiques modele;
    private TextField pseudo;
    private PasswordField mdp;

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

        this.setTop(top);
    }

    public void showCenter() {
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

    public TextField getPseudo() {
        return this.pseudo;
    }

    public PasswordField getMdp() {
        return this.mdp;
    }

    public void showBottom() {
        Button bottom = new Button("Connexion");

        bottom.setCursor(Cursor.HAND);
        Actualite actualite = new Actualite(this, this.modele);

        bottom.setOnAction(new ControleurLoginJDBC(this, actualite));

        BorderPane.setAlignment(bottom, Pos.CENTER);
        BorderPane.setMargin(bottom, new Insets(0, 0, 20, 0));

        bottom.setPrefSize(314, 70);
        bottom.setStyle(
                "-fx-text-fill: white; -fx-background-color: #0781FE; -fx-font-weight: bold; -fx-font-size: 18;");
        this.setBottom(bottom);
    }

    public void resetTF() {
        this.pseudo.setText("");
        this.mdp.setText("");
    }

    public Main getMain() {
        return this.main;
    }

    // Alert : mdp incorrect
}