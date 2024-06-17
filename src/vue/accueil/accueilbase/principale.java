package vue.accueil.accueilbase;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public class principale extends BorderPane {


    public  principale() {
        this.setStyle("-fx-background-color: #FFFFFF;");
        VBox top = new VBox();
        HBox top2 = new HBox();
        ImageView logo = new ImageView(new Image("file:assets/img/jo_paris.png"));
        ImageView logo2 = new ImageView(new Image("file:assets/img/jo_paris.png"));
        logo2.setFitHeight(125);
        logo2.setFitWidth(125);
        logo.setFitHeight(125);
        logo.setFitWidth(125);
        top2.getChildren().addAll(logo,logo2);
        top2.setAlignment(Pos.CENTER_RIGHT);
        this.setTop(top);
        HBox menu = new HBox();
        menu.setPrefSize(900,5);
        menu.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue, red);");
        top.getChildren().addAll(top2,menu);
        ImageView volley = new ImageView(new Image("file:assets/img/volley.jpg"));
        volley.setFitHeight(200);
        volley.setFitWidth(500);
        BorderPane.setAlignment(volley,Pos.CENTER);
        BorderPane.setMargin(volley, new Insets(0,0,0,50));
        this.setLeft(volley);
        TextArea text = new TextArea("gerzjgvezhfezfezfezfs");
        text.setStyle("-fx-background-color: #FFFFFF;");
        text.setPrefSize(300,300);
        text.setEditable(false);
        BorderPane.setAlignment(text,Pos.CENTER);
        BorderPane.setMargin(text, new Insets(50,50,50,0));
        this.setRight(text);

    


    }



    
}