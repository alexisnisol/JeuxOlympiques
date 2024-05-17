package modele.sports;
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
    
}
