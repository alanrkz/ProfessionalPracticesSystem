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
public class FXMLMainMenuProfessorController implements Initializable {

    @FXML
    private Button buttonSendNotification;
    @FXML
    private Button buttonEvaluateReport;
    @FXML
    private Button buttonEvaluateSelfAssessment;
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
