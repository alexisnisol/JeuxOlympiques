package controller.admin;

import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modele.JeuxOlympiques;
import modele.competitions.Competition;
import modele.exceptions.MauvaisParticipantException;
import modele.exceptions.ParticipantDejaPresentException;
import modele.exceptions.ParticipantOccupeException;
import modele.exceptions.SexeCompetitionException;
import modele.participants.Participant;
import modele.participants.Athlete;
import modele.participants.Equipe;
import modele.sports.Sport;
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
            case AjoutAthlete:
                Athlete athlete = new Athlete(
                        ajout.getNom().getText(),
                        ajout.getPrenom().getText(),
                        ajout.getSexe(),
                        ajout.getForce(),
                        ajout.getEndurance(),
                        ajout.getAgilite(),
                        ajout.getPays());

                Competition competition = ajout.getEpreuveBox().getValue();

                try {
                    for (Competition compet : ajout.getMain().getCompetitions()) {

                    }
                } catch (SexeCompetitionException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur de sexe dans la compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } catch (ParticipantDejaPresentException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Participant déjà présent dans la compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } catch (ParticipantOccupeException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Participant déjà occupé dans une autre compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } catch (MauvaisParticipantException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Mauvais type de participant pour la compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                break;
            case AjoutEquipe:
                Equipe equipe = new Equipe(ajout.getNomEquipe().getText(),
                        JeuxOlympiques.getSportFromName(ajout.getSport()),
                        ajout.getTailleMax(), ajout.getEnRelais(),
                        ajout.getPaysEquipe());

                try {

                    System.out.println("Equipe enregistrée");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Equipe enregistrée");
                    alert.showAndWait();
                    alert.close();
                } catch (SexeCompetitionException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur de sexe dans la compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } catch (ParticipantDejaPresentException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Participant déjà présent dans la compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } catch (ParticipantOccupeException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Participant déjà occupé dans une autre compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                } catch (MauvaisParticipantException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Mauvais type de participant pour la compétition");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
        }
    }
}
