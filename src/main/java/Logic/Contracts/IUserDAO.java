package Logic.Contracts;

import Logic.DTO.User;


public interface IUserDAO {
    
     public String registerUser(User user);
     
     public String modifyUser(User user);
    
}
