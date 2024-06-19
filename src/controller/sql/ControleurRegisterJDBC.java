package controller.sql;

import java.sql.SQLException;

import bd.server.RequetesJDBC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import vue.accueil.Main;
import vue.accueil.Register;

public class ControleurRegisterJDBC implements EventHandler<ActionEvent> {

    private Register register;

    public ControleurRegisterJDBC(Register register) {
        this.register = register;
    }

    @Override
    public void handle(ActionEvent event) {
        Main main = register.getMain();
        try {
            boolean inscription = RequetesJDBC.inscription(register.getNom().getText(),
                    register.getPrenom().getText(), register.getPseudo().getText(), register.getMdp().getText());
            if (inscription) {
                this.register.resetTF();
                main.getPopup(Alert.AlertType.CONFIRMATION, "Inscription effectuée !").show();
                register.getMain().afficherlogin();
            } else {
                main.getPopup(Alert.AlertType.ERROR, "Le pseudo est déjà existant !").show();
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}