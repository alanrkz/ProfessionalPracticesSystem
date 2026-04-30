package GUI.Controllers;

import Logic.DAO.LinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLManageLinkedOrganizationController implements Initializable {

    @FXML
    private Label labelTitleManageLinkedOrganization;
    @FXML
    private Button buttonInsertLinkedOrganization;
    @FXML
    private Label labelSelectLinkedOrganization;
    @FXML
    private ComboBox<LinkedOrganization> comboBoxLinkedOrganizations;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDeactive;
    @FXML
    private Button buttonBack;

    LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();

    @FXML
    public void loadLinkedOrganizations() throws DataIntegrityException {
        try {
            ObservableList<LinkedOrganization> observableList = FXCollections.observableList(linkedOrganizationDAO.getOrganizations());
            comboBoxLinkedOrganizations.setItems(observableList);
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexcion con la base de datos al cargar las organizaciones vinculadas");
        }
    }

    @FXML
    public void buttonInsertLinkedOrganization() {
        try {
            Stage currentStage = (Stage) buttonInsertLinkedOrganization.getScene().getWindow();
            currentStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/FXMLInsertLinkedOrganization.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Insertar Organización Vinculada");

            stage.setOnHidden(e -> {
                currentStage.show();
                try {
                    loadLinkedOrganizations();
                } catch (DataIntegrityException ex) {
                    AlertMessages.showAlert("Error en la base de datos al cargar las organizaciones vinculadas");
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
            loadLinkedOrganizations();
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexcion con la base de datos al cargar las organizaciones vinculadas");
        }
    }
    
    @FXML
    public void buttonBack() {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        stage.close();
    }

}
