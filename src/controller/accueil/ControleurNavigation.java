package controller.accueil;

import vue.accueil.Actualite;
import vue.accueil.Navigation.NavAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Cette classe est responsable de la gestion des événements de navigation dans
 * l'application.
 * Elle implémente l'interface EventHandler<ActionEvent> pour pouvoir gérer les
 * événements de type ActionEvent.
 */
public class ControleurNavigation implements EventHandler<ActionEvent> {

    private Actualite accueil;
    private NavAction action;

    /**
     * Constructeur de la classe ControleurNavigation.
     * 
     * @param accueil L'objet Actualite qui représente la page d'accueil de
     *                l'application.
     * @param action  L'action de navigation à effectuer.
     */
    public ControleurNavigation(Actualite accueil, NavAction action) {
        this.accueil = accueil;
        this.action = action;
    }

    /**
     * Méthode qui gère l'événement de navigation.
     * Elle est appelée lorsque l'utilisateur effectue une action de navigation.
     * 
     * @param event L'événement de type ActionEvent déclenché par l'utilisateur.
     */
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

            case ORGANISATEUR_LANCER:
                this.accueil.afficherLancer();
                break;
            default:
                this.accueil.afficheRecherche();
                break;
        }
    }
}