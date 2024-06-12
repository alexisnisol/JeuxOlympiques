package modele.sports;

import modele.participants.Participant;

public class Athletisme extends Sport{

    private int distance;

    public Athletisme(String nom, boolean enEquipe, int distance, int taille) {
        super(nom, enEquipe, taille);
        this.distance = distance;
    }

    /**
     * getter de la distance à faire sur ce sport
     * @return la distance
     */
    public int getDistance(){
        return this.distance;
    }

    @Override
    public float calculerPerformance(Participant participant) {
        return ((participant.getAgilite()*2) + (participant.getEndurance()*5) + participant.getForce());
    }
    
}