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
    
}