package modele;

import modele.exceptions.EquipePleineException;
import modele.exceptions.ParticipantDejaPresentException;

public class Athletes implements Participant{

    private String nom;
    private String prenom;
    private Sexe sexe;
    private int force;
    private int endurance;
    private int agilite;
    private Classement classement;
    private Pays pays;
    private Equipes equipe;
    private Competition competitionActuelle;
    private float performanceActuelle;

    public Athletes(String nom, String prenom, Sexe sexe, int force, int endurance, int agilite, Pays pays){
        this.nom = nom;
        this.prenom = prenom;
        this.agilite = agilite;
        this.endurance = endurance; 
        this.sexe = sexe;
        this.force = force;
        this.pays = pays;
        this.competitionActuelle = null;
        this.equipe = null;
        this.classement = new Classement();
        this.pays.addAthlete(this);
    }

    public boolean rejoindreEquipe(Equipes equipe){
        try {
            this.equipe = equipe;
            this.equipe.addAthlete(this);
            return true;
        } catch (EquipePleineException e) {
            e.printStackTrace();
        } catch (ParticipantDejaPresentException e){
            e.printStackTrace();
        }
        return false;
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
    @Override
    public Classement getClassement() {
        return this.classement;
    }
    
    /**
     * getter de la force
     * @return la force
     */
    @Override
    public int getForce() {
        return this.force;
    }

    /**
     * getter de l'endurance
     * @return l'endurance
     */
    @Override
    public int getEndurance() {
        return this.endurance;
    }

    /**
     * getter de l'agilité
     * @return l'agilité
     */
    @Override
    public int getAgilite() {
        return this.agilite;
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
    public float participer() throws IllegalStateException{
        if(this.competitionActuelle == null){
            throw new IllegalStateException("L'athlète ne participe à aucune compétition");
        }
        this.performanceActuelle = this.competitionActuelle.getSport().calculerPerformance(this);
        return this.performanceActuelle;
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

    @Override
    public float getPerformance() {
        return this.performanceActuelle;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o==null){return false;}
        if (o == this) {return true; } 
        if (!(o instanceof Athletes)) {return false;}

        Athletes athlete = (Athletes) o;
        return this.nom.equals(athlete.nom) &&
        this.prenom.equals(athlete.prenom) &&
        this.sexe == athlete.sexe &&
        this.force == athlete.force &&
        this.endurance == athlete.endurance &&
        this.agilite == athlete.agilite &&
        this.classement.equals(athlete.classement) &&
        this.pays.equals(athlete.pays) &&
        (this.equipe == null && athlete.equipe == null || this.equipe != null && this.equipe.equals(athlete.equipe)) &&
        (this.competitionActuelle == null && athlete.competitionActuelle == null || this.competitionActuelle != null && this.competitionActuelle.equals(athlete.competitionActuelle));
    }

    @Override
    public int hashCode() {
        return this.nom.hashCode() + this.prenom.hashCode() + this.sexe.hashCode() + this.force + this.endurance + this.agilite + this.pays.hashCode();
    }

    @Override
    public String toString() {
        return this.nom + " " +this.prenom + " (" + this.sexe + ") " + " Pays : " + this.pays + " Equipe : " + this.equipe + " Performance : " + this.performanceActuelle + " Classement : " + this.classement
        + " Force : " + this.force + " Endurance : " + this.endurance + " Agilité : " + this.agilite+ "\n" + "Compétition actuelle : " + this.competitionActuelle + "\n" + "Performance actuelle : " + this.performanceActuelle;
    }
}