package BD.server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import vue.accueil.Main;
import vue.accueil.Main.ButtonAction;

import java.sql.SQLException;

import BD.ConnexionMySQL;

public class ControleurSQL implements EventHandler<ActionEvent>{
    private LoginBD loginBD;

    public ControleurSQL(LoginBD loginBD) {
        this.loginBD = loginBD;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        ConnexionMySQL connexionMySQL = loginBD.getConnexionMySQL();

        try {
            connexionMySQL.connecter(loginBD.getNomServeur(), loginBD.getNomBD(), loginBD.getLogin(), loginBD.getMotDePasse());
            if (connexionMySQL.isConnecte()) {
                loginBD.pageSuivante();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Echec !!!! ");
			alert.setResizable(true);
			alert.setWidth(500);
			alert.setHeaderText("Echec de la connexion au serveur");
			alert.setContentText("Voici le message envoyé par le serveur\n"+e.getMessage());
			alert.showAndWait();
        }
    }
}
