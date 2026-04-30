
package GUI.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELLIN JV
 */
public class FXMLCoordinatorMainWindowController implements Initializable {

    @FXML
    private BorderPane fxmlCoordinatorMainWindow;
    @FXML
    private Label labelCoordinatorOptions;
    @FXML
    private Button buttonProjectManagment;
    @FXML
    private Button buttonStudentsManagment;
    @FXML
    private Button buttonProfessorManage;
    @FXML
    private Button buttonNotificationManage;
    @FXML
    private Button buttonCourseManagement;
    @FXML
    private Button buttonLinkedOrganizationManage;
    @FXML
    private Button buttonExit;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickOnButtonProjectsManagment(ActionEvent event) {
    }

    @FXML
    private void clickOnButtonExit(ActionEvent event) {
        ((Stage) buttonExit.getScene().getWindow()).close();
    }
    
}
