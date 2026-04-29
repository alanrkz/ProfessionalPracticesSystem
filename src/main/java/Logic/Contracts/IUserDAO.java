package Logic.Contracts;


import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author alan rkz
 */
public interface IUserDAO {
    
    public boolean registerUser(User user) throws DataIntegrityException;
     
    public boolean modifyUser(User user) throws DataIntegrityException;
    
}
