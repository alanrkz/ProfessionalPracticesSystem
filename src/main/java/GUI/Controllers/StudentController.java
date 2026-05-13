package GUI.Controllers;


import Logic.DAO.StudentDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.Student;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessages;
import javafx.fxml.FXML;
import java.sql.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ELLIN JV
 */
public class StudentController {

    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldMiddleName;
    @FXML
    private TextField textFieldPaternalSurname;
    @FXML
    private TextField textFieldMaternalSurname;
    @FXML
    private ComboBox<String> comboBoxGender;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private ComboBox<String> comboBoxStatus;

    @FXML
    private TextField textFieldEnrollment;
    @FXML
    private TextField textFieldNrc;
    @FXML
    private ComboBox<String> comboBoxSector;
    @FXML
    private DatePicker datePickerBirthdate;
    @FXML
    private TextField textFieldHoursCovered;

    @FXML
    private CheckBox chkIsIndigenousLanguage;

    @FXML
    private Button buttonCancel;

    private final UserDAO userDAO = new UserDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    @FXML
    public void initialize() {
        comboBoxGender.getItems().addAll("M", "F", "O");

        comboBoxSector.getItems().addAll("Público", "Privado", "Social");

        comboBoxStatus.getItems().addAll("Activo", "Inactivo");
    }

    @FXML
    private void clickOnButtonRegister() {

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
                AlertMessages.showAlert("Error al registrar usuario");
                return;
            }

            Student student = new Student();          
            student.setEnrollment(textFieldEnrollment.getText());
            student.setNrc(textFieldNrc.getText());
            student.setHoursCovered(Integer.parseInt(textFieldHoursCovered.getText()));
            student.setBirthdate(Date.valueOf(datePickerBirthdate.getValue()));
            student.setIndigenousLanguage(chkIsIndigenousLanguage.isSelected());

            student.setIdUser(user.getIdUser());

            if (student.getIdUser() <= 0) {
                throw new Exception(" ID de usuario inválido (0 o negativo)");
            }

            boolean studentResult = studentDAO.registerStudent(student);

            if (studentResult) {
                AlertMessages.showAlert("Alumno registrado correctamente");
            } else {
                AlertMessages.showAlert("No se pudo registrar el alumno");
            }

        } catch (DataIntegrityException e) {
            AlertMessages.showAlert("Error en base de datos");

        } catch (Exception e) {
            AlertMessages.showAlert("Error: " + e.getMessage());
        }
    }

    @FXML
    private void clickOnButtonCancel() {
        ((Stage) buttonCancel.getScene().getWindow()).close();
    }
}
