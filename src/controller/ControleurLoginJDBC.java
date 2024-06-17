package controller;

import java.sql.SQLException;
import java.util.Optional;

import BD.RequetesJDBC.RoleConnexion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import vue.accueil.Login;
import vue.accueil.Main;
import vue.accueil.Main.ButtonAction;

public class ControleurLoginJDBC implements EventHandler<ActionEvent>{

    private Login login;
    
    public ControleurLoginJDBC(Login login) {
        this.login = login;
    }

    @Override
    public void handle(ActionEvent event) {
        Main main = login.getMain();
        try {
            RoleConnexion connexion = main.getRequetesJDBC().connexion(login.getPseudo().getText(), login.getMdp().getText());

            if(connexion == RoleConnexion.INVALIDE){
                main.getPopup(Alert.AlertType.ERROR, "Le Mot de passe est incorrect !").show();
            }else{
                switch (connexion) {
                    case JOURNALISTE:
                        break;
                
                    default:
                        break;
                }
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}