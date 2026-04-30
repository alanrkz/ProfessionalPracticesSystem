package GUI.Controllers;


import Logic.DAO.ProjectDAO;
import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLManageProjectController implements Initializable {

    @FXML
    private Label labelTitleManageProject;
    @FXML
    private Button buttonInsertProject;
    @FXML
    private Label labelSelectProject;
    @FXML
    private ComboBox<Project> comboBoxProjects;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDeactive;
    @FXML
    private Button buttonBack;
    
    ProjectDAO projectDAO = new ProjectDAO();
    
    @FXML
    public void loadProjects() throws DataIntegrityException {
        try {
            ObservableList<Project> observableList = FXCollections.observableList(projectDAO.getProjects());
            comboBoxProjects.setItems(observableList);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Error con la base de datos");
        }
    }
    
    @FXML
    public void buttonInsertLinkedOrganization() {
        try {
            Stage currentStage = (Stage) buttonInsertProject.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLInsertProject.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Insertar Proyecto");

            stage.setOnHidden(e -> {
                currentStage.show();
                try {
                    loadProjects();
                } catch (DataIntegrityException ex) {
                    AlertMessages.showAlert("Error en la base de datos al cargar los proyectos");
                }
            });

            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Funcionalidad no disponible por el momento");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadProjects();
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error con la base de datos al obtener los proyectos");
        }
    }    
    
    @FXML
    public void buttonBack() {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }

}
