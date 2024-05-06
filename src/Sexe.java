public enum Sexe {
    HOMME("M."),
    FEMME("Mme.");

    private final String abreviation;

    /**
     * Constructeur de l'énumération Sexe
     * @param abreviation l'abréviation du sexe
     */
    Sexe(String abreviation) {
        this.abreviation = abreviation;
    }

    /**
     * Retourne l'abréviation du sexe
     * @return l'abréviation du sexe
     */
    public String getAbreviation() {
        return abreviation;
    }
}
