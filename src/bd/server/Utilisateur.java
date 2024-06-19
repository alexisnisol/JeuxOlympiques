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

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public RequetesJDBC.RoleConnexion getRole() {
        return role;
    }

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

}
