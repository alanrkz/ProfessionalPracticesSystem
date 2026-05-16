package GUI.Controllers;


import Logic.DAO.ProjectDAO;
import Logic.DTO.Project;
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
public class FXMLDeactivateProjectController implements Initializable {
    @FXML
    private Button buttonYes;
    @FXML
    private Button buttonNo;
    @FXML
    private TextField textFieldProjectName;
    @FXML
    private TextField textFieldIdProject;
    private Project project;

    
    public void loadFields() {
        textFieldProjectName.setText(project.getProjectName());
        textFieldIdProject.setText(String.valueOf(project.getIdProject()));
    }
    
    @FXML
    public void buttonYes () {
        try {
            ProjectDAO projectDAO = new ProjectDAO();
            projectDAO.deactivateProject(project.getIdProject());
            
            if (projectDAO.deactivateProject(project.getIdProject())) {
                AlertMessagess.showAlert("Proyecto inactivado exitosamente");
                Stage stage = (Stage) buttonYes.getScene().getWindow();
                stage.close();
            }
        } catch (DataIntegrityException e) {
            AlertMessagess.showAlert("Error al inactivar el proyecto");
        }
    }
    
    @FXML
    public void buttonNo(ActionEvent event) {
        Stage stage = (Stage) buttonNo.getScene().getWindow();
        stage.close();
    }

    public void setProject(Project project) {
        this.project = project;
        loadFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
}
