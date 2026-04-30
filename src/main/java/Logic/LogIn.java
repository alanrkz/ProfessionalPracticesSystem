package Logic;

import Logic.DAO.CoordinatorDAO;
import Logic.DAO.ProfessorDAO;
import Logic.DAO.StudentDAO;
import Logic.DAO.UserDAO;
import Logic.DTO.LogInResult;
import Logic.DTO.User;
import Logic.Exceptions.BusinessException;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author ELLIN JV
 */
public class LogIn {

    private UserDAO userDAO = new UserDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();

    public LogInResult login(String email, String password) throws BusinessException, DataIntegrityException {

        if (email == null || email.isEmpty()) {
            throw new BusinessException("Correo vacio");
        }

        if (password == null || password.isEmpty()) {
            throw new BusinessException("Contraseña vacia");
        }

        try {
            User user = userDAO.existsUser(email, password);

            if (user == null) {
                throw new BusinessException("Correo o contraseña incorrectos");
            }

            int idUser = user.getIdUser();

            if (coordinatorDAO.getCoordinatorByUserId(idUser)) {
                return new LogInResult(user, "COORDINADOR");
            }

            if (professorDAO.getProfessorByUserId(idUser)) {
                return new LogInResult(user, "PROFESOR");
            }

            if (studentDAO.existsStudent(idUser)) {
                return new LogInResult(user, "ESTUDIANTE");
            }
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Error de conexion con la base de datos");
        }
        
        return null;
    }
}
