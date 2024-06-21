package vue.accueil;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vue.organisateur.Lancer;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import modele.JeuxOlympiques;
import vue.administrateur.Ajouter;
import vue.administrateur.UtilisateursPane;
import vue.journaliste.ConsulterPane;
import vue.journaliste.Rechercher;
import bd.server.Utilisateur;
import bd.server.RequetesJDBC.RoleConnexion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;

public class Actualite extends BorderPane {

    private Login login;
    private JeuxOlympiques modele;
    private Navigation navigation;
    private Utilisateur user;

    public Actualite(Login login, JeuxOlympiques modele, Utilisateur user) {

        this.user = user;
        this.login = login;
        this.modele = this.login.getMain().getModele();

        this.setStyle("-fx-background-color: #FFFFFF;");

        navigation = new Navigation(this, this.modele);
        this.setTop(navigation);

        // centre de la fenetre
        BorderPane main = new BorderPane();
        main.setPrefHeight(400);
        BorderPane.setMargin(main, new Insets(10));

        main.setPadding(new Insets(10))  ;

        // premiere article
        ImageView J = new ImageView(new Image("file:assets/img/J.png"));
        J.setFitHeight(150);
        J.setFitWidth(200);
        VBox vboxJ = new VBox();
        vboxJ.setPadding(new Insets(10));
        vboxJ.setSpacing(10);
        vboxJ.setStyle("-fx-background-color: #D9D9D9; -fx-border-color: #D9D9D9; -fx-border-radius: 20;");
        vboxJ.setAlignment(Pos.CENTER);
        String TextJ = "La flamme olympique, qui était partie le 27 avril du port du Pirée à bord du Belem, a débarqué ce mercredi sur le sol français. Jul, rappeur marseillais au succès incontestable, a allumé le chaudron placé sur le Vieux-Port de Marseille.";
        TextFlow textJ = new TextFlow(new Text(TextJ));
        textJ.setTextAlignment(TextAlignment.CENTER);
        textJ.setStyle(
                "-fx-background-color: transparent;  -fx-border-color: transparent transparent transparent transparent; -fx-font-size: 11px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-text-fill: #000000;");
        textJ.setPrefSize(300, 100);
        vboxJ.setMaxHeight(150);
        vboxJ.setPrefSize(270, 100);
        vboxJ.getChildren().addAll(J, textJ);

        // deuxieme article
        VBox vboxVolley = new VBox();
        ImageView volley = new ImageView(new Image("file:assets/img/volley.jpg"));
        volley.setFitHeight(150);
        volley.setFitWidth(200);
        String longText = "Une performance encourageante pour les Bleus à l'approche des Jeux Olympiques de Paris 2024. Les Tricolores, médaillés d'or à Tokyo, ont vaincu l'Italie, champion du monde";
        TextFlow text = new TextFlow(new Text(longText));
        text.setStyle(
                "-fx-background-color: transparent;  -fx-border-color: transparent transparent transparent transparent; -fx-font-size: 12.5px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-text-fill: #000000;");
        text.setPrefSize(300, 100);
        text.setTextAlignment(TextAlignment.CENTER);
        vboxVolley.setPadding(new Insets(10));
        vboxVolley.setSpacing(10);
        vboxVolley.setStyle("-fx-background-color: #D9D9D9; -fx-border-color: #D9D9D9; -fx-border-radius: 20;");
        vboxVolley.setAlignment(Pos.CENTER);
        vboxVolley.setPrefSize(270, 150);
        vboxVolley.setMaxHeight(150);
        vboxVolley.getChildren().addAll(volley, text);

        // troisieme article
        VBox vboxNatation = new VBox();
        ImageView natation = new ImageView(new Image("file:assets/img/natation.png"));
        natation.setFitHeight(150);
        natation.setFitWidth(200);
        String longTextNatation = "Les nageurs français ont réalisé une performance exceptionnelle lors des championnats d'Europe de natation. Les Bleus ont remporté 10 médailles dont 4 en or.";
        TextFlow textNatation = new TextFlow(new Text(longTextNatation));
        textNatation.setStyle(
                "-fx-background-color: transparent;  -fx-border-color: transparent transparent transparent transparent; -fx-font-size: 12.5px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-text-fill: #000000;");
        textNatation.setTextAlignment(TextAlignment.CENTER);
        textNatation.setPrefSize(300, 100);
        vboxNatation.setPadding(new Insets(10));
        vboxNatation.setSpacing(10);
        vboxNatation.setStyle("-fx-background-color: #D9D9D9; -fx-border-color: #D9D9D9; -fx-border-radius: 20;");
        vboxNatation.setAlignment(Pos.CENTER);
        vboxNatation.setPrefSize(270, 150);
        vboxNatation.getChildren().addAll(natation, textNatation);
        vboxNatation.setMaxHeight(150);

        VBox.setMargin(vboxNatation, new Insets(10, 10, 10, 10));
        VBox.setMargin(vboxJ, new Insets(10, 10, 10, 10));
        VBox.setMargin(vboxVolley, new Insets(10, 10, 10, 10));

        BorderPane.setAlignment(main, Pos.CENTER);
        BorderPane.setAlignment(vboxJ, Pos.CENTER);
        BorderPane.setAlignment(vboxNatation, Pos.CENTER);
        BorderPane.setAlignment(vboxVolley, Pos.CENTER);

        main.setCenter(vboxVolley);
        main.setLeft(vboxJ);
        main.setRight(vboxNatation);

        BorderPane.setMargin(vboxJ, new Insets(10));
        BorderPane.setMargin(vboxVolley, new Insets(10));
        BorderPane.setMargin(vboxNatation, new Insets(10));

        this.setCenter(main);

    }

    public JeuxOlympiques getModele() {
        return this.modele;
    }

    public Utilisateur getUtilisateur() {
        return this.user;
    }

    public RoleConnexion getUserRole() {
        return this.user.getRole();
    }

    public void afficheAccueil() {
        this.login.getMain().getScene().setRoot(this);

        navigation = new Navigation(this, this.modele);
        this.setTop(navigation);
    }

    public void afficheRecherche() {
        this.login.getMain().getScene().setRoot(new Rechercher(this.navigation, this.login.getMain()));

    }

    public void afficherAjout() {
        this.login.getMain().getScene().setRoot(new Ajouter(this.navigation, this.login.getMain()));
    }

    public void afficherUsers() {
        this.login.getMain().getScene().setRoot(new UtilisateursPane(this.navigation, this.user));
    }

    public void afficherConsultation() {
        this.login.getMain().getScene().setRoot(new ConsulterPane(this.navigation, this.login.getMain()));
    }

    public void afficherMain() {
        this.login.getMain().afficherAccueil();
    }

    public void afficherLancer() {
        this.login.getMain().getScene().setRoot(new Lancer(this.navigation, this.login.getMain()));
    }

}