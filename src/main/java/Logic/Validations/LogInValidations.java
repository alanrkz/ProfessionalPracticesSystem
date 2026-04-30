package Logic.Validations;

/**
 *
 * @author ELLIN JV
 */
public class LogInValidations {

    public static void validateEmail(String email) throws IllegalArgumentException {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Formato de correo inválido");
        }
    }

    public static void validatePassword(String password) throws IllegalArgumentException {

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        if (password.length() < 4) {
            throw new IllegalArgumentException("La contraseña es demasiado corta");
        }
    }

    public static void validateLoginFields(String email, String password) {
        validateEmail(email);
        validatePassword(password);
    }
}
