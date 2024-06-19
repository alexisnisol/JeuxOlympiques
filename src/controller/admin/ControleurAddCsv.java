package controller.admin;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import modele.JeuxOlympiques;
import vue.administrateur.Ajouter;

public class ControleurAddCsv implements EventHandler<ActionEvent> {

    private JeuxOlympiques modele;
    private Ajouter ajout;

    public ControleurAddCsv(JeuxOlympiques modele, Ajouter ajout) {
        this.modele = modele;
        this.ajout = ajout;
    }

    @Override
    public void handle(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(ajout.getScene().getWindow());
        if (selectedFile != null) {
            
            this.ajout.resetTF();
        
            ajout.addPopup("Création des JO via csv effectué !").show();
            ajout.getMain().setModele(JeuxOlympiques.convertFromArrayCsv(2024, JeuxOlympiques.fromCsv(selectedFile.getPath())));
            ajout.getMain().getScene().setCursor(Cursor.DEFAULT);
        }
    }
}
