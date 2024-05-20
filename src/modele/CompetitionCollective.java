package modele;

import modele.exceptions.CompetitionPleineException;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.sports.Sport;

public class CompetitionCollective extends Competition{

    public CompetitionCollective(int nbParticipant, Sexe sexe, Sport sport) {
        super(nbParticipant, sexe, sport);
    }
    
    
    @Override
    public void enregistrerParticipant(Participant participant) throws SexeCompetitionException, ParticipantDejaPresentException, ParticipantOccupeException, CompetitionPleineException, MauvaisParticipantException{
        if(participant instanceof Athletes){
            throw new MauvaisParticipantException();
        }
        super.enregistrerParticipant(participant);
    }

}
