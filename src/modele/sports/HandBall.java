package modele.sports;

import modele.Participant;

public class HandBall extends Sport{

    public HandBall(float coeff) {
        super(coeff);
    }

    @Override
    public float calculerPerformance(Participant participant) {
        return ((participant.getAgilite()*5) + (participant.getEndurance()*2) + participant.getForce()*2) * this.getCoeff();
    }
}