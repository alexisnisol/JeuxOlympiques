package vue.administrateur;

import BD.RequetesJDBC;
import BD.server.LoginBD;
import controller.ControleurAccueil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TestRole extends Application {

    public void accueil() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 900, 550);

        stage.setTitle("Jeux IUT'Olympiques");
        stage.setScene(scene);
        stage.show();

        scene.setRoot(new Role());
    }

    public static void main(String[] args) {
        launch(args);
    }

}