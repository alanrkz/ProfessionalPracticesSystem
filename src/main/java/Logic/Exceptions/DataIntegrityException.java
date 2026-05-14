package Logic.Exceptions;

/**
 *
 * @author alan rkz
 */
public class DataIntegrityException extends Exception{
    public DataIntegrityException(String message) {
        super(message);
    }
    
    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}

