package Logic.Contracts;


import Logic.DTO.Coordinator;
import Logic.DTO.User;

/**
 *
 * @author alan rkz
 */
public interface ICoordinatorDAO {
    
    public String registerCoordinator(Coordinator coordinator);
    
    public String deactivateCoordinator(User user, Coordinator coordinator);
    
}
