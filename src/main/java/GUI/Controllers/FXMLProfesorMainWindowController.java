package GUI.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELLIN JV
 */
public class FXMLProfesorMainWindowController implements Initializable {

    @FXML
    private Label labelProfessorOptions;
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
