package modele;

import java.util.ArrayList;
import java.util.List;
import modele.exceptions.EquipePleineException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.sports.Sport;

public class Equipes implements Participant {

    private String nomEquipe;
    private int tailleMax;
    private Sport sport;
    private boolean enRelais;
    private Classement classement;
    private List<Athletes> listeAthletes;
    private Pays pays;
    private Competition competitionActuelle;
    private float performanceActuelle;

    public Equipes(String nomEquipe, Sport sport, int tailleMax, boolean enRelais, Pays pays) {
        this.nomEquipe = nomEquipe;
        this.tailleMax = tailleMax;
        this.sport = sport;
        this.enRelais = enRelais;
        this.pays = pays;
        this.listeAthletes = new ArrayList<>();
        this.competitionActuelle = null;
        this.classement = new Classement();
        this.pays.addEquipe(this);
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
     * Retourne si l'équipe est une équipe relais.
     * 
     * @return l'attribut enRelais
     */
    public boolean isEnRelais() {
        return enRelais;
    }

    /**
     * Définie une équipe comme une équipe relais.
     * 
     * @param enRelais l'attribut enRelais
     */
    public void setEnRelais(boolean enRelais) {
        this.enRelais = enRelais;
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
    public List<Athletes> getListeAthletes() {
        return listeAthletes;
    }

    /**
     * Ajoute un athlète à la liste des athlètes de l'équipe
     * 
     * @param athlete l'athlète à ajouter
     * @throws EquipePleineException
     * @throws ParticipantDejaPresentException
     */
    public void addAthlete(Athletes athlete) throws EquipePleineException, ParticipantDejaPresentException {
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
    }

    /**
     * Calcul la force totale de l'équipe : somme des forces de tous les athlètes
     * 
     * @return la force totale de l'équipe
     */
    @Override
    public int getForce() {
        int totalForce = 0;
        for (Athletes ath : this.listeAthletes) {
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
        for (Athletes ath : this.listeAthletes) {
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
        for (Athletes ath : this.listeAthletes) {
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
     * Retourne le nom de l'équipe.
     * 
     * @return le nom de l'équipe
     */
    @Override
    public String obtenirNom() {
        return this.nomEquipe;
    }

    @Override
    public Sexe obtenirSexe() {
        int nbHomme = 0;
        for (Athletes athetes : this.listeAthletes) {
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
    public Equipes obtenirEquipes() {
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
        if (!(o instanceof Equipes)) {
            return false;
        }

        Equipes equipe = (Equipes) o;
        return this.nomEquipe.equals(equipe.nomEquipe) &&
                this.sport.equals(equipe.sport) &&
                this.tailleMax == equipe.tailleMax &&
                this.enRelais == equipe.enRelais &&
                this.pays.equals(equipe.pays);
    }

    public String athletesToString() {
        StringBuilder sb = new StringBuilder();
        for (Athletes athlete : this.listeAthletes) {
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
        return this.nomEquipe.hashCode() + this.tailleMax + (this.enRelais ? 34 : 17) + this.pays.hashCode();
    }

    @Override
    public String toString() {
        return "Equipe : " + this.nomEquipe + " (" + this.pays + ")" + " Elle contient " + this.listeAthletes.size()
                + " athlètes" + " et participe à la compétition : "
                + this.competitionActuelle + " avec une performance de " + this.performanceActuelle + " points"
                + " et  " + this.classement + "."
                + " Elle est composée de : " + athletesToString() + " Est-Elle en relais : " + this.enRelais + "."
                + " Taille maximale : " + this.tailleMax + "."
                + " Force totale : " + this.getForce() + "." + " Agilité totale : " + this.getAgilite() + "."
                + " Endurance totale : " + this.getEndurance() + ".";
    }

}
