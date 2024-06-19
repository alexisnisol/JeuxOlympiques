package modele.sports;

import modele.participants.Participant;

public class Natation extends Sport{
    
    private int distance;

    public Natation(String nom, boolean enEquipe, int distance, int taille) {
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
        return ((participant.getAgilite()*3) + (participant.getEndurance()*3) + participant.getForce());
    }

    @Override
    public String getTypeSport() {
        return "Natation";
    }
    
}
