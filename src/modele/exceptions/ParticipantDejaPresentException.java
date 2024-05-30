package modele.exceptions;
public class ParticipantDejaPresentException extends Exception{
    
    public ParticipantDejaPresentException(){
        super("Le participant est déjà inscrit à cette compétition");
    }
    
    public ParticipantDejaPresentException(String message){
        super(message);
    }
}
