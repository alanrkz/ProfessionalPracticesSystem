package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;

/**
 * FXML Controller class
 *
 * @author ELLIN JV
 */
public class CoordinatorDashboardController implements Initializable {

    @FXML
    private SVGPath studentManagementIcon;
    @FXML
    private SVGPath professorManagementIcon;
    @FXML
    private SVGPath courseManagementIcon;
    @FXML
    private SVGPath projectManagementIcon;
    @FXML
    private SVGPath linkedOrganizationIcon;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
