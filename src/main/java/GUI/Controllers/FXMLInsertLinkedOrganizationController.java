package GUI.Controllers;

import Logic.DAO.LinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataIntegrityException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLInsertLinkedOrganizationController implements Initializable {

    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField textFieldCompanyName;
    @FXML
    private TextField textFieldSector;
    @FXML
    private TextField textFieldDirectUsers;
    @FXML
    private TextField textFieldIndirectUsers;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldAddress;

    @FXML
    public void insertLinkedOrganization() {
    try { 
        if (valideFields()) {
            LinkedOrganization linkedOrganization = new LinkedOrganization();
            linkedOrganization.setCompanyName(textFieldCompanyName.getText());
            linkedOrganization.setSector(textFieldSector.getText());
            linkedOrganization.setDirectUsers(textFieldDirectUsers.getText());
            linkedOrganization.setIndirectUsers(textFieldIndirectUsers.getText());
            linkedOrganization.setEmail(textFieldEmail.getText());
            linkedOrganization.setPhone(textFieldPhone.getText());
            linkedOrganization.setCity(textFieldCity.getText());
            linkedOrganization.setAddress(textFieldAddress.getText());

            LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();

            if (linkedOrganizationDAO.registerOrganization(linkedOrganization)) {
                AlertMessages.showAlert("Registro Exitoso de la Organizacion vinculada");
            }
            
        } else {
            AlertMessages.showAlert("Los campos obligatorios no pueden esatr vacios");
        }
    } catch (DataIntegrityException e) {
        AlertMessages.showAlert("Error de conexcion con la base de datos");
    }
    }

    public boolean valideFields() {
        boolean verified = true;

        if (textFieldCompanyName.getText() == null || textFieldCompanyName.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldSector.getText() == null || textFieldSector.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldDirectUsers.getText() == null || textFieldDirectUsers.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldIndirectUsers.getText() == null || textFieldIndirectUsers.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldEmail.getText() == null || textFieldEmail.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldPhone.getText() == null || textFieldPhone.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldCity.getText() == null || textFieldCity.getText().trim().isEmpty()) {
            verified = false;
        }

        if (textFieldAddress.getText() == null || textFieldAddress.getText().trim().isEmpty()) {
            verified = false;
        }

        return verified;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

}
