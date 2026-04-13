package Logic.Exceptions;


import java.sql.SQLException;

/**
 *
 * @author alan rkz
 */
public class DataAccessException  extends SQLException{
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
