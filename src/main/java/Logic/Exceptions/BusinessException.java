package Logic.Exceptions;

/**
 *
 * @author ELLIN JV
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}