package modele;

public class Classement {

    private int medaillesOr;
    private int medaillesArgent;
    private int medaillesBronze;

    public Classement() {
        this.medaillesOr = 0;
        this.medaillesArgent = 0;
        this.medaillesBronze = 0;
    }

    public int getMedaillesOr() {
        return this.medaillesOr;
    }

    public int getMedaillesArgent() {
        return this.medaillesArgent;
    }

    public int getMedaillesBronze() {
        return this.medaillesBronze;
    }

    public void ajouterMedailleOr() {
        this.medaillesOr++;
    }

    public void ajouterMedailleArgent() {
        this.medaillesArgent++;
    }

    public void ajouterMedailleBronze() {
        this.medaillesBronze++;
    }

    
    public void ajouterMedailleOr(int nb) {
        this.medaillesOr += nb;
    }

    public void ajouterMedailleArgent(int nb) {
        this.medaillesArgent += nb;
    }

    public void ajouterMedailleBronze(int nb) {
        this.medaillesBronze += nb;
    }

    public int getTotal(){
        return this.medaillesOr + this.medaillesArgent + this.medaillesBronze;
    }

    @Override
    public String toString() {
        return "Classement{" + "medaillesOr=" + medaillesOr + ", medaillesArgent=" + medaillesArgent + ", medaillesBronze=" + medaillesBronze + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.medaillesOr;
        hash = 97 * hash + this.medaillesArgent;
        hash = 97 * hash + this.medaillesBronze;
        return hash;
    }

    @Override
    public boolean equals(Object o){
        if (o==null){return false;}
        if (o == this) {return true; } 
        if (!(o instanceof Classement)) {return false;}

        Classement classement = (Classement) o;
        return this.medaillesOr == classement.medaillesOr &&
        this.medaillesArgent == classement.medaillesArgent &&
        this.medaillesBronze == classement.medaillesBronze;
    }
}
