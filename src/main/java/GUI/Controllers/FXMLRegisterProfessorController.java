package GUI.Controllers;


import Logic.DAO.ProfessorDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.Professor;
import Logic.DTO.User;
import Logic.Exceptions.BusinessException;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessagess;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELLIN JV
 */
public class FXMLRegisterProfessorController implements Initializable {

    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonCancel;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldMiddleName;
    @FXML
    private TextField textFieldPaternalSurname;
    @FXML
    private Label labelFirstName;
    @FXML
    private TextField textFieldMaternalSurname;
    @FXML
    private Label labelPaternalSurname;
    @FXML
    private Label labelMaternalSurname;
    @FXML
    private Label labelGender;
    @FXML
    private Label labelMiddleName;
    @FXML
    private Label labelStaffNumber;
    @FXML
    private Label labelEmail;
    @FXML
    private ComboBox<String> comboBoxGender;
    @FXML
    private Label labelProfessorPersonalData;
    @FXML
    private Label labelProfessorUserData;
    @FXML
    private TextField textFieldStaffNumber;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private Label labelProfessorScholarData;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private Label labelPassword;
    @FXML
    private Label labelAssignAsCoordinator;
    @FXML
    private Label labelShift;
    @FXML
    private TextField textFieldShift;
    @FXML
    private Label labelStatus;
    @FXML
    private ComboBox<String> comboBoxStatus;
    @FXML
    private ComboBox<String> comboBoxIsCoordinator;
    @FXML
    private Label labelRegisterProfessor;

    private final UserDAO userDAO = new UserDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    @FXML
    public void initialize() {
        comboBoxGender.getItems().addAll("M", "F");

        comboBoxIsCoordinator.getItems().addAll("Sí", "No");

        comboBoxStatus.getItems().addAll("Activo", "Inactivo");
    }

    @FXML
    private void onButtonRegisterClicked (ActionEvent event) throws Exception {
        try {

            User user = new User();

            user.setFirstName(textFieldFirstName.getText());
            user.setMiddleName(textFieldMiddleName.getText());
            user.setPaternalSurname(textFieldPaternalSurname.getText());
            user.setMaternalSurname(textFieldMaternalSurname.getText());
            user.setGender(comboBoxGender.getValue());
            user.setEmail(textFieldEmail.getText());
            user.setPassword(textFieldPassword.getText());
            user.setStatus(true);

            boolean userResult = userDAO.registerUser(user);

            if (!userResult) {
                AlertMessagess.showAlert("Error al registrar usuario");
                return;
            }

            Professor professor = new Professor();

            professor.setNumberStaff(
                    textFieldStaffNumber.getText()
            );

            professor.setShift(
                    textFieldShift.getText()
            );

            boolean isCoordinator
                    = comboBoxIsCoordinator.getValue().equals("Sí");

            professor.setIsCoordinator(isCoordinator);

            professor.setIdUser(user.getIdUser());

            if (professor.getIdUser() <= 0) {

                throw new Exception(
                        "Invalid user ID"
                );
            }

            boolean professorResult = professorDAO.registerProfessor(professor);

            if (professorResult) {

                AlertMessagess.showAlert(
                        "Profesor registrado correctamente"
                );

            } else {

                AlertMessagess.showAlert(
                        "No se pudo registrar el profesor"
                );
            }

        } catch (DataIntegrityException e) {

            AlertMessagess.showAlert("Error en base de datos");

        } catch (BusinessException e) {

            AlertMessagess.showAlert(
                    "Error: " + e.getMessage()
            );
        }
    }

    @FXML
    private void onButtonCancelClicekd(ActionEvent event) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
