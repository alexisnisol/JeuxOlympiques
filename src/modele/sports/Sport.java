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

    @Override
    public boolean equals(Object o) {
        if (o==null){return false;}
        if (o == this) {return true; } 
        if (!(o instanceof Sport)) {return false;}
        Sport s = (Sport) o;
        return this.coeff == s.coeff;
    }
}
