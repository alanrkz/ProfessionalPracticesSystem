package Logic.Contracts;


import Logic.DTO.Coordinator;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author alan rkz
 */
public interface ICoordinatorDAO {
    
    public boolean registerCoordinator(Coordinator coordinator) throws DataIntegrityException;
    
    public boolean deactivateCoordinator(User user, Coordinator coordinator) throws DataIntegrityException;
    
    
    
}
