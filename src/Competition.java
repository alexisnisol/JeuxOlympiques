import java.util.ArrayList;
import java.util.List;

public abstract class Competition {
    private int nbParticipant;
    private String sexe;
    private List<Participant> lesParticipants;
    private Sport sport;
    
    
    public Competition(int nbParticipant, String sexe, List<Participant> lesParticipants,Sport sport) {
        this.nbParticipant = nbParticipant;
        this.sexe = sexe;
        this.lesParticipants = new ArrayList<>();
        this.sport = sport;
    }


    public int getNbParticipant() {
        return nbParticipant;
    }


    public String getSexe() {
        return sexe;
    }


    public List<Participant> getParticipants() {
        return lesParticipants;
        
    }


    public Sport getSport() {
        return sport;
    }

}
