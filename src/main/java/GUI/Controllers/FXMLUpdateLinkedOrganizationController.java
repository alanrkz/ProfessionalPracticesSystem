package GUI.Controllers;

import Logic.DAO.CatalogSocialSectorDAO;
import Logic.DAO.LinkedOrganizationDAO;
import Logic.DTO.CatalogSocialSector;
import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import Logic.Validations.LogInValidations;
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

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLUpdateLinkedOrganizationController implements Initializable {

    @FXML
    private TextField textFieldNameOrganization;
    @FXML
    private TextField textFieldDirectUsers;
    @FXML
    private TextField textFieldIndirectUsers;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private ComboBox<CatalogSocialSector> comboBoxSector;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextArea textAreaAddress;
    private LinkedOrganization linkedOrganization;

    CatalogSocialSectorDAO catalogSocialSectorDAO = new CatalogSocialSectorDAO();

    public void loadSocialSectors() {
        try {
            ObservableList<CatalogSocialSector> observableList = FXCollections.observableList(catalogSocialSectorDAO.getSocialSectors());
            comboBoxSector.setItems(observableList);
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos al cargar el catalogo de sectores sociales");
        }
    }

    public void loadFields() {
        textFieldNameOrganization.setText(linkedOrganization.getCompanyName());
        textFieldDirectUsers.setText(linkedOrganization.getDirectUsers());
        textFieldIndirectUsers.setText(linkedOrganization.getIndirectUsers());
        textFieldEmail.setText(linkedOrganization.getEmail());
        textFieldPhone.setText(linkedOrganization.getPhone());
        textAreaAddress.setText(linkedOrganization.getAddress());

    }

    @FXML
    public void buttonCancel(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buttonUpdate() {
        try {
            if (valideFields()) {
                if (LogInValidations.validateEmail(textFieldEmail.getText())) {
                    LinkedOrganization updatedOrganization = new LinkedOrganization();
                    updatedOrganization.setIdLikedOrganization(linkedOrganization.getIdLikedOrganization());
                    updatedOrganization.setCompanyName(textFieldNameOrganization.getText());
                    CatalogSocialSector catalogSocialSector = (CatalogSocialSector) comboBoxSector.getValue();
                    updatedOrganization.setSector(catalogSocialSector.getSectorName());
                    updatedOrganization.setDirectUsers(textFieldDirectUsers.getText());
                    updatedOrganization.setIndirectUsers(textFieldIndirectUsers.getText());
                    updatedOrganization.setEmail(textFieldEmail.getText());
                    updatedOrganization.setPhone(textFieldPhone.getText());
                    updatedOrganization.setAddress(textAreaAddress.getText());

                    LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();

                    if (linkedOrganizationDAO.updateOrganization(updatedOrganization)) {
                        AlertMessages.showAlert("Actualizacion Existosa de la Organizacion vinculada");
                        Stage stage = (Stage) buttonUpdate.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    AlertMessages.showAlert("Formato de Correo Electronico incorrecto. Por favor ingresalo nuevamente");
                }

            } else {
                AlertMessages.showAlert("Los campos obligatorios no pueden estar vacios");
            }
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexcion con la base de datos");
        }
    }

    public boolean valideFields() {
        boolean verified = true;

        if (textFieldNameOrganization.getText() == null || textFieldNameOrganization.getText().trim().isEmpty()) {
            verified = false;
        }

        if (comboBoxSector.getValue() == null) {
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

        if (textAreaAddress.getText() == null || textAreaAddress.getText().trim().isEmpty()) {
            verified = false;
        }

        return verified;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSocialSectors();
    }

    public void setLinkedOrganization(LinkedOrganization linkedOrganization) {
        this.linkedOrganization = linkedOrganization;
        loadFields();
    }

}
