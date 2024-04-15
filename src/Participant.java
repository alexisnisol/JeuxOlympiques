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

    //TODO : Obtenir le sexe du participant (nécessite une implémentation dans Equipes et Athletes)
    //TODO : Obtenir le pays du participant (nécessite une implémentation dans Equipes et Athletes)
    //TODO : Obtenir l'équipe du participant (nécessite une implémentation dans Equipes et Athletes)
} 
    
