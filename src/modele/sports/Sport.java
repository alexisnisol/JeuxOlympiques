package modele.sports;

import modele.participants.Participant;

public abstract class Sport {
    
    private String nom;
    private boolean enEquipe;
    private int taille;

    public Sport(String nom, boolean enEquipe, int taille){
        this.nom = nom;
        this.enEquipe = enEquipe;
        this.taille = taille;
    }

    public abstract float calculerPerformance(Participant participant);

    public String getNom() {
        return nom;
    }
    public abstract String getTypeSport();

    public boolean isEnEquipe() {
        return enEquipe;
    }

    public int getTaille() {
        return taille;
    }

    @Override
    public boolean equals(Object object){
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Sport)) return false;
        Sport sport = (Sport) object;
        return this.nom.equals(sport.getNom());
    }

    @Override
    public int hashCode(){
        return this.nom.hashCode();
    }

    @Override
    public String toString() {
        return "Sport [nom=" + nom + ", enEquipe=" + enEquipe + "]";
    }

}
