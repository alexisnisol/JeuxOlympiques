package modele;

public class Classement implements Comparable<Classement> {

    private int medaillesOr;
    private int medaillesArgent;
    private int medaillesBronze;

    public Classement() {
        this.medaillesOr = 0;
        this.medaillesArgent = 0;
        this.medaillesBronze = 0;
    }

    /**
     * Renvoie le nombre de médailles d'or.
     * 
     * @return Le nombre de médailles d'or.
     */
    public int getMedaillesOr() {
        return this.medaillesOr;
    }

    /**
     * Renvoie le nombre de médailles d'argent.
     * 
     * @return Le nombre de médailles d'argent.
     */
    public int getMedaillesArgent() {
        return this.medaillesArgent;
    }

    /**
     * Renvoie le nombre de médailles de bronze.
     * 
     * @return Le nombre de médailles de bronze.
     */
    public int getMedaillesBronze() {
        return this.medaillesBronze;
    }

    /**
     * Ajoute une médaille d'or au classement.
     */
    public void ajouterMedailleOr() {
        this.medaillesOr++;
    }

    /**
     * Ajoute une médaille d'argent au classement.
     */
    public void ajouterMedailleArgent() {
        this.medaillesArgent++;
    }

    /**
     * Ajoute une médaille de bronze au classement.
     */
    public void ajouterMedailleBronze() {
        this.medaillesBronze++;
    }

    /**
     * Ajoute un nombre spécifié de médailles d'or au classement.
     * 
     * @param nb Le nombre de médailles d'or à ajouter.
     */
    public void ajouterMedailleOr(int nb) {
        this.medaillesOr += nb;
    }

    /**
     * Ajoute un nombre spécifié de médailles d'argent au classement.
     * 
     * @param nb Le nombre de médailles d'argent à ajouter.
     */
    public void ajouterMedailleArgent(int nb) {
        this.medaillesArgent += nb;
    }

    /**
     * Ajoute un nombre spécifié de médailles de bronze au classement.
     * 
     * @param nb Le nombre de médailles de bronze à ajouter.
     */
    public void ajouterMedailleBronze(int nb) {
        this.medaillesBronze += nb;
    }

    /**
     * Renvoie le total de médailles (or, argent et bronze) dans le classement.
     * 
     * @return Le total de médailles.
     */
    public int getTotal() {
        return this.medaillesOr + this.medaillesArgent + this.medaillesBronze;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du classement
     * des médailles d'or.
     * 
     * @return La représentation du classement des médailles d'or.
     */
    public Classement getClassementOr() {
        Classement classement = new Classement();
        classement.ajouterMedailleOr(this.medaillesOr);
        return classement;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du classement.
     * 
     * @return La représentation du classement.
     */
    @Override
    public String toString() {
        return "" + this.medaillesOr + " médailles d'or, " + this.medaillesArgent
                + " médailles d'argent, " + this.medaillesBronze + " médailles de bronze.";
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du classement,
     * au format plus simple.
     * 
     * @return La représentation du classement.
     */
    public String shortString() {
        return "" + this.medaillesOr + " Or, " + this.medaillesArgent
                + " Argent, " + this.medaillesBronze + " Bronze.";
    }

    /**
     * Calcule le code de hachage du classement.
     * 
     * @return Le code de hachage du classement.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.medaillesOr;
        hash = 97 * hash + this.medaillesArgent;
        hash = 97 * hash + this.medaillesBronze;
        return hash;
    }

    /**
     * Vérifie si le classement est égal à un autre objet.
     * 
     * @param o L'objet à comparer.
     * @return true si le classement est égal à l'objet spécifié, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Classement)) {
            return false;
        }

        Classement classement = (Classement) o;
        return this.medaillesOr == classement.medaillesOr &&
                this.medaillesArgent == classement.medaillesArgent &&
                this.medaillesBronze == classement.medaillesBronze;
    }

    @Override
    public int compareTo(Classement o) {
        return Integer.compare(this.getTotal(), o.getTotal());
    }

}
