package vue.accueil;

import BD.RequetesJDBC.RoleConnexion;
import controller.ControleurNavigation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modele.JeuxOlympiques;
import vue.accueil.accueilbase.Actualite;

public class Navigation extends BorderPane {

    private Actualite accueil;
    private JeuxOlympiques modele;

    private HBox menu;

    public Navigation(Actualite accueil, JeuxOlympiques modele) {
        this.accueil = accueil;
        this.modele = modele;
        ImageView logo_img1 = new ImageView(new Image("file:assets/img/jo_paris.png"));
        ImageView logo_img2 = new ImageView(new Image("file:assets/img/loupe_recherche.png"));
        Button rechercheButton = new Button();
        rechercheButton.setTooltip(new Tooltip("Rechercher"));
        rechercheButton.setGraphic(logo_img2);
        rechercheButton.setId("nav-loupe");
        rechercheButton.setOnAction(new ControleurNavigation(accueil, modele, NavAction.RECHERCHE));
        logo_img1.setFitHeight(100);
        logo_img1.setFitWidth(125);
        Button accueilButton = new Button();
        accueilButton.setTooltip(new Tooltip("Accueil"));
        accueilButton.setOnAction(new ControleurNavigation(accueil, modele, NavAction.ACCUEIL));
        accueilButton.setGraphic(logo_img1);
        accueilButton.setId("nav-loupe");
        logo_img2.setFitHeight(50);
        logo_img2.setFitWidth(50);
        this.setLeft(accueilButton);
        ImageView decoimg = new ImageView(new Image("file:assets/img/icon.png"));
        decoimg.setFitHeight(50);
        decoimg.setFitWidth(50);
        Button deco = new Button();
        deco.setTooltip(new Tooltip("Déconnexion"));
        deco.setGraphic(decoimg);
        deco.setOnAction(new ControleurNavigation(accueil, modele, NavAction.DECONNEXION));
        deco.setId("nav-loupe");
        HBox hbox_deco_recherche = new HBox();
        hbox_deco_recherche.getChildren().addAll(rechercheButton, deco);
        hbox_deco_recherche.setSpacing(10);
        hbox_deco_recherche.setAlignment(Pos.CENTER);
        this.setRight(hbox_deco_recherche);
        BorderPane.setAlignment(rechercheButton, Pos.CENTER);
        BorderPane.setAlignment(deco, Pos.CENTER);
        HBox barre = new HBox();
        barre.setPrefSize(900, 5);
        barre.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue, red);");
        this.setBottom(barre);

        menu = new HBox(10);
        menu.setAlignment(Pos.CENTER_LEFT);
        Button consulter = new Button("CONSULTER");
        consulter.setOnAction(new ControleurNavigation(accueil, modele, NavAction.CONSULTER));
        consulter.setPrefSize(150, 50);
        consulter.setId("navbar-btn");
        menu.getChildren().add(consulter);
        this.setCenter(menu);

        BorderPane.setAlignment(consulter, Pos.CENTER_LEFT);
        this.setPrefSize(900, 20);

        if (accueil.getUserRole() == RoleConnexion.ADMINISTRATEUR) {
            majAdmin();
        }

    }

    public enum NavAction {
        ACCUEIL,
        RECHERCHE,
        CONSULTER,
        DECONNEXION,
        AJOUTER,
        ROLE
    }

    public void majAdmin() {
        Button ajouter = new Button("AJOUTER");
        ajouter.setOnAction(new ControleurNavigation(accueil, modele, NavAction.AJOUTER));
        ajouter.setPrefSize(150, 50);
        ajouter.setId("navbar-btn");
        menu.getChildren().add(ajouter);
        Button role = new Button("ROLE");
        role.setOnAction(new ControleurNavigation(accueil, modele, NavAction.ROLE));
        role.setPrefSize(150, 50);
        role.setId("navbar-btn");
        menu.getChildren().add(role);
    }

}
