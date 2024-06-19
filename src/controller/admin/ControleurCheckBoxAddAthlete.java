package controller.admin;

import vue.Recherche.Rechercher;
import vue.administrateur.Ajouter;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

public class ControleurCheckBoxAddAthlete implements ChangeListener<Boolean> {
    private Ajouter pane;

    public ControleurCheckBoxAddAthlete(Ajouter pane) {
        this.pane = pane;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        this.pane.getEpreuveBox().setVisible(oldValue);
        this.pane.getEpreuveBox().setValue(null);

        this.pane.getEquipe().setVisible(newValue);
        this.pane.getEquipe().setText("");
    }
}
