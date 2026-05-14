package GUI.Controllers;

import GUI.AlertMessages;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELLIN JV
 */
public class StudentManagmentController implements Initializable {

    @FXML
    private Button buttonRegisterNewStudent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void registerNewStudent() {
        try {
            Stage currentStage = (Stage) buttonRegisterNewStudent.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/StudentRegistrationForm.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registrar Estudiante");

            stage.show();

        } catch (IOException e) {
            AlertMessages.showWarningAlert("Advertencia","Funcionalidad no disponible por el momento");
            e.printStackTrace();
        }
    }

}
