package controller.admin.observables;

import java.sql.SQLException;

import bd.server.RequetesJDBC;
import bd.server.Utilisateur;
import bd.server.RequetesJDBC.RoleConnexion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControleurChangeRole implements ChangeListener<RoleConnexion> {
    
    private Utilisateur user;

    public ControleurChangeRole(Utilisateur user){
        this.user = user;
    }
    
    /**
     * Observable qui permet de mettre à jour le rôle de l'utilisateur du ComboBox pour la gestion des rôles par l'administrateur
     */
    @Override
    public void changed(ObservableValue<? extends RoleConnexion> observable, RoleConnexion oldValue, RoleConnexion newValue)
    {   
        try {
            RequetesJDBC.updateUserRole(user, newValue);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
