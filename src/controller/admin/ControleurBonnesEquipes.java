package controller.admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modele.participants.Equipe;
import vue.administrateur.Ajouter;

public class ControleurBonnesEquipes implements ChangeListener<String> {
    
    private Ajouter pane;

    public ControleurBonnesEquipes(Ajouter pane){
        this.pane = pane;
    }
    
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
    {   
        this.pane.getEquipesBox().getItems().clear();
        boolean equipePresente = false;
        for(Equipe equipe : this.pane.getModele().obtenirEquipes()){
            if (equipe.obtenirPays().getNom().equals(newValue)) {
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