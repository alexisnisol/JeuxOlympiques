import java.util.ArrayList;
import java.util.List;

public abstract class Competition {
    private int nbParticipantsNecessaire;
    private String sexe;
    private List<Participant> lesParticipants;
    private Sport sport;
    
    
    public Competition(int nbParticipantsNecessaire, String sexe, List<Participant> lesParticipants,Sport sport) {
        this.nbParticipantsNecessaire = nbParticipantsNecessaire;
        this.sexe = sexe;
        this.lesParticipants = new ArrayList<>();
        this.sport = sport;
    }


    /**
     * Retourne le nombre de participants nécessaire pour la compétition
     * @return le nombre de participants
     */
    public int getNbParticipantsNecessaire() {
        return this.nbParticipantsNecessaire;
    }

    /**
     * Retourne le sexe de la compétition
     * @return le sexe de la compétition : Masculine ou Féminine
     */
    public String getSexe() {
        return this.sexe;
    }


    /**
     * Retourne la liste des participants de la compétition
     * @return la liste des participants de la compétition
     */
    public List<Participant> getParticipants() {
        return this.lesParticipants;
    }

    /**
     * Ajoute un participant à la liste des participants de la compétition
     * @param participant le participant à ajouter
     */
    public void addParticipant(Participant participant){
        //TODO : vérifier que le participant est bien du bon sexe
        //TODO : vérifier que le participant n'est pas déjà inscrit
        //TODO : vérifier que le participant est bien inscrit à la bonne compétition
        //TODO : Définir la compétition actuel du participant
        //TODO : Vérifier que la taille max de participants pour l'épreuve n'est pas dépassé (si c'est le cas, lever une exception) => Vérifier lorsqu'on ajoute une Equipes entière, et lorsqu'on ajoute un Athlete
        this.lesParticipants.add(participant);
    }


    public Sport getSport() {
        return sport;
    }

    @Override
    public boolean equals(Object o) {
        if (o==null){return false;}
        if (o == this) {return true; } 
        if (!(o instanceof Competition)) {return false;}

        Competition c = (Competition) o;
        return c.lesParticipants == this.lesParticipants;
    }
}
