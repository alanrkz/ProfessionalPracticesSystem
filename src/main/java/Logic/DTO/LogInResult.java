package Logic.DTO;


import Logic.Enums.UserRole;

/**
 *
 * @author ELLIN JV
 */
public class LogInResult {
    private User user;
    private UserRole role;
    private String userIdentifier;

    
    public LogInResult() {
    }
    
    public LogInResult(User user, UserRole role, String userIdentifier) {
        this.user = user;
        this.role = role;
        this.userIdentifier = userIdentifier;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

}
