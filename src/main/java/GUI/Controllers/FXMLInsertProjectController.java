package GUI.Controllers;


import Logic.DAO.ProjectDAO;
import Logic.DAO.LinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import java.io.IOException;
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


public class FXMLInsertProjectController implements Initializable {

    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField textFieldProjectName;
    @FXML
    private TextField textFieldDuration;
    @FXML
    private TextField textFieldAvailableSpaces;
    @FXML
    private TextField textFieldProjectMethodology;
    @FXML
    private ComboBox comboBoxLinkedOrganization;
    @FXML
    private TextArea textAreaDescription;
    
    LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();

    public void loadLinkedOrganizations() {
        try {
            ObservableList<LinkedOrganization> observableList = FXCollections.observableList(linkedOrganizationDAO.getOrganizations());
            comboBoxLinkedOrganization.setItems(observableList);
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos al cargar las organizaciones vinculadas");
        }
    }

    @FXML
    private void insertProject() throws DataIntegrityException, IOException {
        try {
            if (valideFields()) {
                Project project = new Project();
                project.setProjectName(textFieldProjectName.getText());
                project.setDuration(textFieldDuration.getText());
                project.setDescription(textAreaDescription.getText());
                project.setAvailableSpaces(Integer.parseInt(textFieldAvailableSpaces.getText()));
                project.setProjectMethodology(textFieldProjectMethodology.getText());
                LinkedOrganization linkedOrganization = (LinkedOrganization) comboBoxLinkedOrganization.getValue();
                project.setIdLikedOrganization(linkedOrganization.getIdLikedOrganization());

                ProjectDAO projectDAO = new ProjectDAO();
                if (projectDAO.registerProject(project)) {
                    AlertMessages.showAlert("Proyecto insertado exitosamente");
                }
                
            } else {
                AlertMessages.showAlert("Los campos obligatorios no pueden esatr vacios");
            }
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos");
        }
    }

    public boolean valideFields() {
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
        if (comboBoxLinkedOrganization == null) {
            verified = false;
        }

        return verified;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLinkedOrganizations();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

}
