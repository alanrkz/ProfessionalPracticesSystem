package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IProfessorDAO;
import Logic.DTO.Professor;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProfessorDAO implements IProfessorDAO {

    private static final Logger logger = Logger.getLogger(ProfessorDAO.class.getName());
    @Override
    public boolean registerProfessor(Professor professor) throws DataIntegrityException {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Profesor VALUES (?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getNumberStaff());
            preparedStatement.setString(2, professor.getShift());
            preparedStatement.setDate(3, professor.getRegistrationDate());
            preparedStatement.setDate(4, professor.getTerminationDate());
            preparedStatement.setString(5, professor.getServiceTime());
            preparedStatement.setBoolean(6, professor.isIsCoordinator());
            preparedStatement.setInt(7, professor.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se inserto ningun registro para el profesor con numero: " + professor.getNumberStaff());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al registrar profesor con numero: " + professor.getNumberStaff(), e);
            throw new DataIntegrityException("Tuvimos problemas para registrar un nuevo profesor. Intentelo mas tarde", e);
        }
    }

    @Override
    public boolean deactivateProfessor(User user, Professor professor) throws DataIntegrityException {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se desactivo ningun usuario con id: " + professor.getIdUser());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al desactivar profesor con idUsuario: " + professor.getIdUser(), e);
            throw new DataIntegrityException("Tuvimos problemas para desactivar al profesor. Intentelo mas tarde", e);
        }
    }

    @Override
    public boolean updateProfessor(Professor professor) throws DataIntegrityException {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Profesor SET turno = ?, esCoordinador = ? WHERE numeroPersonal = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getShift());
            preparedStatement.setBoolean(2, professor.isIsCoordinator());
            preparedStatement.setString(3, professor.getNumberStaff());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se actualizo el profesor con numero: " + professor.getNumberStaff());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar profesor con numero: " + professor.getNumberStaff(), e);
            throw new DataIntegrityException("Tuvimos problemas para actualizar al profesor. Intentelo mas tarde", e);
        }
    }

    @Override
    public ArrayList<Professor> getProfessors() throws DataIntegrityException {

        ArrayList<Professor> listProfessors = new ArrayList<>();
        String query = "SELECT * FROM Profesor;";

        try (Connection connection = DatabaseConnection.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Professor professor = new Professor();
                professor.setNumberStaff(resultSet.getString("numeroPersonal"));
                professor.setShift(resultSet.getString("turno"));
                professor.setRegistrationDate(resultSet.getDate("fechaRegistro"));
                professor.setTerminationDate(resultSet.getDate("fechaBaja"));
                professor.setServiceTime(resultSet.getString("tiempoServicio"));
                professor.setIsCoordinator(resultSet.getBoolean("esCoordinador"));
                professor.setIdUser(resultSet.getInt("idUsuario"));

                listProfessors.add(professor);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener lista de profesores", e);
            throw new DataIntegrityException("Tuvimos problemas para obtener a los profesores. Intentelo mas tarde", e);
        }

        return listProfessors;
    }

}
