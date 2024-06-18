package vue.accueil.accueilbase;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modele.Classement;
import modele.JeuxOlympiques;
import modele.Pays;
import vue.Recherche.Rechercher;
import vue.accueil.Login;
import vue.accueil.Navigation;
import vue.administrateur.Ajouter;
import vue.journaliste.ConsulterPane;
import vue.journaliste.Medailles;

import java.util.Map;

import BD.RequetesJDBC.RoleConnexion;
import controller.ControleurNavigation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class principale extends BorderPane {

    private Login login;
    private JeuxOlympiques modele;
    private Navigation navigation;
    private RoleConnexion userRole;

    public principale(Login login, JeuxOlympiques modele) {

        this.login = login;
        this.modele = this.login.getMain().getModele();

        this.setStyle("-fx-background-color: #FFFFFF;");

        navigation = new Navigation(this, this.modele);
        this.setTop(navigation);

        // centre de la fenetre

        ImageView volley = new ImageView(new Image("file:assets/img/volley.jpg"));
        volley.setFitHeight(300);
        volley.setFitWidth(450);
        BorderPane.setAlignment(volley, Pos.CENTER);
        BorderPane.setMargin(volley, new Insets(10, 0, 0, 50));
        this.setLeft(volley);
        String longText = "Une performance encourageante pour les Bleus à l'approche des Jeux Olympiques de Paris 2024. Les Tricolores, médaillés d'or à Tokyo, ont vaincu l'Italie, championen du monde";
        TextFlow text = new TextFlow(new Text(longText));
        BorderPane.setMargin(text, new Insets(10));
        text.setStyle(
                "-fx-background-color: transparent;  -fx-border-color: transparent transparent transparent transparent;");
        text.setPrefSize(300, 100);
        BorderPane.setMargin(text, new Insets(50, 50, 0, 0));
        BorderPane.setAlignment(text, Pos.CENTER);
        this.setRight(text);
    }

    public JeuxOlympiques getModele() {
        return this.modele;
    }

    public RoleConnexion getUserRole() {
        return this.userRole;
    }

    public void afficheAccueil(RoleConnexion userRole) {
        this.userRole = userRole;
        this.login.getMain().getScene().setRoot(this);
        navigation = new Navigation(this, this.modele);
        this.setTop(navigation);
    }

    public void afficheRecherche() {
        this.login.getMain().getScene().setRoot(new Rechercher(this.navigation));

    }

    public void afficherAjout() {
        this.login.getMain().getScene().setRoot(new Ajouter(this.navigation));
    }

    public void afficherConsultation() {
        this.login.getMain().getScene().setRoot(new ConsulterPane(this.login.getMain(), this.navigation));
    }

    public void afficherMain() {
        this.login.getMain().afficherAccueil();
    }

}