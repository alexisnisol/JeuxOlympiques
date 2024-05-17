package IHM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Vue extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        // Add your UI components to the root pane

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX View");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}