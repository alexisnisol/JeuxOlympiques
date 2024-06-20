package controller.admin.observables;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import modele.participants.Equipe;
import vue.administrateur.Ajouter;

public class ControleurBonnesEquipes implements ChangeListener<String> {
    
    private Ajouter pane;

    public ControleurBonnesEquipes(Ajouter pane){
        this.pane = pane;
    }
    
    /**
     * Observable qui permet de mettre à jour la liste des équipes en fonction du pays saisie, 
     * et de désactiver le bouton d'ajout d'athlète si le pays n'a pas d'équipe
     */
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
    {   
        if(this.pane.athleteDansUneEquipe()){
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
}