package controller.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.JeuxOlympiques;
import modele.competitions.Competition;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.Pays;
import vue.administrateur.Ajouter.TypeAjout;
import vue.administrateur.Ajouter;

public class ControleurAjouter implements EventHandler<ActionEvent> {

    private JeuxOlympiques modele;
    private Ajouter ajout;
    private TypeAjout typeAjout;

    public ControleurAjouter(JeuxOlympiques modele, Ajouter ajout, TypeAjout typeAjout) {
        this.modele = modele;
        this.ajout = ajout;
        this.typeAjout = typeAjout;
    }

    @Override
    public void handle(ActionEvent event) {

        switch (typeAjout) {
            //Ajout d'un Athlète
            case AjoutAthlete:

                try {
                    if (this.ajout.getNom().getText().isEmpty() || 
                    this.ajout.getPrenom().getText().isEmpty() || 
                    this.ajout.getPays().getText().isEmpty() ||
                    (this.ajout.getEpreuvesAthletesBox().getValue() == null && !this.ajout.athleteDansUneEquipe())) {
                        this.ajout.errorPopupMissingData("Une donnée est manquante").showAndWait();
                        return; //Si un des champs est vide, on arrête la méthode
                    }
                } catch (NumberFormatException e) {
                    this.ajout.errorPopupMissingData("Les champs force, endurance et agilité doivent être des nombres entiers.").showAndWait();
                    return;
                }
                
                Pays pays = this.modele.getPaysFromString(this.ajout.getPays().getText());

                Athlete athlete = new Athlete(
                        ajout.getNom().getText(),
                        ajout.getPrenom().getText(),
                        ajout.getSexe(),
                        ajout.getForce(),
                        ajout.getEndurance(),
                        ajout.getAgilite(),
                        pays);

                //Si on met la checkbox pour ajouter un athlète dans une équipe à false
                //Alors on ajoute l'athlète à une épreuve individuelle
                if (!this.ajout.athleteDansUneEquipe()) {

                    //On récupère la compétition sélectionnée dans la ComboBox
                    Competition competition = ajout.getEpreuvesAthletesBox().getValue();
                    try {
                        competition.enregistrerParticipant(athlete);
                        this.ajout.addPopup("L'athlète " + athlete.obtenirNom() + " a bien été ajouté.").showAndWait();
                        this.ajout.resetTF();

                    } catch (SexeCompetitionException | ParticipantDejaPresentException | ParticipantOccupeException
                            | MauvaisParticipantException exception) {
                        this.ajout.errorPopupMissingData(exception.getMessage()).showAndWait();
                    }
                } else { //Sinon on ajoute l'athlète à une équipe

                    if (this.ajout.getEquipesBox().getValue() == null) {
                        this.ajout.errorPopupMissingData("Une équipe doit être sélectionnée").showAndWait();
                        return;
                    }

                    Equipe equipe = this.ajout.getEquipesBox().getValue();

                    if (equipe.obtenirPays().equals(pays)) {
                        athlete.rejoindreEquipe(equipe);
                        this.ajout.addPopup("L'athlète " + athlete.obtenirNom() + " a rejoint l'équipe.").showAndWait();
                        this.ajout.resetTF();
                    } else {
                        this.ajout.errorPopupMissingData("L'athlète et l'équipe doivent être du même pays").showAndWait();
                    }

                }

                break;


            //Ajout d'une équipe
            case AjoutEquipe:
                Competition competition = ajout.getEpreuveEquipes();
                if (competition == null ||
                this.ajout.getNomEquipe().getText().isEmpty() ||
                this.ajout.getPaysEquipe().getText().isEmpty()) {
                    this.ajout.errorPopupMissingData("Une donnée est manquante").showAndWait();
                    return;
                }

                Pays paysEquipe = this.modele.getPaysFromString(this.ajout.getPaysEquipe().getText());

                Equipe equipe = new Equipe(ajout.getNomEquipe().getText(),
                        competition.getSexe(),
                        competition.getSport(),
                        competition.getSport().getTaille(),
                        paysEquipe);

                try {
                    competition.enregistrerParticipant(equipe);
                    this.ajout.addPopup("L'équipe " + equipe.obtenirNom() + " a bien été ajouté.").showAndWait();
                    this.ajout.resetTF();

                } catch (SexeCompetitionException | ParticipantDejaPresentException | ParticipantOccupeException
                        | MauvaisParticipantException exception) {
                    this.ajout.errorPopupMissingData(exception.getMessage()).showAndWait();
                }
        }
    }
}
