public class Athletes implements Participant{

    private String nom;
    private String prenom;
    private String sexe;
    private int force;
    private int endurance;
    private int agilite;
    private String medailles;
    private int totalMedailles;
    private Pays pays;
    private Equipes equipe;

    public Athletes(String nom, String prenom, String sexe, int force, int endurance, int agilite, String medailles, int totalMedailles, Pays pays, Equipes equipe){
        this.nom = nom;
        this.prenom = prenom;
        this.agilite = agilite;
        this.endurance = endurance; 
        this.sexe = sexe;
        this.force = force;
        this.medailles = medailles;
        this.totalMedailles = totalMedailles;
        this.pays = pays;
        this.equipe = equipe;
    }

    /**
     * getter du nom
     * @return le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * getter du prenom
     * @return le prenom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * getter du sexe
     * @return le sexe
     */
    public String getSexe() {
        return this.sexe;
    }

    /**
     * getter des médailles
     * @return les médailles
     */
    public String getMedailles() {
        return this.medailles;
    }

    /**
     * getter de la force
     * @return la force
     */
    public int getForce() {
        return this.force;
    }

    /**
     * getter de l'endurance
     * @return l'endurance
     */
    public int getEndurance() {
        return this.endurance;
    }

    /**
     * getter de l'agilité
     * @return l'agilité
     */
    public int getAgilite() {
        return this.agilite;
    }

    /**
     * getter du nombre total des médailles
     * @return les médailles
     */
    public int getTotalMedailles() {
        return this.totalMedailles;
    }

    /**
     * getter de l'équipe
     * @return l'équipe
     */
    public Equipes getEquipes() {
        return this.equipe;
    }

    /** getter du pays
     * @return le pays
     */
    public Pays getPays() {
        return this.pays;
    }


    @Override
    public void participer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'participer'");
    }

    @Override
    public String obtenirNom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenirNom'");
    }
    
}