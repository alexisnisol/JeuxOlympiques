package controller;

import vue.accueil.Navigation.NavAction;
import vue.accueil.accueilbase.Actualite;
import vue.administrateur.Ajouter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.JeuxOlympiques;

public class ControleurNavigation implements EventHandler<ActionEvent> {

    private Actualite accueil;
    private JeuxOlympiques modele;
    private NavAction action;

    public ControleurNavigation(Actualite accueil, JeuxOlympiques modele, NavAction action) {
        this.accueil = accueil;
        this.modele = modele;
        this.action = action;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (action) {
            case CONSULTER:
                this.accueil.afficherConsultation();
                break;

            case ACCUEIL:
                this.accueil.afficheAccueil(accueil.getUserRole());
                break;

            case DECONNEXION:
                this.accueil.afficherMain();
                break;

            case AJOUTER:
                this.accueil.afficherAjout();
                break;

            case ROLE:
                this.accueil.afficherRole();
                break;
            default:
                this.accueil.afficheRecherche();
                break;
        }

    }
}