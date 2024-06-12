package modele.participants;

import modele.Classement;
import modele.Pays;
import modele.Sexe;
import modele.competitions.Competition;
import modele.exceptions.EquipePleineException;
import modele.exceptions.ParticipantDejaPresentException;

/**
 * Cette classe représente un athlète participant aux Jeux Olympiques.
 */
public class Athlete implements Participant {
    private String nom;
    private String prenom;
    private Sexe sexe;
    private int force;
    private int endurance;
    private int agilite;
    private Classement classement;
    private Pays pays;
    private Equipe equipe;
    private Competition competitionActuelle;
    private float performanceActuelle;

    /**
     * Constructeur de la classe Athletes.
     * 
     * @param nom       le nom de l'athlète
     * @param prenom    le prénom de l'athlète
     * @param sexe      le sexe de l'athlète
     * @param force     la force de l'athlète
     * @param endurance l'endurance de l'athlète
     * @param agilite   l'agilité de l'athlète
     * @param pays      le pays de l'athlète
     */
    public Athlete(String nom, String prenom, Sexe sexe, int force, int endurance, int agilite, Pays pays) {
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

    /**
     * Permet à l'athlète de rejoindre une équipe.
     * 
     * @param equipe l'équipe à rejoindre
     * @return true si l'athlète a réussi à rejoindre l'équipe, false sinon
     */
    public boolean rejoindreEquipe(Equipe equipe) {
        try {
            this.equipe = equipe;
            this.equipe.addAthlete(this);
            return true;
        } catch (EquipePleineException e) {
            e.printStackTrace();
        } catch (ParticipantDejaPresentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtient le nom de l'athlète.
     * 
     * @return le nom de l'athlète
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Obtient le prénom de l'athlète.
     * 
     * @return le prénom de l'athlète
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Obtient le classement de l'athlète.
     * 
     * @return le classement de l'athlète
     */
    @Override
    public Classement getClassement() {
        return this.classement;
    }

    /**
     * Obtient la force de l'athlète.
     * 
     * @return la force de l'athlète
     */
    @Override
    public int getForce() {
        return this.force;
    }

    /**
     * Obtient l'endurance de l'athlète.
     * 
     * @return l'endurance de l'athlète
     */
    @Override
    public int getEndurance() {
        return this.endurance;
    }

    /**
     * Obtient l'agilité de l'athlète.
     * 
     * @return l'agilité de l'athlète
     */
    @Override
    public int getAgilite() {
        return this.agilite;
    }

    /**
     * Obtient la compétition actuelle de l'athlète en tant que Participant.
     * 
     * @return la compétition actuelle, null s'il n'y a pas de compétition en cours
     */
    @Override
    public Competition getCompetitionActuelle() {
        return this.competitionActuelle;
    }

    /**
     * Définit la compétition actuelle de l'athlète en tant que Participant.
     * 
     * @param competition la compétition actuelle
     */
    @Override
    public void setCompetitionActuelle(Competition competition) {
        this.competitionActuelle = competition;
    }

    /**
     * Permet à l'athlète de participer à la compétition actuelle.
     * 
     * @return la performance de l'athlète
     * @throws IllegalStateException si l'athlète ne participe à aucune compétition
     */
    @Override
    public float participer() throws IllegalStateException {
        if (this.competitionActuelle == null) {
            throw new IllegalStateException("L'athlète ne participe à aucune compétition");
        }
        this.performanceActuelle = this.competitionActuelle.getSport().calculerPerformance(this);
        return this.performanceActuelle;
    }

    /**
     * Obtient le nom complet de l'athlète.
     * 
     * @return le nom complet de l'athlète
     */
    @Override
    public String obtenirNom() {
        return this.sexe.getAbreviation() + " " + this.nom + " " + this.prenom;
    }

    /**
     * Obtient le sexe de l'athlète.
     * 
     * @return le sexe de l'athlète
     */
    @Override
    public Sexe obtenirSexe() {
        return this.sexe;
    }

    /**
     * Obtient le pays de l'athlète.
     * 
     * @return le pays de l'athlète
     */
    @Override
    public Pays obtenirPays() {
        return this.pays;
    }

    /**
     * Obtient l'équipe de l'athlète.
     * 
     * @return l'équipe de l'athlète
     */
    @Override
    public Equipe obtenirEquipes() {
        return this.equipe;
    }

    /**
     * Obtient la performance actuelle de l'athlète.
     * 
     * @return la performance actuelle de l'athlète
     */
    @Override
    public float getPerformance() {
        return this.performanceActuelle;
    }

    /**
     * Vérifie si l'objet spécifié est égal à l'athlète.
     * 
     * @param o l'objet à comparer
     * @return true si l'objet est égal à l'athlète, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Athlete)) {
            return false;
        }

        Athlete athlete = (Athlete) o;
        return this.nom.equals(athlete.nom) &&
                this.prenom.equals(athlete.prenom) &&
                this.sexe == athlete.sexe &&
                this.force == athlete.force &&
                this.endurance == athlete.endurance &&
                this.agilite == athlete.agilite &&
                this.pays.equals(athlete.pays) &&
                (this.equipe == null && athlete.equipe == null
                        || this.equipe != null && this.equipe.equals(athlete.equipe))
                &&
                (this.competitionActuelle == null && athlete.competitionActuelle == null
                        || this.competitionActuelle != null
                                && this.competitionActuelle.equals(athlete.competitionActuelle));
    }

    /**
     * Retourne le code de hachage de l'athlète.
     * 
     * @return le code de hachage de l'athlète
     */
    @Override
    public int hashCode() {
        return this.nom.hashCode() + this.prenom.hashCode() + this.sexe.hashCode() + this.force + this.endurance
                + this.agilite + this.pays.hashCode();
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'athlète.
     * 
     * @return une représentation sous forme de chaîne de caractères de l'athlète
     */
    @Override
    public String toString() {
        return this.nom + " " + this.prenom + " (" + this.pays + ")" + " Sexe : " + this.sexe + " Force : " + this.force
                + " Endurance : " + this.endurance + " Agilité : " + this.agilite + " Performance : "
                + this.performanceActuelle + " Équipe : " + this.equipe + " Compétition actuelle : "
                + this.competitionActuelle;
    }
}
