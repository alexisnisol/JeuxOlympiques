package controller;

import vue.accueil.accueilbase.principale;
import java.sql.SQLException;
import BD.RequetesJDBC.RoleConnexion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import vue.accueil.Login;
import vue.accueil.Main;

public class ControleurLoginJDBC implements EventHandler<ActionEvent> {

    private Login login;
    private principale accueil;

    public ControleurLoginJDBC(Login login, principale accueil) {
        this.login = login;
        this.accueil = accueil;
    }

    @Override
    public void handle(ActionEvent event) {
        Main main = login.getMain();
        try {
            RoleConnexion connexion = main.getRequetesJDBC().connexion(login.getPseudo().getText(),
                    login.getMdp().getText());

            if (connexion == RoleConnexion.INVALIDE) {
                main.getPopup(Alert.AlertType.ERROR, "Le Mot de passe est incorrect !").show();
            } else {
                accueil.afficheAccueil(connexion);

                this.login.resetTF();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}