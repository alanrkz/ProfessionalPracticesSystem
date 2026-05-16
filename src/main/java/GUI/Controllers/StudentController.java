package GUI.Controllers;


import GUI.AlertMessages;
import Logic.DAO.CourseDAO;
import Logic.DAO.StudentDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.Course;
import Logic.DTO.Student;
import Logic.DTO.User;
import Logic.Exceptions.BusinessException;
import Logic.Exceptions.DataIntegrityException;
import Logic.Exceptions.InvalidFormDataException;
import Logic.Validations.StudentRegistrationValidations;
import Logic.Validations.UserValidations;
import javafx.fxml.FXML;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextField textFieldEnrollment;
    @FXML
    private ComboBox<Course> comboBoxCourse;
    @FXML
    private DatePicker datePickerBirthdate;
    @FXML
    private CheckBox checkBoxIndigenousLanguage;
    @FXML
    private Button buttonCancel;

    private final UserDAO userDAO;
    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;
    
    private static final int NO_USER_IDENTIFIER = 0;
    private static final int INITIAL_HOURS_COVERED = 0;    

    
    public StudentController(){
        
        courseDAO = new CourseDAO();
        userDAO = new UserDAO();
        studentDAO = new StudentDAO();
    }

    
    @FXML
    public void initialize() {

        loadComboBoxGenderOptions();
        loadComboBoxCourseOptions();
    }

    @FXML
    private void onRegisterStudentButtonClicked() {

        try {

            User user = createUserFromForm();
            Student student = createStudentFromForm();
            
            UserValidations.validateUserRegistrationData(user);
            StudentRegistrationValidations.validateStudentRegistrationData(student);
            
            registerUser(user);
            assignUserIdentifier(student, user);
            registerStudent(student);
            
            AlertMessages.showInformationAlert("Registro exitoso", "El alumno fue registrado correctamente.");
            closeWindow();

        } catch (InvalidFormDataException exception) {

            AlertMessages.showWarningAlert("Campos inválidos", exception.getMessage());

        } catch (BusinessException exception) {

            AlertMessages.showErrorAlert("Error de negocio", exception.getMessage());

        } catch (DataIntegrityException exception) {

            AlertMessages.showErrorAlert("Error de persistencia", "Ocurrió un problema con la base de datos.");
        }
    }

    private User createUserFromForm() {

        User user = new User();
        user.setFirstName(textFieldFirstName.getText().trim());
        user.setMiddleName(textFieldMiddleName.getText().trim());
        user.setPaternalSurname(textFieldPaternalSurname.getText().trim());
        user.setMaternalSurname(textFieldMaternalSurname.getText().trim());
        user.setGender(comboBoxGender.getValue());
        user.setEmail(textFieldEmail.getText().trim());
        user.setPassword(textFieldPassword.getText().trim());
        user.setStatus(true);
        return user;
    }

    private Student createStudentFromForm() {

        Student student = new Student();
        String enrollment = textFieldEnrollment.getText().trim();
        student.setEnrollment(enrollment);
        Course selectedCourse = comboBoxCourse.getValue();
        student.setNrc(selectedCourse.getNrc());
        int hoursCovered = INITIAL_HOURS_COVERED;
        student.setHoursCovered(hoursCovered);
        student.setBirthdate(Date.valueOf(datePickerBirthdate.getValue()));
        student.setIndigenousLanguage(checkBoxIndigenousLanguage.isSelected());
        return student;
    }

    private void registerUser(User user) throws BusinessException, DataIntegrityException {

        boolean wasUserRegistered = userDAO.registerUser(user);
        if (!wasUserRegistered) {

            throw new BusinessException("No fue posible registrar el usuario.");
        }
    }

    private void assignUserIdentifier(Student student, User user) throws DataIntegrityException {

        int userIdentifier = user.getIdUser();
        if (userIdentifier <= NO_USER_IDENTIFIER) {

            throw new DataIntegrityException("El identificador del usuario es inválido.");
        }
        student.setIdUser(userIdentifier);
    }

    private void registerStudent(Student student) throws DataIntegrityException {

        boolean wasStudentRegistered = studentDAO.registerStudent(student);
        if (!wasStudentRegistered) {

            throw new DataIntegrityException("No fue posible registrar el alumno.");
        }
    }

    private void loadComboBoxCourseOptions() {

        try {
            
            ObservableList<Course> courseList = FXCollections.observableList(courseDAO.getCourses());
            comboBoxCourse.setItems(courseList);
            
        } catch (DataIntegrityException exception) {
            AlertMessages.showErrorAlert("Error", "Error de conexion con la base de datos al cargar las Experiencias Educativas");
        }
    }

    private void loadComboBoxGenderOptions() {

        comboBoxGender.getItems().addAll(
                "M",
                "F",
                "O"
        );
    }

    @FXML
    private void onCancelButtonClicked() {

        closeWindow();
    }

    private void closeWindow() {

        Stage currentWindow = (Stage) buttonCancel.getScene().getWindow();
        currentWindow.close();
    }
    
    
    
}
