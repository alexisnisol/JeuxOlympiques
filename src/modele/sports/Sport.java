package modele.sports;

import modele.Participant;

public abstract class Sport {
    
    private float coeff;

    public Sport(float coeff){
        this.coeff = coeff;
    }
    
    /**
     * getter du coeff du sport
     * @return le coeff
     */
    public float getCoeff(){
        return this.coeff;
    }

    public abstract float calculerPerformance(Participant participant);

    //TODO : Faire le equals
}
