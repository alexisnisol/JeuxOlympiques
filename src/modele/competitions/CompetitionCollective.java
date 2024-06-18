package modele.competitions;

import modele.Sexe;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Athlete;
import modele.participants.Participant;
import modele.sports.Sport;

public class CompetitionCollective extends Competition {

    public CompetitionCollective(Sexe sexe, Sport sport) {
        super(sexe, sport);
    }

    @Override
    public void enregistrerParticipant(Participant participant) throws SexeCompetitionException,
            ParticipantDejaPresentException, ParticipantOccupeException, MauvaisParticipantException {
        if (participant instanceof Athlete) {
            throw new MauvaisParticipantException();
        }
        super.enregistrerParticipant(participant);
    }

    @Override
    public String affichageVue() {
        return "Collective " + this.sexe + " de " + sport.getNom();
    }

    @Override
    public String toString() {
        return "La Competition Collective " + this.sexe + " de " + sport.getNom() + " a " + lesParticipants.size()
                + " Participants";
    }

}