package modele.exceptions;
public class EquipePleineException extends Exception{
    
    public EquipePleineException(){
        super("L'équipe est déjà pleine");
    }
    
    public EquipePleineException(String message){
        super(message);
    }
}
