package modele.exceptions;
public class SexeCompetitionException extends Exception{
    
    public SexeCompetitionException(){
        super("Le sexe du participant ne correspond pas au sexe de la compétition");
    }
    
    public SexeCompetitionException(String message){
        super(message);
    }

    
}
