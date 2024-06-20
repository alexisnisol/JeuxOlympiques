package controller;

import vue.journaliste.ConsulterPane;
import vue.journaliste.ConsulterPane.ConsultAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.JeuxOlympiques;

public class ControleurConsultation implements EventHandler<ActionEvent> {

    private ConsulterPane pane;
    private JeuxOlympiques modele;
    private ConsultAction action;

    public ControleurConsultation(ConsulterPane pane, JeuxOlympiques modele, ConsultAction action) {
        this.pane = pane;
        this.modele = modele;
        this.action = action;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (action) {
            case MEDAILLES:
                this.pane.afficherMedailles(this.modele.classementMedailles());
                break;

            case COMPETITIONS:
                this.pane.afficherCompetitions(this.modele.getLesCompetitions());
                break;

            default:
                break;
        }

    }
}