package GUI.Controllers;


import Logic.Validations.AlertMessagess;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLMainMenuCoordinatorController implements Initializable {

    @FXML
    private Button buttonProjectManage;
    @FXML
    private Button buttonStudentsManage;
    @FXML
    private Button buttonProfessorManage;
    @FXML
    private Button buttonNotificationManage;
    @FXML
    private Button buttonCourseManage;
    @FXML
    private Button buttonLinkedOrganizationManage;
    @FXML
    private Button buttonExit;

    
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
            AlertMessagess.showAlert("Funcionalidad no disponible por el momento");
        }
    }
    
    @FXML
    public void buttonProjectManage() {
        try {
            Stage currentStage = (Stage) buttonProjectManage.getScene().getWindow();
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
            AlertMessagess.showAlert("Funcionalidad no disponible por el momento");
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
            AlertMessagess.showAlert("Funcionalidad no disponible por el momento");
        }
    }
    
    @FXML
    private void buttonExit() {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
