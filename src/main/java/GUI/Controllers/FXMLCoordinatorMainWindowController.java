package GUI.Controllers;


import Logic.Validations.AlertMessages;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void buttonLinkedOrganizationManage() {
        try {
            Stage currentStage = (Stage) buttonLinkedOrganizationManage.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLManageLinkedOrganization.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestionar Organizacion Vinculada");

            stage.setOnHidden(e -> {
                currentStage.show();
            });

            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Funcionalidad no disponible por el momento");
        }
    }
    
    @FXML
    public void buttonProjectManagment() {
        try {
            Stage currentStage = (Stage) buttonProjectManagment.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLManageProject.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestionar Proyecto");

            stage.setOnHidden(e -> {
                currentStage.show();
            });

            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Funcionalidad no disponible por el momento");
        }
    }
    
    @FXML
    public void buttonProfessorManage() {
        try {
            Stage currentStage = (Stage) buttonProfessorManage.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLManageProfessor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestionar Profesor");

            stage.setOnHidden(e -> {
                currentStage.show();
            });

            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Funcionalidad no disponible por el momento");
        }
    }

    @FXML
    private void clickOnButtonExit(ActionEvent event) {
        ((Stage) buttonExit.getScene().getWindow()).close();
    }
    
}
