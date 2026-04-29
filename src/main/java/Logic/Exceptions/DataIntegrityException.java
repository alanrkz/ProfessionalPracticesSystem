package Logic.Exceptions;


import java.sql.SQLException;

/**
 *
 * @author alan rkz
 */
public class DataIntegrityException extends SQLException{
    public DataIntegrityException(String massage) {
        super(massage);
    }
    
    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}

