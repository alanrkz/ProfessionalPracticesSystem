package Logic;

import Logic.DAO.CoordinatorDAO;
import Logic.DAO.ProfessorDAO;
import Logic.DAO.StudentDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.LogInResult;
import Logic.DTO.User;
import Logic.Enums.UserRole;
import Logic.Exceptions.BusinessException;
import Logic.Exceptions.DataIntegrityException;
import Logic.Validations.AlertMessagess;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ELLIN JV
 */
public class LogIn {

    private final UserDAO userDAO;
    private final StudentDAO studentDAO;
    private final CoordinatorDAO coordinatorDAO;
    private final ProfessorDAO professorDAO;

    public LogIn(UserDAO userDAO, StudentDAO studentDAO, CoordinatorDAO coordinatorDAO, ProfessorDAO professorDAO) {
        this.userDAO = userDAO;
        this.studentDAO = studentDAO;
        this.coordinatorDAO = coordinatorDAO;
        this.professorDAO = professorDAO;
    }

    public Optional<LogInResult> login(String email, String password) throws DataIntegrityException {
        try {

            Optional<User> result = userDAO.getUserByCredentials(email, password);

            if (result.isEmpty()) {
                AlertMessagess.showAlert("Usuario no encontrado");
            }

            User user = result.get();
            int idUser = user.getIdUser();

            if (coordinatorDAO.existsCoordinator(idUser)) {
                String personalNumber = coordinatorDAO.getPersonalNumberByIdUser(idUser);
                return Optional.of(new LogInResult(user, UserRole.COORDINATOR, personalNumber));
            }

            if (professorDAO.existsProfessor(idUser)) {
                String personalNumber = professorDAO.getPersonalNumberByIdUser(idUser);
                return Optional.of(new LogInResult(user, UserRole.PROFESSOR, personalNumber));
            }

            if (studentDAO.existsStudent(idUser)) {
                String enrollment= studentDAO.getEnrollmentByIdUser(idUser);
                return Optional.of(new LogInResult(user, UserRole.STUDENT, enrollment));
            }

            return Optional.empty();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Error de conexión con la base de datos", e);
        }
    }
}
