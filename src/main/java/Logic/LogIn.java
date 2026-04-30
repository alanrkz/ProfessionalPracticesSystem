package Logic;


import Logic.DAO.CoordinatorDAO;
import Logic.DAO.ProfessorDAO;
import Logic.DAO.StudentDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.LogInResult;
import Logic.DTO.User;

/**
 *
 * @author ELLIN JV
 */
public class LogIn {
    private UserDAO userDAO = new UserDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();

    public LogInResult login(String email, String password) throws Exception {

        if (email == null || email.isEmpty()) {
            throw new Exception("Correo vacío");
        }

        if (password == null || password.isEmpty()) {
            throw new Exception("Contraseña vacía");
        }

        User user = userDAO.login(email, password);

        if (user == null) {
            throw new Exception("Correo o contraseña incorrectos");
        }

        int idUser = user.getIdUser();

        if (coordinatorDAO.getCoordinatorByUserId(idUser)) {
            return new LogInResult(user, "COORDINADOR");
        }

        if (studentDAO.getStudentByUserId(idUser)) {
            return new LogInResult(user, "ESTUDIANTE");
        }

        if (professorDAO.getProfessorByUserId(idUser)) {
            return new LogInResult(user, "PROFESOR");
        }

        throw new Exception("Usuario sin rol asignado");
    }
}
