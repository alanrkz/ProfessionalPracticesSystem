package Logic.Exceptions;

/**
 *
 * @author ELLIN JV
 */
public class InvalidFormDataException extends BusinessException{
    
    public InvalidFormDataException(String message) {
        super(message);
    }

    public InvalidFormDataException(String message, Throwable cause) {
        super(message, cause);
    }  
    
}
