package GUI.Controllers;


import Logic.DAO.LinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessagess;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLDeactivateLinkedOrganizationController implements Initializable {
    @FXML
    private Button buttonYes;
    @FXML
    private Button buttonNo;
    @FXML
    private TextField textFieldIdLinkedOrganization;
    @FXML
    private TextField textFieldLinkedOrganizationName;
    private LinkedOrganization linkedOrganization;
    
    
    public void loadFields() {
        textFieldLinkedOrganizationName.setText(linkedOrganization.getCompanyName());
        textFieldIdLinkedOrganization.setText(String.valueOf(linkedOrganization.getIdLikedOrganization()));
    }
    
    @FXML
    public void buttonYes () {
        try {
            LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();
            linkedOrganizationDAO.deactivateOrganization(linkedOrganization.getIdLikedOrganization());
            
            if (linkedOrganizationDAO.deactivateOrganization(linkedOrganization.getIdLikedOrganization())) {
                AlertMessagess.showAlert("Organizacion vinculada inactivada exitosamente");
                Stage stage = (Stage) buttonYes.getScene().getWindow();
                stage.close();
            }
        } catch (DataIntegrityException e) {
            AlertMessagess.showAlert("Error al inactivar la organizacion vinculada");
        }
    }
    
    @FXML
    public void buttonNo(ActionEvent event) {
        Stage stage = (Stage) buttonNo.getScene().getWindow();
        stage.close();
    }

    public void setLinkedOrganization(LinkedOrganization linkedOrganization) {
        this.linkedOrganization = linkedOrganization;
        loadFields();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
