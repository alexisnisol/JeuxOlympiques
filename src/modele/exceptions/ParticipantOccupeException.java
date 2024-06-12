package modele.exceptions;
public class ParticipantOccupeException extends Exception{
    
    public ParticipantOccupeException(){
        super("Le participant est déjà inscrit à une compétition");
    }
    
    public ParticipantOccupeException(String message){
        super(message);
    }
}
