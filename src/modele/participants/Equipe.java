package modele.participants;

import java.util.ArrayList;
import java.util.List;

import bd.server.RequetesJDBC;
import modele.Classement;
import modele.Pays;
import modele.Sexe;
import modele.competitions.Competition;
import modele.exceptions.EquipePleineException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.sports.Sport;

public class Equipe implements Participant {

    private String nomEquipe;
    private int tailleMax;
    private Sport sport;
    private Classement classement;
    private List<Athlete> listeAthletes;
    private Pays pays;
    private Competition competitionActuelle;
    private float performanceActuelle;

    public Equipe(String nomEquipe, Sport sport, int tailleMax, Pays pays) {
        this.nomEquipe = nomEquipe;
        this.tailleMax = tailleMax;
        this.sport = sport;
        this.pays = pays;
        this.listeAthletes = new ArrayList<>();
        this.competitionActuelle = null;
        this.classement = new Classement();
        this.pays.addEquipe(this);

        saveToBd();
    }

    public void saveToBd(){
        try{
            RequetesJDBC.creerEquipe(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Retourne la taille maximale de l'équipe.
     * 
     * @return la taille maximale de l'équipe
     */
    public int getTailleMax() {
        return this.tailleMax;
    }

    /**
     * Définie la taille maximale de l'équipe.
     * 
     * @param tailleMax la taille maximale de l'équipe
     */
    public void setTailleMax(int tailleMax) {
        this.tailleMax = tailleMax;
    }

    /**
     * Retourne la taille actuelle de l'équipe.
     * 
     * @return la taille actuelle de l'équipe
     */
    public int getTaille() {
        return this.listeAthletes.size();
    }

    /**
     * Retourne le sport de l'équipe.
     * 
     * @return le sport de l'équipe.
     */
    public Sport getSport() {
        return this.sport;
    }

    /**
     * getter des médailles
     * 
     * @return les médailles
     */
    @Override
    public Classement getClassement() {
        return this.classement;
    }

    /**
     * Getter de la liste des athlètes de l'équipe
     * 
     * @return la liste des athlètes de l'équipe
     */
    public List<Athlete> getListeAthletes() {
        return listeAthletes;
    }

    /**
     * Ajoute un athlète à la liste des athlètes de l'équipe
     * 
     * @param athlete l'athlète à ajouter
     * @throws EquipePleineException
     * @throws ParticipantDejaPresentException
     */
    public void addAthlete(Athlete athlete) throws EquipePleineException, ParticipantDejaPresentException {
        if (this.listeAthletes.size() + 1 > this.tailleMax) {
            throw new EquipePleineException();
        }
        if (this.listeAthletes.contains(athlete)) {
            throw new ParticipantDejaPresentException();
        }
        this.listeAthletes.add(athlete);
    }

    public boolean estPleine() {
        return this.listeAthletes.size() == this.tailleMax;
    }

    /**
     * Setter du pays de l'équipe
     * 
     * @param pays le pays de l'équipe
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    /**
     * Getter de la compétition actuelle, en tant que Participant
     * 
     * @return la compétition, null si il n'y a pas de compétition en cours.
     */
    @Override
    public Competition getCompetitionActuelle() {
        return this.competitionActuelle;
    }

    /**
     * Setter de la compétition actuelle en tant que Participant.
     * 
     * @param competition la compétition actuelle.
     */
    @Override
    public void setCompetitionActuelle(Competition competition) {
        this.competitionActuelle = competition;

        try{
            RequetesJDBC.setCompetEquipe(this, competition);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Calcul la force totale de l'équipe : somme des forces de tous les athlètes
     * 
     * @return la force totale de l'équipe
     */
    @Override
    public int getForce() {
        int totalForce = 0;
        for (Athlete ath : this.listeAthletes) {
            totalForce += ath.getForce();
        }
        return totalForce;
    }

    /**
     * Calcul l'agilité totale de l'équipe : somme des agilités de tous les athlètes
     * 
     * @return l'agilité totale de l'équipe
     */
    @Override
    public int getAgilite() {
        int totalAgilite = 0;
        for (Athlete ath : this.listeAthletes) {
            totalAgilite += ath.getAgilite();
        }
        return totalAgilite;
    }

    /**
     * Calcul l'endurance totale de l'équipe : somme des endurances de tous les
     * athlètes
     * 
     * @return l'endurance totale de l'équipe
     */
    @Override
    public int getEndurance() {
        int totalEndurance = 0;
        for (Athlete ath : this.listeAthletes) {
            totalEndurance += ath.getEndurance();
        }
        return totalEndurance;
    }

    @Override
    public float participer() throws IllegalStateException {
        if (this.competitionActuelle == null) {
            throw new IllegalStateException("L'athlète ne participe à aucune compétition");
        }
        this.performanceActuelle = this.competitionActuelle.getSport().calculerPerformance(this);
        return this.performanceActuelle;
    }

    /**
     * Retourne le nom de l'équipe, sans le préfixe "Equipe"
     * 
     * @return le nom de l'équipe
     */
    public String getNom() {
        return this.nomEquipe;
    }

    /**
     * Retourne le nom de l'équipe.
     * 
     * @return le nom de l'équipe
     */
    @Override
    public String obtenirNom() {
        return "Equipe " + this.nomEquipe;
    }

    @Override
    public Sexe obtenirSexe() {
        int nbHomme = 0;
        for (Athlete athetes : this.listeAthletes) {
            if (athetes.obtenirSexe() == Sexe.HOMME) {
                ++nbHomme;
            } else {
                --nbHomme;
            }
        }
        if (nbHomme >= 0) {
            return Sexe.HOMME;
        }
        return Sexe.FEMME;
    }

    /**
     * Getter du pays de l'équipe
     * 
     * @return le pays
     */
    @Override
    public Pays obtenirPays() {
        return this.pays;
    }

    /**
     * Getter de l'équipe
     * 
     * @return l'équipe
     */
    @Override
    public Equipe obtenirEquipes() {
        return this;
    }

    @Override
    public float getPerformance() {
        return this.performanceActuelle;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Equipe)) {
            return false;
        }

        Equipe equipe = (Equipe) o;
        return this.nomEquipe.equals(equipe.nomEquipe) &&
                this.sport.equals(equipe.sport) &&
                this.tailleMax == equipe.tailleMax &&
                this.pays.equals(equipe.pays);
    }

    public String athletesToString() {
        StringBuilder sb = new StringBuilder();
        for (Athlete athlete : this.listeAthletes) {
            sb.append(athlete.getNom()).append(" ").append(athlete.getPrenom()).append(", ");
        }
        // Supprime la dernière virgule et l'espace
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.nomEquipe.hashCode() + this.tailleMax + this.pays.hashCode();
    }

    public String afficherEquipe(){
        return "Equipe : " + this.nomEquipe + " (" + this.pays + ")" + " Elle contient " + this.listeAthletes.size()
                + " athlètes" + " et participe à la compétition : "
                + this.competitionActuelle + " avec une performance de " + this.performanceActuelle + " points"
                + " et  " + this.classement + "."
                + " Elle est composée de : " + athletesToString()
                + " Taille maximale : " + this.tailleMax + "."
                + " Force totale : " + this.getForce() + "." + " Agilité totale : " + this.getAgilite() + "."
                + " Endurance totale : " + this.getEndurance() + ".";
    }

    @Override
    public String toString() {
        return this.nomEquipe; //Pour le combobox de la vue
    }

}
