package modele.participants;

import modele.Classement;
import modele.Pays;
import modele.Sexe;
import modele.competitions.Competition;

public interface Participant {

    /**
     * Méthode permettant à un participant de participer à une compétition.
     * @return le score du participant
     * @throws IllegalStateException si le participant ne peut pas participer à la compétition
     */
    public float participer() throws IllegalStateException;
    
    /**
     * Méthode permettant d'obtenir le nom du participant.
     * @return le nom du participant
     */
    public String obtenirNom();
    
    /**
     * Méthode permettant d'obtenir le sexe du participant.
     * @return le sexe du participant
     */
    public Sexe obtenirSexe();

    /**
     * Méthode permettant d'obtenir le pays du participant.
     * @return le pays du participant
     */
    public Pays obtenirPays();

    /**
     * Méthode permettant d'obtenir l'équipe du participant.
     * @return l'équipe du participant
     */
    public Equipes obtenirEquipes();

    /**
     * Getter de la compétition actuelle, en tant que Participant.
     * @return la compétition, null si il n'y a pas de compétition en cours.
     */
    public Competition getCompetitionActuelle();

    /**
     * Setter de la compétition actuelle en tant que Participant.
     * @param competition la compétition actuelle.
     */
    public void setCompetitionActuelle(Competition competition);

    /**
     * Renvoie le classement du participant.
     * @return le classement du participant
     */
    public Classement getClassement();

    /**
     * Renvoie la force du participant.
     * @return la force du participant
     */
    public int getForce();

    /**
     * Renvoie l'agilité du participant.
     * @return l'agilité du participant
     */
    public int getAgilite();

    /**
     * Renvoie l'endurance du participant.
     * @return l'endurance du participant
     */
    public int getEndurance();

    /**
     * Renvoie la performance du participant.
     * @return la performance du participant
     */
    public float getPerformance();
    

}
