public class Athletes implements Participant{

    private String nom;
    private String prenom;
    private Sexe sexe;
    private int force;
    private int endurance;
    private int agilite;
    private String medailles;
    private int totalMedailles;
    private Pays pays;
    private Equipes equipe;
    private Competition competitionActuelle;

    public Athletes(String nom, String prenom, Sexe sexe, int force, int endurance, int agilite, String medailles, int totalMedailles, Pays pays, Equipes equipe){
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
        this.competitionActuelle = null;
        this.pays.addAthlete(this);
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
     * Getter de la compétition actuelle, en tant que Participant
     * @return la compétition, null si il n'y a pas de compétition en cours.
     */
    @Override
    public Competition getCompetitionActuelle(){
        return this.competitionActuelle;
    }

    /**
     * Setter de la compétition actuelle en tant que Participant.
     * @param competition la compétition actuelle.
     */
    @Override
    public void setCompetitionActuelle(Competition competition){
        this.competitionActuelle = competition;
    }


    @Override
    public int participer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'participer'");
    }

    @Override
    public String obtenirNom() {
        return this.sexe.getAbreviation() + " " + this.nom + " " + this.prenom;
    }

    /**
     * getter du sexe de l'Athlète
     * @return le sexe de l'Athlète
     */
    @Override
    public Sexe obtenirSexe(){
        return this.sexe;
    }

    /**
     * getter du pays de l'Athlète
     * @return le pays de l'Athlète
     */
    @Override
    public Pays obtenirPays() {
        return this.pays;
    }

    /**
     * getter de l'équipe de l'Athlète
     * @return l'équipe de l'Athlète
     */
    @Override
    public Equipes obtenirEquipes() {
        return this.equipe;
    }
    
}