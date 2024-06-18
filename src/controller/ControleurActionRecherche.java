package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vue.Recherche.Rechercher;

public class ControleurActionRecherche implements EventHandler<ActionEvent> {

    private Rechercher recherche;

    public ControleurActionRecherche(Rechercher recherche) {
        this.recherche = recherche;
    }

    @Override
    public void handle(ActionEvent event) {
        recherche.majAffichage();
    }
}