package modele;

import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.sports.Sport;

public class CompetitionCollective extends Competition {

    public CompetitionCollective(Sexe sexe, Sport sport) {
        super(sexe, sport);
    }

    @Override
    public void enregistrerParticipant(Participant participant) throws SexeCompetitionException,
            ParticipantDejaPresentException, ParticipantOccupeException, MauvaisParticipantException {
        if (participant instanceof Athletes) {
            throw new MauvaisParticipantException();
        }
        super.enregistrerParticipant(participant);
    }

    @Override
    public String toString() {
        return "La Competition Collective " + this.sexe + " de " + sport.getNom() + " a " + lesParticipants.size()
                + " Participants";
    }

}