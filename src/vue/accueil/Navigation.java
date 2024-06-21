package vue.accueil;

import bd.server.RequetesJDBC.RoleConnexion;
import controller.accueil.ControleurNavigation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * La classe Navigation représente la barre de navigation de l'application.
 * Elle hérite de la classe BorderPane.
 */
public class Navigation extends BorderPane {

    private Actualite accueil;

    private HBox menu;

    /**
     * Constructeur de la classe Navigation.
     * 
     * @param accueil L'objet Actualite représentant la page d'accueil.
     * @param modele  L'objet JeuxOlympiques représentant le modèle de
     *                l'application.
     */
    public Navigation(Actualite accueil) {
        this.accueil = accueil;

        // Création des images pour les boutons de recherche et d'accueil
        ImageView logo_img1 = new ImageView(new Image("file:assets/img/jo_paris.png"));
        ImageView logo_img2 = new ImageView(new Image("file:assets/img/loupe_recherche.png"));

        // Création du bouton de recherche
        Button rechercheButton = new Button();
        rechercheButton.setTooltip(new Tooltip("Rechercher"));
        rechercheButton.setGraphic(logo_img2);
        rechercheButton.setId("nav-loupe");
        rechercheButton.setOnAction(new ControleurNavigation(accueil, NavAction.RECHERCHE));

        // Configuration de l'image du logo d'accueil
        logo_img1.setFitHeight(100);
        logo_img1.setFitWidth(125);

        // Création du bouton d'accueil
        Button accueilButton = new Button();
        accueilButton.setTooltip(new Tooltip("Accueil"));
        accueilButton.setOnAction(new ControleurNavigation(accueil, NavAction.ACCUEIL));
        accueilButton.setGraphic(logo_img1);
        accueilButton.setId("nav-loupe");

        // Configuration de l'image de la loupe de recherche
        logo_img2.setFitHeight(50);
        logo_img2.setFitWidth(50);

        // Ajout du bouton d'accueil à la partie gauche de la barre de navigation
        this.setLeft(accueilButton);

        // Configuration de l'image de déconnexion
        ImageView decoimg = new ImageView(new Image("file:assets/img/icon.png"));
        decoimg.setFitHeight(50);
        decoimg.setFitWidth(50);

        // Création du bouton de déconnexion
        Button deco = new Button();
        deco.setTooltip(new Tooltip("Déconnexion"));
        deco.setGraphic(decoimg);
        deco.setOnAction(new ControleurNavigation(accueil, NavAction.DECONNEXION));
        deco.setText("Déconnexion");
        deco.setId("nav-loupe");

        // Création d'une HBox pour regrouper le bouton de recherche et le bouton de
        // déconnexion
        HBox hbox_deco_recherche = new HBox();
        hbox_deco_recherche.getChildren().addAll(rechercheButton, deco);
        hbox_deco_recherche.setSpacing(10);
        hbox_deco_recherche.setAlignment(Pos.CENTER);

        // Ajout de la HBox à la partie droite de la barre de navigation
        this.setRight(hbox_deco_recherche);

        // Configuration de l'alignement des boutons de recherche et de déconnexion
        BorderPane.setAlignment(rechercheButton, Pos.CENTER);
        BorderPane.setAlignment(deco, Pos.CENTER);

        // Création de la barre de séparation en bas de la barre de navigation
        HBox barre = new HBox();
        barre.setPrefSize(900, 5);
        barre.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue, red);");
        this.setBottom(barre);

        // Création du menu de navigation
        menu = new HBox(10);
        menu.setAlignment(Pos.CENTER_LEFT);

        // Création du bouton "CONSULTER"
        Button consulter = new Button("CONSULTER");
        consulter.setOnAction(new ControleurNavigation(accueil, NavAction.CONSULTER));
        consulter.setPrefSize(150, 50);
        consulter.setId("navbar-btn");
        menu.getChildren().add(consulter);

        // Ajout du menu de navigation à la partie centrale de la barre de navigation
        this.setCenter(menu);

        // Configuration de l'alignement du bouton "CONSULTER"
        BorderPane.setAlignment(consulter, Pos.CENTER_LEFT);
        this.setPrefSize(900, 20);

        // Mise à jour du menu en fonction du rôle de l'utilisateur
        if (accueil.getUserRole() == RoleConnexion.ADMINISTRATEUR) {
            majAdmin();
        }
        if (accueil.getUserRole() == RoleConnexion.ORGANISATEUR) {
            majOrga();
        }
    }

    /**
     * Enumération des actions de navigation possibles.
     */
    public enum NavAction {
        ACCUEIL,
        RECHERCHE,
        CONSULTER,
        DECONNEXION,
        ADMIN_AJOUTER,
        ADMIN_USERS,
        ORGANISATEUR_LANCER
    }

    /**
     * Met à jour le menu de navigation pour un administrateur.
     */
    public void majAdmin() {
        // Création du bouton "AJOUTER"
        Button ajouter = new Button("AJOUTER");
        ajouter.setOnAction(new ControleurNavigation(accueil, NavAction.ADMIN_AJOUTER));
        ajouter.setPrefSize(125, 50);
        ajouter.setId("navbar-btn");
        menu.getChildren().add(ajouter);

        // Création du bouton "UTILISATEURS"
        Button users = new Button("UTILISATEURS");
        users.setOnAction(new ControleurNavigation(accueil, NavAction.ADMIN_USERS));
        users.setPrefSize(200, 50);
        users.setId("navbar-btn");
        menu.getChildren().add(users);
    }

    /**
     * Met à jour le menu de navigation pour un organisateur.
     */
    public void majOrga() {
        // Création du bouton "LANCER"
        Button lancer = new Button("LANCER");
        lancer.setOnAction(new ControleurNavigation(accueil, NavAction.ORGANISATEUR_LANCER));
        lancer.setPrefSize(150, 50);
        lancer.setId("navbar-btn");
        menu.getChildren().add(lancer);
    }

}