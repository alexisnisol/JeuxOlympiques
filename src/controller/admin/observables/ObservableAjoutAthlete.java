package controller.admin.observables;

import modele.Sexe;
import modele.competitions.Competition;
import modele.competitions.CompetitionIndividuelle;
import vue.administrateur.Ajouter;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

public class ObservableAjoutAthlete implements ChangeListener<Sexe> {
    private Ajouter pane;

    public ObservableAjoutAthlete(Ajouter pane) {
        this.pane = pane;
    }

    @Override
    public void changed(ObservableValue<? extends Sexe> observable, Sexe oldValue, Sexe newValue) {
        if (oldValue == null || !oldValue.equals(newValue)) {
            this.pane.getEpreuvesAthletesBox().getItems().clear();
            for (Competition c : pane.getModele().getLesCompetitions()) {
                if (c instanceof CompetitionIndividuelle && c.getSexe() == pane.getSexe()) {
                    this.pane.getEpreuvesAthletesBox().getItems().add(c);
                }
            }
        }
    }
}
