package Logic.DTO;

/**
 *
 * @author ELLIN JV
 */
public class LogInResult {
    private User user;
    private String role;

    public LogInResult(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }
}
