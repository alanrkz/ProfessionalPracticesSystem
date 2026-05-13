package Logic.DTO;


import Logic.Enums.UserRole;

/**
 *
 * @author ELLIN JV
 */
public class LogInResult {
    private User user;
    private UserRole role;

    
    public LogInResult(User user, UserRole role) {
        this.user = user;
        this.role = role;
    }

    
    public User getUser() {
        return user;
    }

    public UserRole getUserRole() {
        return role;
    }

}
