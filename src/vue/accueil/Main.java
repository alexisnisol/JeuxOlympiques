package vue.accueil;

import java.sql.SQLException;
import java.util.List;

import bd.server.RequetesJDBC;
import controller.accueil.ControleurAccueil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modele.JeuxOlympiques;

public class Main extends Application {

    private Scene scene;
    private BorderPane root;

    private Login loginPane;
    private Register registerPane;
    private LoginBD loginBD;
    private JeuxOlympiques modele;
    private Stage stage;

    /**
     * Constructeur de la classe Main
     */

    public void accueil() {

        // On initialise la connexion à la base de données
        RequetesJDBC.laConnexion = this.loginBD.getConnexionMySQL();

        // List<List<String>> liste = JeuxOlympiques.fromCsv("src/donnees.csv");
        // this.modele = JeuxOlympiques.convertFromArrayCsv(2024, liste);


        // On récupère les données de la base de données

        try {
            this.modele = JeuxOlympiques.convertFromBD();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // On affiche l'accueil
        Label titre = new Label("Bienvenue dans les Jeux IUT’Olympiques");
        titre.setAlignment(Pos.CENTER);
        titre.setStyle("-fx-font-size: 30px;");
        titre.setPadding(new Insets(25, 0, 25, 0));
        BorderPane.setAlignment(titre, Pos.CENTER);
        root.setTop(titre);

        ImageView image = new ImageView("./assets/img/accueil_iut.png");
        image.setFitHeight(250);
        image.setFitWidth(500);
        root.setCenter(image);

        HBox menu = new HBox();
        Button inscrire = new Button("S'inscrire");
        Button connexion = new Button("Se connecter");
        inscrire.setStyle("-fx-font-size: 20px; -fx-background-color: #0781FE; -fx-text-fill: #ffffff;");
        inscrire.setCursor(Cursor.HAND);
        connexion.setStyle("-fx-font-size: 20px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
        connexion.setCursor(Cursor.HAND);
        inscrire.setPrefSize(175, 50);
        connexion.setPrefSize(175, 50);
        inscrire.setAlignment(Pos.CENTER);
        inscrire.setOnAction(new ControleurAccueil(this, ButtonAction.REGISTER_PANE));
        connexion.setOnAction(new ControleurAccueil(this, ButtonAction.LOGIN_PANE));
        menu.setSpacing(25);
        double sizeMenu = inscrire.getPrefWidth() + connexion.getPrefWidth() + menu.getSpacing();
        menu.setMaxWidth(sizeMenu);
        menu.getChildren().addAll(inscrire, connexion);
        BorderPane.setMargin(menu, new Insets(25, 0, 25, 0));
        BorderPane.setAlignment(menu, Pos.CENTER);
        menu.setAlignment(Pos.TOP_LEFT);
        root.setBottom(menu);
        root.setStyle("-fx-background-color: #ffffff;");
    }

    /**
     * Affiche l'accueil
     */

    public void afficherAccueil() {
        this.stage.setScene(scene);
        accueil();
        this.scene.setRoot(root);
    }

    /**
     * Affiche la page de connexion à la base de données
     */
    public void afficherLoginBD() {
        Scene scene = new Scene(this.loginBD, 300, 400);
        this.stage.setScene(scene);
        scene.setRoot(loginBD);
    }
    
    /**
     * Affiche la page de connexion
     */
    public void afficherlogin() {
        this.scene.setRoot(this.loginPane);

    }

    /**
     * Affiche la page d'inscription
     */
    public void afficherInscription() {
        this.scene.setRoot(this.registerPane);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.root = new BorderPane();

        this.loginBD = new LoginBD(this);

        this.scene = new Scene(root, 900, 550);
        this.stage.getIcons().add(new ImageView("file:assets/img/jo_paris.png").getImage());
        this.loginPane = new Login(this, this.modele);
        this.registerPane = new Register(this);

        scene.getStylesheets().add("file:assets/css/styles.css");

        stage.setTitle("Jeux IUT'Olympiques");
        stage.setScene(scene);
        stage.show();
        afficherLoginBD();

    }

    /**
     * Permet de definir le modele
     * 
     * @param pane la page à afficher
     */
    public void setModele(JeuxOlympiques modele) {
        this.modele = modele;
    }

    /**
     * Permet de récupérer le modèle
     * 
     * @return le modèle
     */
    public JeuxOlympiques getModele() {
        return this.modele;
    }

    /**
     * Affiche une fenêtre popup lié à l'authentification
     * 
     * @return la fenêtre popup
     */
    public Alert getPopup(AlertType type, String text) {
        Alert alert = new Alert(type, text, ButtonType.OK);
        alert.setTitle("Authentification");
        return alert;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Enumération des actions possibles pour les boutons de l'accueil.
     */
    public enum ButtonAction {
        LOGIN_PANE,
        REGISTER_PANE,
        CONNEXION,
        INSCRIPTION
    }


    /**
     * Permet de récupérer la scène
     * 
     * @return la scène
     */
    public Scene getScene() {
        return this.scene;
    }

}
