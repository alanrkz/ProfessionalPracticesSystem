package Logic.Contracts;


import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.util.Optional;

/**
 *
 * @author alan rkz
 */
public interface IUserDAO {
    
    public boolean registerUser(User user) throws DataIntegrityException;
     
    public boolean UpdateUser(User user) throws DataIntegrityException;
    
    public Optional<User> getUserByCredentials(String email, String password) throws DataIntegrityException;
    
}
