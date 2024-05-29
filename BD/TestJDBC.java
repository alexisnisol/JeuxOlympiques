
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;

//javac --source-path ./src -d ./bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls src/TestJDBC.java
//java -cp ./bin:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls TestJDBC

public class TestJDBC extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private LoginBD loginBD;
    private FicheJoueur ficheJoueur;
    private JoueurBD joueurBD;
    private ConnexionMySQL connexionMySQL;
    private Scene scene;
    private MenuApplication menuApplication;
    private Label message;
    private FicheResultat ficheResultat;
    private VBox pMessage;

    public void init(){
        try {
            this.connexionMySQL = new ConnexionMySQL();
        }catch (ClassNotFoundException ex){
            System.out.println("Driver MySQL non trouvé!!!");
            System.exit(1);
        }
        this.loginBD=new LoginBD(this);
        this.ficheJoueur=new FicheJoueur(this);
        this.ficheResultat=new FicheResultat();
        this.joueurBD=new JoueurBD(this.connexionMySQL);
        this.message=new Label("Vous n'êtes pas connecté");
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        this.pMessage= new VBox();
        this.pMessage.setPadding(new Insets(5));
        pMessage.getChildren().add(message);
       
    }
    @Override
    public void start(Stage primaryStage) {
        VBox fp=new VBox(5);
        fp.setAlignment(Pos.TOP_CENTER);
        menuApplication=new MenuApplication(this);
        fp.getChildren().addAll(this.menuApplication,message);
        this.scene= new Scene(fp,500,500);
        primaryStage.setScene(this.scene);
        primaryStage.setTitle("Test JDBC");
        primaryStage.show();
    }

    public LoginBD getLoginBD() {
        return loginBD;
    }

    public void connexionReussie(){
        this.message.setText("Vous êtes connecté");

        VBox fp=((VBox)scene.getRoot());
        fp.getChildren().remove(1);
        fp.getChildren().addAll(this.message);
        this.menuApplication.connecter();
    }

    public void deconnexionReussie(){
        this.message.setText("Vous êtes déconnecté");
        
        scene.setRoot(pMessage);VBox fp=((VBox)scene.getRoot());
        fp.getChildren().remove(1);
        fp.getChildren().addAll(this.message);
        this.menuApplication.deconnecter();
    }
    public void showFenetreConnexion(){
        VBox fp=((VBox)scene.getRoot());
        fp.getChildren().remove(1);
        fp.getChildren().addAll(this.loginBD);
        
    }
    public void showFicheJoueur(){
        VBox fp=((VBox)scene.getRoot());
        fp.getChildren().remove(1);
        fp.getChildren().addAll(this.ficheJoueur);
        
    }
    public void showFicheResultat(String resultat){
        this.ficheResultat.setTexte(resultat);
        VBox fp=((VBox)scene.getRoot());
        fp.getChildren().remove(1);
        fp.getChildren().addAll(this.ficheResultat);
        
    }
    public void setMessage(String message){
        this.message.setText(message);
        VBox fp=((VBox)scene.getRoot());
        fp.getChildren().remove(1);
        fp.getChildren().addAll(this.message);
	
    }

    public ConnexionMySQL getConnexionMySQL() {
        return connexionMySQL;
    }

    public JoueurBD getJoueurBD() {
        return joueurBD;
    }

    public FicheJoueur getFicheJoueur() {
        return ficheJoueur;
    }
}
