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

    /**
     * Observable qui permet de changer l'affichage des ComboBox en fonction de la CheckBox sélectionnée
     * Si la CheckBox est sélectionnée, on affiche les ComboBox pour les équipes et on cache ceux pour les épreuves individuelles
     * Sinon, on affiche les ComboBox pour les épreuves individuelles et on cache ceux pour les équipes
     * Car si l'athlète est dans une équipe, il ne peut pas participer à une épreuve individuelle
     * @param observable ObservableValue<Boolean> : la valeur de la CheckBox
     * @param oldValue Boolean : true si la CheckBox était sélectionnée, false sinon
     * @param newValue Boolean : true si la CheckBox est sélectionnée, false sinon
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        this.pane.getEpreuvesAthletesBox().setVisible(oldValue);
        this.pane.getEpreuvesAthletesBox().setValue(null);

        this.pane.getEquipesBox().setVisible(newValue);
        this.pane.getEquipesBox().setValue(null);

        if(newValue){
            this.pane.getEquipesBox().getItems().clear();
            boolean equipePresente = false;
            //On récupère la liste des équipes du modèle
            for(Equipe equipe : this.pane.getModele().obtenirEquipes()){
                //On ajoute les équipes associé dans la ComboBox, du pays saisie
                if (equipe.obtenirPays().getNom().equals(this.pane.getPays().getText())) {
                    this.pane.getEquipesBox().getItems().add(equipe);
                    equipePresente = true;
                }
            }

            //Si le pays n'a pas d'équipe alors que l'athlète doit appartenir à une équipe, on désactive le bouton d'ajout d'athlète
            this.pane.getAjouterAthlete().setDisable(!equipePresente);
        }
    }
}
