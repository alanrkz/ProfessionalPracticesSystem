package GUI.Controllers;


import Logic.DAO.ProfessorDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.Professor;
import Logic.DTO.User;
import java.sql.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author ELLIN JV
 */
public class ProfessorController {

    @FXML private TextField txtNumberStaff;
    @FXML private ComboBox<String> cbShift;
    @FXML private DatePicker dpRegistrationDate;
    @FXML private DatePicker dpTerminationDate;
    @FXML private TextField txtServiceTime;
    @FXML private CheckBox chkIsCoordinator;

    @FXML private TextField txtFirstName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;

    private final UserDAO userDAO = new UserDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    @FXML
    private void handleRegister() {

        try {
            User user = new User();
            user.setFirstName(txtFirstName.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());
            user.setStatus(true);

            if (!userDAO.registerUser(user)) {
                showAlert("Error al registrar usuario");
                return;
            }

            Professor professor = new Professor();
            professor.setNumberStaff(txtNumberStaff.getText());
            professor.setShift(cbShift.getValue());
            professor.setRegistrationDate(Date.valueOf(dpRegistrationDate.getValue()));
            professor.setTerminationDate(Date.valueOf(dpTerminationDate.getValue()));
            professor.setIsCoordinator(chkIsCoordinator.isSelected());
            professor.setIdUser(user.getIdUser());

            if (professorDAO.registerProfessor(professor)) {
                showAlert("Profesor registrado correctamente");
            } else {
                showAlert("Error al registrar profesor");
            }

        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
