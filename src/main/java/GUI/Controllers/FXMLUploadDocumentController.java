package GUI.Controllers;

import Logic.DAO.StudentFilesDAO;
import Logic.DTO.CatalogDocumentType;
import Logic.DTO.LogInResult;
import Logic.DTO.StudentFiles;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan rkz
 */
public class FXMLUploadDocumentController implements Initializable {

    @FXML
    private ComboBox<CatalogDocumentType> comboBoxDocumentType;
    @FXML
    private Button buttonSelectDocument;
    @FXML
    private Button buttonUpload;
    @FXML
    private Button buttonCancel;
    @FXML
    private Label labelDocumentName;
    @FXML
    private Label labelURLDocument;

    private LogInResult logInResult;

    boolean selectedDocument = false;

    StudentFilesDAO studentFilesDAO = new StudentFilesDAO();

    public void loadSocialSectors() {
        try {

            ObservableList<CatalogDocumentType> observableList = FXCollections.observableList(studentFilesDAO.getDocumentTypes());
            comboBoxDocumentType.setItems(observableList);

        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos al cargar el catalogo de tipos de documento");
        }
    }

    @FXML
    public void buttonSelectDocument() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar documento PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF (*.pdf)", "*.pdf"));

        Stage stage = (Stage) buttonSelectDocument.getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            selectedDocument = true;

            String documentName = selectedFile.getName();
            String documentURL = selectedFile.toURI().toString();

            labelDocumentName.setText(documentName);
            labelURLDocument.setText(documentURL);

        } else {
            AlertMessages.showAlert("No se selecciono ningun archivo PDF");
        }
    }

    @FXML
    public void buttonUpload() {
        try {
            if (valideFields()) {
                if (selectedDocument) {
                    StudentFiles studentFiles = new StudentFiles();
                    studentFiles.setDocumentName(labelDocumentName.getText());
                    CatalogDocumentType catalogDocumentType = (CatalogDocumentType) comboBoxDocumentType.getValue();
                    studentFiles.setDocumentType(catalogDocumentType.getNameDocumentType());
                    studentFiles.setDocumentURL(labelURLDocument.getText());
                    studentFiles.setEnrollment(logInResult.getUserIdentifier());

                    if (studentFilesDAO.registerFiles(studentFiles)) {
                        AlertMessages.showAlert("Registro Exitoso del documento ingresado");
                        Stage stage = (Stage) buttonUpload.getScene().getWindow();
                        stage.close();
                    }

                } else {
                    AlertMessages.showAlert("Por favor seleccione un documento");
                }
            } else {
                AlertMessages.showAlert("Por favor seleccione el tipo de documento que quiere subir");
            }

        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error de conexion con la base de datos");
        }
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

    public boolean valideFields() {
        boolean verified = true;

        if (comboBoxDocumentType.getValue() == null) {
            verified = false;
        }

        return verified;
    }

    public void setLogInResult(LogInResult logInResult) {
        this.logInResult = logInResult;
    }

}
