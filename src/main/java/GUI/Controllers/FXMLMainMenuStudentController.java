package GUI.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void buttonExit() {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
    
}
