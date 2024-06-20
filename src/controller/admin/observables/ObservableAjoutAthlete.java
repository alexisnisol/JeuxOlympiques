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

    /**
     * Observable qui permet de changer l'affichage des ComboBox en fonction du Sexe sélectionné
     * Si le Sexe est sélectionné, on affiche les ComboBox pour les épreuves individuelles pour l'athlète à ajouter
     * Sinon, on cache les ComboBox pour les épreuves individuelles
     * @param observable ObservableValue<Sexe> : la valeur du Sexe
     * @param oldValue Sexe : le Sexe précédent
     * @param newValue Sexe : le Sexe sélectionné
     */
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
