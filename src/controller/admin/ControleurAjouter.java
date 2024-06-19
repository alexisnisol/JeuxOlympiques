package controller.admin;

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
            case AjoutAthlete:

                Pays pays = this.modele.getPaysFromString(this.ajout.getPays().getText());
                

                try{
                    if (this.ajout.getNom().getText().isEmpty() || this.ajout.getPrenom().getText().isEmpty() || this.ajout.getPays().getText().isEmpty() || (this.ajout.getEpreuvesAthletesBox().getValue() == null && !this.ajout.athleteDansUneEquipe())) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur lors de la récupération des données");
                        alert.setContentText("Une donnée est manquante");
                        alert.showAndWait();
                        return;
                    }
                }catch(NumberFormatException e){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur lors de la récupération des données");
                    alert.setContentText("Les champs force, endurance et agilité doivent être des nombres entiers.");
                    alert.showAndWait();
                    return;
                }

            
                Athlete athlete = new Athlete(
                        ajout.getNom().getText(),
                        ajout.getPrenom().getText(),
                        ajout.getSexe(),
                        ajout.getForce(),
                        ajout.getEndurance(),
                        ajout.getAgilite(),
                        pays);

                if(!this.ajout.athleteDansUneEquipe()){

                    Competition competition = ajout.getEpreuvesAthletesBox().getValue();
                    try {
                        competition.enregistrerParticipant(athlete);
                        this.ajout.addPopup("L'athlète " + athlete.obtenirNom() + " a bien été ajouté.").showAndWait();
                        this.ajout.resetTF();

                    } catch (SexeCompetitionException | ParticipantDejaPresentException | ParticipantOccupeException | MauvaisParticipantException exception) {
                        exception.printStackTrace();
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur dans la compétition");
                        alert.setContentText(exception.getMessage());
                        alert.showAndWait();
                    }
                }else{

                    if(this.ajout.getEquipesBox().getValue() == null){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur lors de la récupération des données");
                        alert.setContentText("L'équipe n'existe pas");
                        alert.showAndWait();
                        return;
                    }

                    Equipe equipe = this.ajout.getEquipesBox().getValue();

                    if(equipe.obtenirPays().equals(pays)){
                        athlete.rejoindreEquipe(equipe);
                        this.ajout.addPopup("L'athlète " + athlete.obtenirNom() + " a rejoint l'équipe.").showAndWait();
                        this.ajout.resetTF();                            
                    }else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur lors de la récupération des données");
                        alert.setContentText("Erreur dans le pays");
                        alert.showAndWait();
                    }

                }
                
                break;
            case AjoutEquipe:

                Competition competition = ajout.getEpreuveEquipes();
                if(competition == null || this.ajout.getNomEquipe().getText().isEmpty() || this.ajout.getPaysEquipe().getText().isEmpty()){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur lors de la récupération des données");
                    alert.setContentText("Une donnée est manquante");
                    alert.showAndWait();
                    return;
                }
                
                Pays paysEquipe = this.modele.getPaysFromString(this.ajout.getPaysEquipe().getText());
                
                Equipe equipe = new Equipe(ajout.getNomEquipe().getText(),
                        competition.getSport(),
                        competition.getSport().getTaille(),
                        paysEquipe);
    
                try {
                    competition.enregistrerParticipant(equipe);
                    this.ajout.addPopup("L'équipe " + equipe.obtenirNom() + " a bien été ajouté.").showAndWait();
                    this.ajout.resetTF();

                } catch (SexeCompetitionException | ParticipantDejaPresentException | ParticipantOccupeException | MauvaisParticipantException exception) {
                    exception.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Erreur dans la compétition");
                    alert.setContentText(exception.getMessage());
                    alert.showAndWait();
                }
        }
    }
}
