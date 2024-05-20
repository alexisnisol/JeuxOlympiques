package modele.sports;

import modele.Participant;

public class Athletisme extends Sport{

    private int distance;

    public Athletisme(float coeff, int distance) {
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
        return ((participant.getAgilite()*2) + (participant.getEndurance()*5) + participant.getForce()) * this.getCoeff();
    }
    
}