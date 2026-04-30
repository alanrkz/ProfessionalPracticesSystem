package GUI.Controllers;

import Logic.DAO.ProfessorDAO;
import Logic.DTO.Professor;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLManageProfessorController implements Initializable {

    @FXML
    private Button buttonInsertProfessor;
    @FXML
    private ComboBox<Professor> comboBoxProfessors;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDeactive;
    @FXML
    private Button buttonBack;

    ProfessorDAO professorDAO = new ProfessorDAO();

    public void loadProfessors() {
        try {
            ObservableList<Professor> observableList = FXCollections.observableList(professorDAO.getProfessors());
            comboBoxProfessors.setItems(observableList);
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos al cargar los profesores");
        }
    }

    @FXML
    public void buttonInsertProfessor() {
        try {
            Stage currentStage = (Stage) buttonInsertProfessor.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLInsertProfessor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Insertar Profesor");

            stage.setOnHidden(e -> {
                currentStage.show();
                loadProfessors();
            });

            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Funcionalidad no disponible por el momento");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProfessors();
    }

    @FXML
    public void buttonBack(ActionEvent event) {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }

}
