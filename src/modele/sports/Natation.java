package modele.sports;

import modele.Participant;

public class Natation extends Sport{
    
    private int distance;

    public Natation(float coeff, int distance) {
        super(coeff);
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
        return ((participant.getAgilite()*3) + (participant.getEndurance()*3) + participant.getForce()) * this.getCoeff();
    }
    
}
