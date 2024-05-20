package modele.sports;

import modele.Participant;

public class Escrime extends Sport{

    private TypeEscrime type;

    public Escrime(float coeff,TypeEscrime type) {
        super(coeff);
        this.type = type;
    }

    /**
     * getter du type de l'escrime : fleuret ou épée
     * @return le type
     */
    public TypeEscrime getType(){
        return this.type;
    }

    @Override
    public float calculerPerformance(Participant participant) {
        return ((participant.getAgilite()*2) + (participant.getEndurance()*2) + (participant.getForce()*5)) * this.getCoeff();
    }
}
