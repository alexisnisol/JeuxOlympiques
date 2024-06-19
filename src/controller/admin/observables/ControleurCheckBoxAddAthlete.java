package controller.admin.observables;

import vue.administrateur.Ajouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modele.participants.Equipe;

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

        if(newValue){
            this.pane.getEquipesBox().getItems().clear();
            boolean equipePresente = false;
            for(Equipe equipe : this.pane.getModele().obtenirEquipes()){
                if (equipe.obtenirPays().getNom().equals(this.pane.getPays().getText())) {
                    this.pane.getEquipesBox().getItems().add(equipe);
                    equipePresente = true;
                }
            }
            if(!equipePresente){
                this.pane.getAjouterAthlete().setDisable(true);
            }else{

                this.pane.getAjouterAthlete().setDisable(false);
            }
        }
    }
}
