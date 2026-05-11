package GUI.Controllers;

import Logic.DAO.LinkedOrganizationDAO;
import Logic.DAO.ProjectDAO;
import Logic.DTO.LinkedOrganization;
import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLUpdateProjectController implements Initializable {

    @FXML
    private TextField textFieldProjectName;
    @FXML
    private TextField textFieldDuration;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private TextField textFieldAvailableSpaces;
    @FXML
    private TextField textFieldProjectMethodology;
    @FXML
    private ComboBox<LinkedOrganization> comboBoxLinkedOrganization;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonCancel;
    private Project project;

    private ProjectDAO projectDAO = new ProjectDAO();

    private LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();

    public void loadLinkedOrganizations() {
        try {
            ObservableList<LinkedOrganization> observableList = FXCollections.observableList(linkedOrganizationDAO.getOrganizations());
            comboBoxLinkedOrganization.setItems(observableList);
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error al cargar las organizaciones vinculadas");
        }
    }

    public void loadFields() {
        textFieldProjectName.setText(project.getProjectName());
        textFieldDuration.setText(project.getDuration());
        textAreaDescription.setText(project.getDescription());
        textFieldAvailableSpaces.setText(String.valueOf(project.getAvailableSpaces()));
        textFieldProjectMethodology.setText(project.getProjectMethodology());
    }

    @FXML
    public void buttonUpdate() {

        try {
            if (validateFields()) {
                Project updatedProject = new Project();
                updatedProject.setIdProject(project.getIdProject());
                updatedProject.setProjectName(textFieldProjectName.getText());
                updatedProject.setDuration(textFieldDuration.getText());
                updatedProject.setDescription(textAreaDescription.getText());
                updatedProject.setAvailableSpaces(Integer.parseInt(textFieldAvailableSpaces.getText()));
                updatedProject.setProjectMethodology(textFieldProjectMethodology.getText());
                LinkedOrganization linkedOrganization = comboBoxLinkedOrganization.getValue();
                updatedProject.setIdLikedOrganization(linkedOrganization.getIdLikedOrganization());

                if (projectDAO.updateProject(updatedProject)) {
                    AlertMessages.showAlert("Actualizacion exitosa del proyecto");
                    Stage stage = (Stage) buttonUpdate.getScene().getWindow();
                    stage.close();
                }
            } else {
                AlertMessages.showAlert("Los campos obligatorios no pueden estar vacios");
            }
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos");
        }
        
    }

    @FXML
    public void buttonCancel(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public boolean validateFields() {
        boolean verified = true;

        if (textFieldProjectName.getText() == null || textFieldProjectName.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldDuration.getText() == null || textFieldDuration.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textAreaDescription.getText() == null || textAreaDescription.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldAvailableSpaces.getText() == null || textFieldAvailableSpaces.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldProjectMethodology.getText() == null || textFieldProjectMethodology.getText().trim().isEmpty()) {
            verified = false;
        }

        if (comboBoxLinkedOrganization.getValue() == null) {
            verified = false;
        }

        return verified;
    }

    public void setProject(Project project) {
        this.project = project;
        loadFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLinkedOrganizations();
    }

}
