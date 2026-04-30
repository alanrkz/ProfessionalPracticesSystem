package GUI;

import Logic.DAO.ProjectDAO;
import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;
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
    private TextField textFieldDescription;
    @FXML
    private TextField textFieldAvailableSpaces;
    @FXML
    private TextField textFieldProjectMethodology;
    @FXML
    private TextField textFieldIdLikedOrganitation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void insertProject(ActionEvent event) throws DataIntegrityException, IOException {
        if (valideFields()) {
            Project project = new Project();
            project.setProjectName(textFieldProjectName.getText());
            project.setDuration(textFieldDuration.getText());
            project.setDescription(textFieldDescription.getText());
            project.setAvailableSpaces(Integer.parseInt(textFieldAvailableSpaces.getText()));
            project.setProjectMethodology(textFieldProjectMethodology.getText());
            project.setIdLikedOrganization(Integer.parseInt(textFieldIdLikedOrganitation.getText()));

            ProjectDAO projectDAO = new ProjectDAO();
            if (projectDAO.registerProject(project)) {
                Label label = new Label("Insertado Exitosamente");
                Scene scene = new Scene(label, 400, 180);
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWindowSuccessMessage.fxml"));
                scene.setRoot(parent);
                Stage stage = (Stage) textFieldProjectName.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Insertado Exitosamente");
                stage.show();
            } else {
                Label label = new Label("No se pudo insertar correctamente");
                Scene scene = new Scene(label, 400, 180);
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWindowAdviceMessage.fxml"));
                scene.setRoot(parent);
                Stage stage = (Stage) textFieldProjectName.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Problemas para insertar");
                stage.show();
            }
        } else {
            Label label = new Label("Campos vacios");
            Scene scene = new Scene(label, 400, 180);
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLWindowEmptyFieldsMessage.fxml"));
            scene.setRoot(parent);
            Stage stage = (Stage) textFieldProjectName.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Campos vacios");
            stage.show();
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
        if (textFieldDescription.getText() == null || textFieldDescription.getText().trim().isEmpty()) {
            verified = false;
        }
        if (textFieldAvailableSpaces.getText() == null || textFieldAvailableSpaces.getText().trim().isEmpty()) {
            verified = false;
        }
        if (textFieldProjectMethodology.getText() == null || textFieldProjectMethodology.getText().trim().isEmpty()) {
            verified = false;
        }
        if (textFieldIdLikedOrganitation.getText() == null || textFieldIdLikedOrganitation.getText().trim().isEmpty()) {
            verified = false;
        }

        return verified;
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) buttonInsert.getScene().getWindow();
        stage.close();
    }

}
