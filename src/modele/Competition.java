package modele;
import java.util.ArrayList;
import java.util.List;

import modele.exceptions.CompetitionPleineException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.sports.Sport;

public abstract class Competition {
    private int nbParticipantsNecessaire;
    private Sexe sexe;
    private List<Participant> lesParticipants;
    private Sport sport;
    
    
    public Competition(int nbParticipantsNecessaire, Sexe sexe, Sport sport) {
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
    public Sexe getSexe() {
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
     * @throws SexeCompetitionException 
     * @throws ParticipantDejaPresentException 
     * @throws ParticipantOccupeException
     * @throws CompetitionPleineException
     */
    public void enregistrerParticipant(Participant participant) throws SexeCompetitionException, ParticipantDejaPresentException, ParticipantOccupeException, CompetitionPleineException{
        if(participant.obtenirSexe() != this.sexe){
            throw new SexeCompetitionException();
        }
        if(this.lesParticipants.contains(participant)){ //NECESSITE UN EQUALS DANS PARTICIPANT : Participant est une interface -> Faire les equals dans Athletes/Equipes ?
            throw new ParticipantDejaPresentException();
        }
        if(participant.getCompetitionActuelle() != null){
            throw new ParticipantOccupeException();
        }

        int nbParticipant = 1; //1 étant l'athlète si le participant n'est pas une équipe.
        if(participant instanceof Equipes){
            nbParticipant = ((Equipes)participant).getTaille();
        }
        if(this.lesParticipants.size() + nbParticipant > nbParticipantsNecessaire){
            throw new CompetitionPleineException();
        }

        participant.setCompetitionActuelle(this);
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
        return this.sexe.equals(c.sexe) && 
        this.nbParticipantsNecessaire == c.nbParticipantsNecessaire &&
        this.sport.equals(c.sport) &&
        this.lesParticipants.equals(c.lesParticipants); //NECESSITE UN EQUALS DANS PARTICIPANT : Participant est une interface -> Faire les equals dans Athletes/Equipes ?
    }
}
