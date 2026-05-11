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
    public void loadProjects() {
        try {
            ObservableList<Project> observableList = FXCollections.observableList(projectDAO.getProjects());
            comboBoxProjects.setItems(observableList);
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos al cargar los proyectos");
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
                    loadProjects();
            });

            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Funcionalidad no disponible por el momento");
        }
    }
    
    @FXML
    public void buttonUpdate() {
        Project selectedProject = comboBoxProjects.getSelectionModel().getSelectedItem();

        if (selectedProject == null) {
            AlertMessages.showAlert("Seleccione un proyecto");
        } else {
            try {
                Stage currentStage = (Stage) buttonUpdate.getScene().getWindow();
                currentStage.hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLUpdateProject.fxml"));
                Parent root = loader.load();

                FXMLUpdateProjectController controller = loader.getController();
                controller.setProject(selectedProject);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Actualizar Proyecto");

                stage.setOnHidden(e -> {
                    currentStage.show();
                    loadProjects();
                });

                stage.show();

            } catch (IOException e) {
                AlertMessages.showAlert("Funcionalidad no disponible por el momento");
            }
        }
    }
    
    @FXML
    public void buttonDeactive() {
        Project selectedProject = comboBoxProjects.getSelectionModel().getSelectedItem();

        if (selectedProject == null) {
            AlertMessages.showAlert("Seleccione un proyecto");
        } else {
            try {
                Stage currentStage = (Stage) buttonDeactive.getScene().getWindow();
                currentStage.hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLDeactivateProject.fxml"));
                Parent root = loader.load();

                FXMLDeactivateProjectController controller = loader.getController();
                controller.setProject(selectedProject);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Inactivar Proyecto");

                stage.setOnHidden(e -> {
                    currentStage.show();
                    loadProjects();
                });

                stage.show();

            } catch (IOException e) {
                AlertMessages.showAlert("Funcionalidad no disponible por el momento");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProjects();
    }    
    
    @FXML
    public void buttonBack() {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }

}
