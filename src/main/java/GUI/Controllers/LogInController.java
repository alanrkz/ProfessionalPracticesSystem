package GUI.Controllers;

import Logic.DTO.LogInResult;
import Logic.Exceptions.DataIntegrityException;
import Logic.LogIn;
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

        try {

            LogInValidations.validateLoginFields(email, password);
            LogInResult result = loginService.login(email, password);

            switch (result.getRole()) {

                case "COORDINADOR":
                    showAlert("Bienvenido Coordinador: " + result.getUser().getFirstName());
                    openWindow("/GUI/Views/CoordinatorMenuWindow.fxml", "Ventana Principal Coordinador");
                    break;

                case "ESTUDIANTE":
                    showAlert("Bienvenido Estudiante: " + result.getUser().getFirstName());
                    //openWindow("/GUI/Views/FXMLProfessorMainWindow.fxml", "Ventana Principal Profesor");
                    break;

                case "PROFESOR":
                    showAlert("Bienvenido Profesor: " + result.getUser().getFirstName());
                    openWindow("/fxml/FXMLProfesorMainWindow.fxml", "Ventana Principal Profesor");
                    break;
            }

        } catch (IllegalArgumentException e) {
            showAlert(e.getMessage());

        } catch (DataIntegrityException e) {
            showAlert("Error con la base de datos");

        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
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
            e.printStackTrace();
            showAlert("Error al abrir la ventana: " + e.getMessage());
        }
    }
}
