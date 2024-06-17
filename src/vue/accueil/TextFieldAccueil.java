package vue.accueil;

import javafx.scene.control.TextField;

public class TextFieldAccueil extends TextField{

    public TextFieldAccueil(String promptText){
        super();
        this.setPrefSize(433, 59);
        this.setStyle("-fx-background-color: #414246; -fx-text-fill: white; -fx-padding: 10; -fx-font-weight: bold;");
        this.setPromptText(promptText);
    }
    
}
