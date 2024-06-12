package modele.exceptions;

public class MauvaisParticipantException extends Exception{
    
    public MauvaisParticipantException(){
        super("Le participant ne peut pas participer à cette compétition.");
    }
    
    public MauvaisParticipantException(String message){
        super(message);
    }
}
