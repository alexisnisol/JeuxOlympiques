package modele.exceptions;
public class CompetitionPleineException extends Exception{
    
    public CompetitionPleineException(){
        super("La compétition est déjà pleine");
    }
    
    public CompetitionPleineException(String message){
        super(message);
    }
}
