public class Escrime extends Sport{

    private String type; //TODO : Faire une enum

    public Escrime(float coeff,String type) {
        super(coeff);
        this.type = type;
    }

    /**
     * getter du type de l'escrime : fleuret ou épée
     * @return le type
     */
    public String getType(){
        return this.type;
    }
}
