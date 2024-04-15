import java.util.ArrayList;
import java.util.List;

public abstract class Competition {
    private int nbParticipants;
    private String sexe;
    private List<Participant> lesParticipants;
    private Sport sport;
    
    
    public Competition(int nbParticipants, String sexe, List<Participant> lesParticipants,Sport sport) {
        this.nbParticipants = nbParticipants;
        this.sexe = sexe;
        this.lesParticipants = new ArrayList<>();
        this.sport = sport;
    }


    public int getNbParticipants() {
        return nbParticipants;
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
