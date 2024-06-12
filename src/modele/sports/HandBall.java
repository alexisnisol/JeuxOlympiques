package modele.sports;

import modele.participants.Participant;

public class HandBall extends Sport{

    public HandBall(String nom, boolean enEquipe, int taille) {
        super(nom, enEquipe, taille);
    }

    @Override
    public float calculerPerformance(Participant participant) {
        return ((participant.getAgilite()*5) + (participant.getEndurance()*2) + participant.getForce()*2);
    }
}