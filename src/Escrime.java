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
}
