package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.JeuxOlympiques;

public class ControleurAjouter implements EventHandler<ActionEvent> {

    private JeuxOlympiques modele;

    public ControleurAjouter(JeuxOlympiques modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent event) {

    }

}