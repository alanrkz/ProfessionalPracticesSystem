package GUI.Controllers;

import Logic.DAO.UserDAO;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author ELLIN JV
 */
public class LogInController {

    @FXML
    private TextField textFieldEmailLogIn;

    @FXML
    private PasswordField passwordFieldPasswordLogIn;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void clickOnButtonLogin() {

        String email = textFieldEmailLogIn.getText();
        String password = passwordFieldPasswordLogIn.getText();

        try {
            User user = userDAO.login(email, password);

            if (user == null) {
                showAlert("Correo o contraseña incorrectos");
                return;
            }

            showAlert("Bienvenido " + user.getFirstName());

            // 
        } catch (DataIntegrityException e) {
            showAlert("Error con la base de datos");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
