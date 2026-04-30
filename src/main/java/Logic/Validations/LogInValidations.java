package Logic.Validations;

/**
 *
 * @author ELLIN JV
 */
public class LogInValidations {

    public static boolean validateEmail(String email) throws IllegalArgumentException {
        boolean flag = true;

        if (email == null || email.trim().isEmpty()) {
            flag = false;
            AlertMessages.showAlert("El correo no puede estar vacío");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            flag = false;
            AlertMessages.showAlert("Formato de correo inválido");
        }

        return flag;
    }

    public static boolean validatePassword(String password) throws IllegalArgumentException {
        boolean flag = true;

        if (password == null || password.trim().isEmpty()) {
            flag = false;
            AlertMessages.showAlert("La contraseña no puede estar vacía");
        } else {
            if (password.length() < 4) {
                flag = false;
                AlertMessages.showAlert("La contraseña es demasiado corta");
            }
        }

        return flag;
    }

    public static boolean validateLoginFields(String email, String password) {
        if (validateEmail(email) && validatePassword(password)) {
            return true;
        } else {
            return false;
        }
    }
}
