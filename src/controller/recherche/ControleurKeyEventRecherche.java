package controller.recherche;

import vue.Recherche.Rechercher;
import vue.Recherche.Rechercher.TypeRecherche;
import vue.accueil.Main;

import java.util.List;
import java.util.NoSuchElementException;

import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class ControleurKeyEventRecherche implements EventHandler<KeyEvent> {

    private Rechercher rechercher;
    private Main main;

    public ControleurKeyEventRecherche(Rechercher rechercher, Main main) {
        this.rechercher = rechercher;
        this.main = main;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            try {
                Pair<TypeRecherche, List<String>> pairTypeData = main.getRequetesJDBC().search(
                        rechercher.getRecherche(),
                        main.getModele());

                switch (pairTypeData.getKey()) {
                    case ATHLETE:
                        rechercher.majParAthlete(pairTypeData.getValue());
                        break;

                    default:
                        rechercher.majParPays(pairTypeData.getValue());
                        break;
                }
            } catch (NoSuchElementException exception) {
                rechercher.getPopup(AlertType.ERROR, "Aucun résultat trouvé").show();
                ;
            }
        }
    }

}