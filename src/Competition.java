import java.util.ArrayList;
import java.util.List;

public abstract class Competition {
    private int nbParticipant;
    private String sexe;
    private List<Participant> lesParticipants;
    
    
    public Competition(int nbParticipant, String sexe, List<Participant> lesParticipants) {
        this.nbParticipant = nbParticipant;
        this.sexe = sexe;
        this.lesParticipants = new ArrayList<>();
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

}
