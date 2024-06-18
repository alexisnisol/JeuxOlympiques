package controller;

import vue.Recherche.Rechercher;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControleurKeyEventRecherche implements EventHandler<KeyEvent> {

    private Rechercher rechercher;

    public ControleurKeyEventRecherche(Rechercher rechercher) {
        this.rechercher = rechercher;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            rechercher.majAffichage();
        }
    }

}