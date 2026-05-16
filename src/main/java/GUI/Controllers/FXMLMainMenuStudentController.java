package GUI.Controllers;

import Logic.DTO.LogInResult;
import Logic.Validations.AlertMessagess;
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
 * @author alan rkz
 */
public class FXMLMainMenuStudentController implements Initializable {

    @FXML
    private Button buttonApplyProject;
    @FXML
    private Button buttonGenerateReport;
    @FXML
    private Button buttonGenerateSelfAssessment;
    @FXML
    private Button buttonShowNotifications;
    @FXML
    private Button buttonUploadDocuments;
    @FXML
    private Button buttonDownloadDocuments;
    @FXML
    private Button buttonExit;

    private LogInResult logInResult;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void buttonUploadDocuments() {
        try {
            Stage currentStage = (Stage) buttonUploadDocuments.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLUploadDocument.fxml"));
            Parent root = loader.load();

            FXMLUploadDocumentController controller = loader.getController();
            controller.setLogInResult(logInResult);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Subir Documento");

            stage.setOnHidden(e -> {
                currentStage.show();
            });

            stage.show();

        } catch (IOException e) {
            AlertMessagess.showAlert("Funcionalidad no disponible por el momento");
        }
    }

    @FXML
    public void buttonExit() {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    public void setLogInResult(LogInResult logInResult) {
        this.logInResult = logInResult;
    }

}
