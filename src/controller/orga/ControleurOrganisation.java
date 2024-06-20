package controller.orga;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vue.organisateur.Lancer;

public class ControleurOrganisation implements EventHandler<ActionEvent> {

    private Lancer lancer;

    public ControleurOrganisation(Lancer lancer) {
        this.lancer = lancer;
    }

    @Override
    public void handle(ActionEvent event) {
        lancer.majAffichage();
        lancer.lancerJeuxOlympiques();
    }
}