package bd.server;

import java.sql.SQLException;

public class Utilisateur {

    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;
    private RequetesJDBC.RoleConnexion role;
    
    public Utilisateur(String nom, String prenom, String pseudo, String mdp, RequetesJDBC.RoleConnexion role) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.role = role;
    }

    /**
     * Permet de récupérer le nom de l'utilisateur
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     *  Permet de récupérer le prénom de l'utilisateur
     * @return le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *  Permet de récupérer le pseudo de l'utilisateur
     * @return le pseudo de l'utilisateur
     */
    public String getPseudo() {
        return pseudo;
    }
    
    /**
     * Permet de récupérer le mot de passe de l'utilisateur
     * @return le mot de passe de l'utilisateur
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Permet de changer le mot de passe de l'utilisateur
     * @return le mot de passe de l'utilisateur
     */
    public RequetesJDBC.RoleConnexion getRole() {
        return role;
    }

    /**
     * Permet de changer le rôle de l'utilisateur, et de le mettre à jour dans la base de données
     * @param role le rôle de l'utilisateur
     */
    public void setRole(RequetesJDBC.RoleConnexion role) {
        try {
            RequetesJDBC.updateUserRole(this, role);
            this.role = role;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", mdp= xx, role="+ role + "]";
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj instanceof Utilisateur){
            Utilisateur u = (Utilisateur) obj;
            return u.getPseudo().equals(this.getPseudo()) && u.getMdp().equals(this.getMdp()) && u.getRole().equals(this.getRole());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.getPseudo().hashCode()*17 + this.getMdp().hashCode() + this.getRole().hashCode() * 31;
    }

}
