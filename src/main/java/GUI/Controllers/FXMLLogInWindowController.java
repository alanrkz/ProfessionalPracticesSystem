package GUI.Controllers;

import Logic.DAO.CoordinatorDAO;
import Logic.DAO.ProfessorDAO;
import Logic.DAO.StudentDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.LogInResult;
import Logic.Exceptions.DataIntegrityException;
import Logic.LogIn;
import Logic.Validations.AlertMessagess;
import Logic.Validations.LogInValidations;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ELLIN JV
 */
public class FXMLLogInWindowController {

    @FXML
    private TextField textFieldEmailLogIn;

    @FXML
    private PasswordField passwordFieldPasswordLogIn;

    private final LogIn loginService = new LogIn(
            new UserDAO(),
            new StudentDAO(),
            new CoordinatorDAO(),
            new ProfessorDAO()
    );

    @FXML
    public void ButtonLogin() throws DataIntegrityException {

        String email = textFieldEmailLogIn.getText();
        String password = passwordFieldPasswordLogIn.getText();

        if (LogInValidations.validateLoginFields(email, password)) {
            try {
                Optional<LogInResult> result = loginService.login(email, password);

                if (result.isEmpty()) {
                    AlertMessagess.showAlert("Usuario no encontrado");
                } else {
                    LogInResult loginResult = result.get();

                    switch (loginResult.getRole()) {
                        case COORDINATOR:
                            AlertMessagess.showAlert("Bienvenido Coordinador: " + loginResult.getUser().getFirstName());
                            openWindow("/fxml/CoordinatorDashboard.fxml", "Menu Principal Coordinador", loginResult);
                            break;
                        case STUDENT:
                            AlertMessagess.showAlert("Bienvenido Estudiante: " + loginResult.getUser().getFirstName());
                            openWindow("/GUI/Views/FXMLMainMenuStudent.fxml", "Menu Principal Estudiantes", loginResult);
                            break;
                        case PROFESSOR:
                            AlertMessagess.showAlert("Bienvenido Profesor: " + loginResult.getUser().getFirstName());
                            openWindow("/GUI/Views/FXMLProfesorMainWindow.fxml", "Menu Principal Profesores", loginResult);
                            break;
                    }
                }
            } catch (DataIntegrityException e) {
                AlertMessagess.showAlert("Error de conexión con la base de datos");
            }
        }
    }

    public void openWindow(String fxmlPath, String title, LogInResult logInResult) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Object controller = loader.getController();
            if (controller instanceof FXMLMainMenuStudentController) {
                ((FXMLMainMenuStudentController) controller).setLogInResult(logInResult);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();

            Stage currentStage = (Stage) textFieldEmailLogIn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            AlertMessagess.showAlert("Error al abrir la ventana");
            e.printStackTrace();
        }
    }

}
