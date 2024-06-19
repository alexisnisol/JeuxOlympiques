package controller.admin;

import vue.administrateur.Ajouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurCheckBoxAddAthlete implements ChangeListener<Boolean> {
    private Ajouter pane;

    public ControleurCheckBoxAddAthlete(Ajouter pane) {
        this.pane = pane;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        this.pane.getEpreuvesAthletesBox().setVisible(oldValue);
        this.pane.getEpreuvesAthletesBox().setValue(null);

        this.pane.getEquipesBox().setVisible(newValue);
        this.pane.getEquipesBox().setValue(null);
    }
}
