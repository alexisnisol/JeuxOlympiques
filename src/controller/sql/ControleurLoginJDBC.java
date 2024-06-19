package controller.sql;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import bd.server.RequetesJDBC;
import bd.server.Utilisateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import vue.accueil.Login;
import vue.accueil.Main;

public class ControleurLoginJDBC implements EventHandler<ActionEvent> {

    private Login login;

    public ControleurLoginJDBC(Login login) {
        this.login = login;
    }

    @Override
    public void handle(ActionEvent event) {
        Main main = login.getMain();
        try {
            Utilisateur connexion = RequetesJDBC.connexion(login.getPseudo().getText(),
                    login.getMdp().getText());

            this.login.afficherAccueil(connexion);
            this.login.resetTF();

        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NoSuchElementException e1) {
            main.getPopup(Alert.AlertType.ERROR, "Le Mot de passe est incorrect !").show();
        }

    }
}