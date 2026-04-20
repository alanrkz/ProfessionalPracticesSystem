package Logic.Contracts;


import Logic.DTO.User;

/**
 *
 * @author alan rkz
 */
public interface IUserDAO {
    
     public String registerUser(User user);
     
     public String modifyUser(User user);
    
}
