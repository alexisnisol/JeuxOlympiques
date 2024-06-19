package modele.competitions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import BD.RequetesJDBC;
import modele.ComparePerformance;
import modele.Sexe;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Participant;
import modele.sports.Sport;

public abstract class Competition {
    protected Sexe sexe;
    protected List<Participant> lesParticipants;
    protected Sport sport;

    private boolean estJouee;

    public Competition(Sexe sexe, Sport sport) {
        this.sexe = sexe;
        this.lesParticipants = new ArrayList<>();
        this.sport = sport;
        this.estJouee = false;

        saveToBd();
    }

    public void saveToBd(){
        try {
            RequetesJDBC.creerCompet(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne le sexe de la compétition
     * 
     * @return le sexe de la compétition : Masculine ou Féminine
     */
    public Sexe getSexe() {
        return this.sexe;
    }

    /**
     * Retourne vrai si la compétition est jouée, faux sinon
     * 
     * @return vrai si la compétition est jouée, faux sinon
     */
    public boolean estJouee() {
        return this.estJouee;
    }

    /**
     * Retourne la liste des participants de la compétition
     * 
     * @return la liste des participants de la compétition
     */
    public List<Participant> getParticipants() {
        return this.lesParticipants;
    }

    /**
     * Ajoute un participant à la liste des participants de la compétition
     * 
     * @param participant le participant à ajouter
     * @throws SexeCompetitionException
     * @throws ParticipantDejaPresentException
     * @throws ParticipantOccupeException
     * @throws MauvaisParticipantException
     */
    public void enregistrerParticipant(Participant participant) throws SexeCompetitionException,
            ParticipantDejaPresentException, ParticipantOccupeException, MauvaisParticipantException {
        if (participant.obtenirSexe() != this.sexe) {
            throw new SexeCompetitionException();
        }
        if (this.lesParticipants.contains(participant)) {
            throw new ParticipantDejaPresentException();
        }
        if (participant.getCompetitionActuelle() != null) {
            throw new ParticipantOccupeException();
        }

        /*
         * if(participant instanceof Equipes){
         * for(Athletes a : ((Equipes)participant).getListeAthletes()){
         * a.setCompetitionActuelle(this);
         * }
         * }else{
         */
        participant.setCompetitionActuelle(this);
        // }

        this.lesParticipants.add(participant);
    }   

    /**
     * Joue la compétition, détermine les gagnants et termine la compétition.
     * 
     * @return la liste des participants, ordonnée par leur classement
     */
    public List<Participant> jouer() {
        // if(this.estPleine()){
        List<Participant> gagnants = this.calculerPlacement();
        this.estJouee = true;

        if (gagnants.size() > 0)
            gagnants.get(0).getClassement().ajouterMedailleOr();
        if (gagnants.size() > 1)
            gagnants.get(1).getClassement().ajouterMedailleArgent();
        if (gagnants.size() > 2)
            gagnants.get(2).getClassement().ajouterMedailleBronze();
        return gagnants;
        // }
        // return null;
    }

    /**
     * Calcule les gagnants de la compétition
     */
    private List<Participant> calculerPlacement() {
        for (Participant p : this.lesParticipants) {
            p.participer();
        }

        List<Participant> place = new ArrayList<>(this.lesParticipants);

        Collections.sort(place, new ComparePerformance());
        return place;
    }

    public List<Participant> getPlacement() throws IllegalStateException {
        if (!this.estJouee) {
            throw new IllegalStateException("La compétition n'est pas jouée");
        }
        List<Participant> place = new ArrayList<>(this.lesParticipants);
        Collections.sort(place, new ComparePerformance());
        return place;
    }

    public int getPlacementParticipant(Participant participant) throws IllegalStateException {
        if (!this.estJouee) {
            throw new IllegalStateException("La compétition n'est pas jouée");
        }
        return getPlacement().indexOf(participant) + 1;
    }

    public Sport getSport() {
        return sport;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Competition)) {
            return false;
        }

        Competition c = (Competition) o;
        return this.sexe.equals(c.sexe) &&
                this.sport.equals(c.sport);
    }

    @Override
    public int hashCode() {
        return this.sexe.hashCode() + this.sport.hashCode();
    }

    public String affichageVue() {
        return "" + this.sexe + " de " + sport.getNom();
    }

    @Override
    public String toString() {
        return "Competition [" + lesParticipants.size() + " Participants, sexe=" + sexe + ", sport=" + sport.getNom()
                + "]";
    }
}