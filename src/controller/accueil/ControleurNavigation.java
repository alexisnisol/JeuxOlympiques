package controller.accueil;

import vue.accueil.Actualite;
import vue.accueil.Navigation.NavAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurNavigation implements EventHandler<ActionEvent> {

    private Actualite accueil;
    private NavAction action;

    public ControleurNavigation(Actualite accueil, NavAction action) {
        this.accueil = accueil;
        this.action = action;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (action) {
            case CONSULTER:
                this.accueil.afficherConsultation();
                break;

            case ACCUEIL:
                this.accueil.afficheAccueil();
                break;

            case DECONNEXION:
                this.accueil.afficherMain();
                break;

            case ADMIN_AJOUTER:
                this.accueil.afficherAjout();
                break;
            case ADMIN_USERS:
                this.accueil.afficherUsers();
                break;
            default:
                this.accueil.afficheRecherche();
                break;
        }

    }
}