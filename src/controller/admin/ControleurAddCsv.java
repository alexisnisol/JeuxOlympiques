package controller.admin;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import modele.JeuxOlympiques;
import vue.administrateur.Ajouter;

public class ControleurAddCsv implements EventHandler<ActionEvent> {

    private Ajouter ajout;

    public ControleurAddCsv(Ajouter ajout) {
        this.ajout = ajout;
    }

    /**
     * Ouvre une fenêtre de dialogue pour choisir un fichier csv, puis crée les JO correspondants
     */
    @Override
    public void handle(ActionEvent event) {

        //Affichage d'un fileChooser pour choisir un fichier csv, et remplie le modèle avec celui-ci
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(ajout.getScene().getWindow());
        if (selectedFile != null) {
            
            this.ajout.resetTF();
        
            ajout.addPopup("Création des JO via csv effectué !").show();
            ajout.getMain().setModele(JeuxOlympiques.convertFromArrayCsv(2024, JeuxOlympiques.fromCsv(selectedFile.getPath())));
        }
    }
}
