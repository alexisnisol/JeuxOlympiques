package vue.accueil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import bd.server.ConnexionMySQL;
import controller.sql.ControleurSQL;

/**
 * Classe LoginBD
 *
 * Affiche la page de connexion à la base de données
 */

public class LoginBD extends GridPane {

    /**
     * connexionMySQL : ConnexionMySQL - Connexion à la base de données
     * main : Main - Fenêtre principale
     * motDePasse : PasswordField - Champ de texte pour le mot de passe
     * login : TextField - Champ de texte pour le login
     */

    private ConnexionMySQL connexionMySQL;
    private Main main;
    private PasswordField motDePasse;
    private TextField login, nomServeur, nomBD;

    /**
     * Classe EcouteurLogin qui implémente l'interface ChangeListener<Boolean>
     *
     * Ecouteur pour le champ de texte login
     * Si le champ de texte login est vide, le champ de texte nomBD est rempli automatiquement
     * avec la valeur "DB" + login
     * 
     * @param observableValue : ObservableValue<? extends Boolean>
     * @param b : Boolean
     * @param b1 : Boolean
     * @return void : ne retourne rien
     */
    private class EcouteurLogin implements ChangeListener<Boolean> {

        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean b, Boolean b1) {
            if (!b1 && nomBD.getText().equals("") && !(login.getText().equals(""))) {
                nomBD.setText("DB" + login.getText());
            }
        }
    }

    /**
     * Constructeur de la classe LoginBD
     *
     * @param main : Main - Fenêtre principale
     */


    public LoginBD(Main main) {
        super();

        this.main = main;

        // Connexion à la base de données
        try {
            this.connexionMySQL = new ConnexionMySQL();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver MySQL non trouvé!!!");
            System.exit(1);
        }
        // Initialisation de la grille
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);

        // Création des labels et des champs de texte
        Label ln = new Label("Login :");
        ln.setAlignment(Pos.BASELINE_RIGHT);
        Label lp = new Label("Mode de passe :");
        lp.setAlignment(Pos.BASELINE_RIGHT);
        Label ls = new Label("Nom du serveur :");
        ls.setAlignment(Pos.BASELINE_RIGHT);
        Label lbd = new Label("Nom de la base de données :");
        ls.setAlignment(Pos.BASELINE_RIGHT);

        this.motDePasse = new PasswordField();
        this.motDePasse.setAlignment(Pos.BASELINE_LEFT);
        this.login = new TextField();
        this.login.setAlignment(Pos.BASELINE_LEFT);
        this.login.focusedProperty().addListener(new EcouteurLogin());

        this.nomServeur = new TextField("servinfo-maria");
        this.nomServeur.setAlignment(Pos.BASELINE_LEFT);
        this.nomBD = new TextField();
        this.nomBD.setAlignment(Pos.BASELINE_LEFT);
        Button b = new Button("Valider");
        b.setAlignment(Pos.CENTER);
        b.setOnAction(new ControleurSQL(this));
        // Ajout des éléments à la grille
        this.add(ln, 1, 1);
        this.add(lp, 1, 2);
        this.add(ls, 1, 3);
        this.add(lbd, 1, 4);
        this.add(this.login, 2, 1);
        this.add(this.motDePasse, 2, 2);
        this.add(this.nomServeur, 2, 3);
        this.add(this.nomBD, 2, 4);
        this.add(b, 1, 5, 2, 1);
    }

    /**
     * Méthode pageSuivante
     *
     * Affiche la page d'accueil
     */
    public void pageSuivante() {
        this.main.afficherAccueil();
    }

    /**
     * Méthode getConnexionMySQL
     *
     * Retourne la connexion à la base de données
     *
     * @return ConnexionMySQL : connexion à la base de données
     */
    public ConnexionMySQL getConnexionMySQL() {
        return connexionMySQL;
    }

    /**
     * Méthode getMotDePasse
     *
     * Retourne le mot de passe
     *
     * @return String : mot de passe
     */
    public String getMotDePasse() {
        return this.motDePasse.getText();
    }

    /**
     * Méthode getLogin
     *
     * Retourne le login
     *
     * @return String : login
     */

    public String getLogin() {
        return this.login.getText();
    }

    /**
     * Méthode getNomServeur
     *
     * Retourne le nom du serveur
     *
     * @return String : nom du serveur
     */

    public String getNomServeur() {
        return this.nomServeur.getText();
    }

    /**
     * Méthode getNomBD
     *
     * Retourne le nom de la base de données
     *
     * @return String : nom de la base de données
     */
    
    public String getNomBD() {
        return this.nomBD.getText();
    }

}
