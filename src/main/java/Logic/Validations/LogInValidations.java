package Logic.Validations;

/**
 *
 * @author ELLIN JV
 */
public class LogInValidations {

    public static boolean validateEmail(String email) {
        boolean validEmail = true;

        if (email == null || email.trim().isEmpty()) {
            validEmail = false;
            AlertMessages.showAlert("El correo no puede estar vacío");
        } else {
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                validEmail = false;
                AlertMessages.showAlert("Formato de correo inválido");
            }
        }

        return validEmail;
    }

    public static boolean validatePassword(String password) {
        boolean validPassword = true;
        int minimumPasswordLength = 4;

        if (password == null || password.trim().isEmpty()) {
            validPassword = false;
            AlertMessages.showAlert("La contraseña no puede estar vacía");
        } else {
            if (password.length() < minimumPasswordLength) {
                validPassword = false;
                AlertMessages.showAlert("La contraseña es demasiado corta");
            }
        }

        return validPassword;
    }

    public static boolean validateLoginFields(String email, String password) {
        boolean validatedLoginFields = false;
        
        if (validateEmail(email) && validatePassword(password)) {
            validatedLoginFields = true;
        }
        
        return validatedLoginFields;
    }
}
