package controller.recherche;

import vue.Recherche.Rechercher;
import vue.Recherche.Rechercher.TypeRecherche;
import vue.accueil.Main;

import java.util.List;
import java.util.NoSuchElementException;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Pair;

public class ControleurActionRecherche implements EventHandler<ActionEvent> {

    private Rechercher rechercher;
    private Main main;

    public ControleurActionRecherche(Rechercher rechercher, Main main) {
        this.rechercher = rechercher;
        this.main = main;
    }

    @Override
    public void handle(ActionEvent event) {
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