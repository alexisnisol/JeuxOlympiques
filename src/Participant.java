public interface Participant {

    /**
     * Méthode permettant à un participant de participer à une compétition.
     * @return le score du participant
     */
    public int participer();
    
    /**
     * Méthode permettant d'obtenir le nom du participant.
     * @return le nom du participant
     */
    public String obtenirNom();
    
    /**
     * Méthode permettant d'obtenir le nom du participant.
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
} 
    
