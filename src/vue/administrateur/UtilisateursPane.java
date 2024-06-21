package vue.administrateur;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import vue.accueil.Navigation;
import javafx.scene.layout.GridPane;
import controller.admin.observables.ControleurChangeRole;

import java.sql.SQLException;

import bd.server.RequetesJDBC;
import bd.server.Utilisateur;
import bd.server.RequetesJDBC.RoleConnexion;

public class UtilisateursPane extends BorderPane {

    private Utilisateur user;
    private ComboBox<RoleConnexion> roleUser;

    /**
     * Constructeur de la classe UtilisateursPane, 
     * qui affiche la liste des utilisateurs de l'application et leur rôle, 
     * et permet de changer le rôle des utilisateurs en tant qu'administrateur
     * @param navigation
     * @param user
     */
    public UtilisateursPane(Navigation navigation, Utilisateur user) {

        this.user = user;
        this.setStyle("-fx-background-color: #FFFFFF;");
        // TOP
        this.setTop(navigation);

        // CENTER
        GridPane grid = new GridPane();
        // grid.setVgap(20);
        grid.setStyle("-fx-background-color: #D9D9D9;");
        // Configuration des colonnes
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        col1.setHgrow(Priority.ALWAYS);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);
        col2.setHgrow(Priority.ALWAYS);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        col3.setHgrow(Priority.ALWAYS);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(40);
        col4.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(col1, col2, col3, col4);
        grid.setGridLinesVisible(true);

        // Ajout du titre des colonnes
        Label nom = new Label("Nom");
        nom.setAlignment(Pos.CENTER);
        nom.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #AEA6A6; -fx-border-color: black; -fx-border-width: 1px;");
        nom.setPrefSize(800, 100);

        Label prenom = new Label("Prénom");
        prenom.setAlignment(Pos.CENTER);
        prenom.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #AEA6A6; -fx-border-color: black; -fx-border-width: 1px;");
        prenom.setPrefSize(800, 100);

        Label pseudo = new Label("Pseudo");
        pseudo.setAlignment(Pos.CENTER);
        pseudo.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #AEA6A6; -fx-border-color: black; -fx-border-width: 1px;");
        pseudo.setPrefSize(800, 100);

        Label role = new Label("Role");
        role.setAlignment(Pos.CENTER);
        role.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-color: #AEA6A6; -fx-border-color: black; -fx-border-width: 1px;");
        role.setPrefSize(800, 100);

        grid.add(nom, 0, 0);
        grid.add(prenom, 1, 0);
        grid.add(pseudo, 2, 0);
        grid.add(role, 3, 0);

        // Ajout des données

        int rowI = 1;
        try {
            for (Utilisateur utilisateur : RequetesJDBC.getUtilisateurs()) {

                Label nomUser = new Label(utilisateur.getNom() + "");
                nomUser.setPrefWidth(500);
                nomUser.setAlignment(Pos.CENTER);

                Label prenomUser = new Label(utilisateur.getPrenom() + "");
                prenomUser.setPrefWidth(500);
                prenomUser.setAlignment(Pos.CENTER);

                Label pseudoUser = new Label(utilisateur.getPseudo() + "");
                pseudoUser.setPrefWidth(500);
                pseudoUser.setAlignment(Pos.CENTER);
                
                this.roleUser = new ComboBox<RoleConnexion>();
                this.roleUser.setValue(utilisateur.getRole());
                this.roleUser.getItems().addAll(RoleConnexion.values());
                this.roleUser.valueProperty().addListener(new ControleurChangeRole(utilisateur));
                if(utilisateur.equals(user)){
                    roleUser.setDisable(true);
                    roleUser.setTooltip(new Tooltip("Vous ne pouvez pas changer votre propre rôle"));
                }
                roleUser.setPrefWidth(500);

                grid.add(nomUser, 0, rowI);
                grid.add(prenomUser, 1, rowI);
                grid.add(pseudoUser, 2, rowI);
                grid.add(roleUser, 3, rowI);
                rowI++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TitledPane tp = new TitledPane("Utilisateurs de l'application", grid);
        tp.setStyle("-fx-font-size: 18;");
        tp.setPadding(new Insets(20));
        tp.setCollapsible(false);
        tp.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(tp, Pos.BOTTOM_CENTER);
        ScrollPane scrollPane = new ScrollPane(tp);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
        this.setCenter(scrollPane);
    }

    /**
     * Méthode permettant de récupérer l'utilisateur
     * @return l'utilisateur
     */
    public Utilisateur getUtilisateur(){
        return this.user;
    }

    /**
     * Méthode permettant de récupérer le rôle de l'utilisateur
     * @return le rôle de l'utilisateur
     */
    public ComboBox<RoleConnexion> getRoleUser(){
        return this.roleUser;
    }
}