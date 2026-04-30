package GUI.Controllers;

import Logic.DTO.LogInResult;
import Logic.Exceptions.DataIntegrityException;
import Logic.LogIn;
import Logic.Validations.AlertMessages;
import Logic.Validations.LogInValidations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ELLIN JV
 */
public class LogInController {

    @FXML
    private TextField textFieldEmailLogIn;

    @FXML
    private PasswordField passwordFieldPasswordLogIn;

    private final LogIn loginService = new LogIn();

    @FXML
    private void clickOnButtonLogin() {

        String email = textFieldEmailLogIn.getText();
        String password = passwordFieldPasswordLogIn.getText();

        if (LogInValidations.validateLoginFields(email, password)) {
            try {
                LogInResult result = loginService.login(email, password);

                switch (result.getRole()) {

                    case "COORDINADOR":
                        AlertMessages.showAlert("Bienvenido Coordinador: " + result.getUser().getFirstName());
                        openWindow("/GUI/Views/CoordinatorMenuWindow.fxml", "Menu Principal Coordinador");
                        break;

                    case "ESTUDIANTE":
                        AlertMessages.showAlert("Bienvenido Estudiante: " + result.getUser().getFirstName());
                        openWindow("/GUI/Views/FXMLMainMenuStudent.fxml", "Menu Principal Estudiantes");
                        break;

                    case "PROFESOR":
                        AlertMessages.showAlert("Bienvenido Profesor: " + result.getUser().getFirstName());
                        openWindow("/GUI/Views/FXMLMainMenuProfessor.fxml", "Menu Principal Profesores");
                        break;
                }
            } catch (DataIntegrityException e) {
                AlertMessages.showAlert("Error con la base de datos");
            } catch (Exception e) {
                AlertMessages.showAlert("Usuario no encontrado");
            }
        }
    }

    private void openWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();

            Stage currentStage = (Stage) textFieldEmailLogIn.getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            AlertMessages.showAlert("Error al abrir la ventana");
        }
    }
}
