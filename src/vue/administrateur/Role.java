package vue.administrateur;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import vue.accueil.Main;
import vue.accueil.Navigation;
import org.controlsfx.control.SearchableComboBox;

public class Role extends BorderPane {

    private SearchableComboBox<String> user;
    private Main mainPane;

    public Role() {
        this.user = new SearchableComboBox<>();
        this.user.getItems().addAll("Journaliste", "Administrateur");
        this.user.setPromptText("Sélectionner un rôle");
        this.setCenter(this.user);
    }

}
