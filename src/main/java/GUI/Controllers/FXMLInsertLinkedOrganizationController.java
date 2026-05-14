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


public class FXMLInsertLinkedOrganizationController implements Initializable {

    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField textFieldCompanyName;
    @FXML
    private TextField textFieldDirectUsers;
    @FXML
    private TextField textFieldIndirectUsers;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private TextArea textAreaAddress;
    @FXML
    private ComboBox<CatalogSocialSector> comboBoxSector;

    
    CatalogSocialSectorDAO catalogSocialSectorDAO = new CatalogSocialSectorDAO();

    public void loadSocialSectors() {
        try {
            
            ObservableList<CatalogSocialSector> observableList = FXCollections.observableList(catalogSocialSectorDAO.getSocialSectors());
            comboBoxSector.setItems(observableList);
            
        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos al cargar el catalogo de sectores sociales");
        }
    }

    @FXML
    public void insertLinkedOrganization() {
        try {
            
            if (valideFields()) {
                if (LogInValidations.validateEmail(textFieldEmail.getText())) {
                    LinkedOrganization linkedOrganization = new LinkedOrganization();
                    linkedOrganization.setCompanyName(textFieldCompanyName.getText());
                    CatalogSocialSector catalogSocialSector = (CatalogSocialSector) comboBoxSector.getValue();
                    linkedOrganization.setSector(catalogSocialSector.getSectorName());
                    linkedOrganization.setDirectUsers(textFieldDirectUsers.getText());
                    linkedOrganization.setIndirectUsers(textFieldIndirectUsers.getText());
                    linkedOrganization.setEmail(textFieldEmail.getText());
                    linkedOrganization.setPhone(textFieldPhone.getText());
                    linkedOrganization.setAddress(textAreaAddress.getText());
                    
                    LinkedOrganizationDAO linkedOrganizationDAO = new LinkedOrganizationDAO();
                    
                    if (linkedOrganizationDAO.registerOrganization(linkedOrganization)) {
                        AlertMessages.showAlert("Registro Exitoso de la Organizacion vinculada");
                        Stage stage = (Stage) buttonInsert.getScene().getWindow();
                        stage.close();
                    }
                } else {
                    AlertMessages.showAlert("Formato de Correo Electronico incorrecto. Por favor ingresalo nuevamente");
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
    
    @FXML
    public void buttonCancel(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSocialSectors();
    }

}
