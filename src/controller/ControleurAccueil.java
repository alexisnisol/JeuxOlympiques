package controller;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import vue.accueil.Main;
import vue.accueil.Main.ButtonAction;

public class ControleurAccueil implements EventHandler<ActionEvent>{

    private Main main;
    private ButtonAction action;
    
    public ControleurAccueil(Main main, ButtonAction action) {
        this.main = main;
        this.action = action;

    }

    @Override
    public void handle(ActionEvent event) {
        switch (this.action) {
            case LOGIN_PANE:
                main.afficherlogin();
                break;

            case INSCRIPTION:
                main.afficherInscription();
                break;

        
            default:
                main.afficherInscription();
        }
    }
}
