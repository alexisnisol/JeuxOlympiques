package vue.accueil;

import BD.RequetesJDBC;
import BD.server.LoginBD;
import controller.ControleurAccueil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{
    
    private Stage primaryStage;
    private BorderPane root;

    private Login loginPane;
    private Register registerPane;
    private LoginBD loginBD;
    private RequetesJDBC requetesJDBC;

    public void accueil(){
        
        Label titre = new Label("Bienvenue dans les Jeux IUT’Olympiques");
        titre.setAlignment(Pos.CENTER);
        titre.setStyle("-fx-font-size: 30px;");
        BorderPane.setAlignment(titre, Pos.CENTER);
        titre.setPadding(new Insets(25, 0, 25, 0));
        root.setTop(titre);
        ImageView image = new ImageView("./assets/img/accueil.png");
        image.setFitHeight(250);
        image.setFitWidth(500);
        root.setCenter(image);
        HBox menu = new HBox();
        Button inscrire = new Button("S'inscrire");
        Button connexion = new Button("Se connecter");
        inscrire.setStyle("-fx-font-size: 20px; -fx-background-color: #0781FE; -fx-text-fill: #ffffff;");
        connexion.setStyle("-fx-font-size: 20px; -fx-background-color: #000000; -fx-text-fill: #ffffff;");
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
    
    public void afficherAccueil(){
        accueil();
        this.primaryStage.getScene().setRoot(root);
    }

    public void afficherLoginBD(){
        this.primaryStage.getScene().setRoot(this.loginBD);
    }

    public void afficherlogin(){
        this.primaryStage.getScene().setRoot(this.loginPane);
    }

    public void afficherInscription(){
        this.primaryStage.getScene().setRoot(this.registerPane);
    }


	@Override
	public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.root = new BorderPane();
        this.loginPane = new Login(this);
        this.registerPane = new Register(this);
        this.loginBD = new LoginBD(this);

        this.requetesJDBC = new RequetesJDBC(this.loginBD.getConnexionMySQL());

        Scene scene = new Scene(root, 900, 550);
        
        primaryStage.setTitle("Jeux IUT'Olympiques");
        primaryStage.setScene(scene);
        primaryStage.show();
        afficherLoginBD();
        
    }



    public RequetesJDBC getRequetesJDBC(){
        return this.requetesJDBC;
    }

    /**
     * Affiche une fenêtre popup pour demander si l'utilisateur veut vraiment quitter
     * @return la fenêtre popup
     */
    public Alert getPopupIncorrect(String info){
        Alert alert = new Alert(Alert.AlertType.ERROR,"Le " + info + " est incorrect !", ButtonType.OK);
        alert.setTitle("Authentification");
        return alert;
    }
    
    public static void main(String[] args) {
        launch(args);
	}

    public enum ButtonAction{
        LOGIN_PANE,
        REGISTER_PANE,
        CONNEXION,
        INSCRIPTION
    }
}

