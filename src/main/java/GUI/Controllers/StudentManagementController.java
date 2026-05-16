/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI.Controllers;

import GUI.AlertMessages;
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
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELLIN JV
 */
public class StudentManagementController implements Initializable {

    @FXML
    private Button buttonStudentManagement;
    @FXML
    private SVGPath IconStudent;
    @FXML
    private Button buttonProfessorManagement;
    @FXML
    private SVGPath iconProfessor;
    @FXML
    private Button buttonCourseManagement;
    @FXML
    private SVGPath iconCourseManagement;
    @FXML
    private Button buttonProjectManagement;
    @FXML
    private SVGPath iconProjectManagement;
    @FXML
    private Button buttonLinkedOrganizationManagement;
    @FXML
    private Button buttonMainView;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonRegisterNewStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerNewStudent () {

        try {
            
            Stage currentStage = (Stage) buttonRegisterNewStudent.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/StudentRegistrationForm.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Formulario - Registro de Estudiante");

            stage.setOnHidden(e -> {
                currentStage.show();
            });
            stage.show();

        } catch (IOException e) {
            AlertMessagess.showAlert("Funcionalidad no disponible por el momento");
        }
    }
    
    @FXML
    private void loadProfessorManagementView(ActionEvent event) {

        loadView(event, "/fxml/ProfessorManagement.fxml");
    }

    @FXML
    private void loadStudentManagementView(ActionEvent event) {

        loadView(event, "/fxml/StudentManagement.fxml");
    }

    @FXML
    private void loadProjectManagementView() {

        try {
            Stage currentStage = (Stage) buttonProjectManagement.getScene().getWindow();
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
    private void loadLinkedOrganizationManagementView () {

        try {
            
            Stage currentStage = (Stage) buttonLinkedOrganizationManagement.getScene().getWindow();
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
    private void exitWindow(ActionEvent event) {

        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    private void loadView(ActionEvent event, String path) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (IOException exception) {

            AlertMessages.showErrorAlert("Error", "No se pudo cargar la vista");
        }
    }

    @FXML
    private void loadMainView(ActionEvent event) {
        
        loadView(event, "/fxml/CoordinatorDashboard.fxml");
    }
    
}
