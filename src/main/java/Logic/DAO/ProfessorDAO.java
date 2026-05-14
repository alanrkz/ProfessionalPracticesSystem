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

        boolean successfulRegister = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Profesor VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getNumberStaff());
            preparedStatement.setString(2, professor.getShift());
            preparedStatement.setDate(3, professor.getRegistrationDate());
            preparedStatement.setDate(4, professor.getTerminationDate());
            preparedStatement.setBoolean(5, professor.isIsCoordinator());
            preparedStatement.setInt(6, professor.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();

            if (affectedRows > unaffectedRows) {
                successfulRegister = true;
            }

            return successfulRegister;

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Error al registrar profesor con número: "
                    + professor.getNumberStaff(), e);

            throw new DataIntegrityException(
                    "Tuvimos problemas para registrar un nuevo profesor. Inténtelo más tarde",
                    e
            );
        }
    }

    @Override
    public boolean deactivateProfessor(User user, Professor professor) throws DataIntegrityException {

        boolean successfulDeactivate = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > unaffectedRows) {
                successfulDeactivate = true;
            }

            return successfulDeactivate;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar profesor con idUsuario: " + professor.getIdUser(), e);
            throw new DataIntegrityException("Tuvimos problemas para desactivar al profesor. Intentelo mas tarde", e);
        }
    }

    @Override
    public boolean updateProfessor(Professor professor) throws DataIntegrityException {

        boolean successfulUpdate = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Profesor SET turno = ?, esCoordinador = ?, idRol = ? WHERE numeroPersonal = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getShift());
            preparedStatement.setBoolean(2, professor.isIsCoordinator());
            preparedStatement.setInt(3, professor.getIdRol());
            preparedStatement.setString(4, professor.getNumberStaff());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > unaffectedRows) {
                successfulUpdate = true;
            }

            return successfulUpdate;

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
                professor.setIsCoordinator(resultSet.getBoolean("esCoordinador"));
                professor.setIdUser(resultSet.getInt("idUsuario"));
                professor.setIdRol(resultSet.getInt("idRol"));

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

    public ArrayList<Professor> getProfessorsForComboBox() throws DataIntegrityException {

        ArrayList<Professor> listProfessors = new ArrayList<>();
        String query = "SELECT * FROM vw_profesor WHERE estado = true;";

        try (Connection connection = DatabaseConnection.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Professor professor = new Professor();
                professor.setNumberStaff(resultSet.getString("numeroPersonal"));
                professor.setFirstName(resultSet.getString("primerNombre"));
                professor.setMiddleName(resultSet.getString("segundoNombre"));
                professor.setPaternalSurname(resultSet.getString("apellidoPaterno"));
                professor.setMaternalSurname(resultSet.getString("apellidoMaterno"));

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

    @Override
    public boolean existsProfessor(int idUser) throws DataIntegrityException {

        boolean professorExists = false;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT 1 FROM profesor WHERE idUsuario = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                professorExists = true;
            }

            return professorExists;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar existencia del profesor", e);
            throw new DataIntegrityException("Tuvimos problemas para verificar la ecistencia del profesor. Intentalo mas tarde", e);
        }
    }

    public String getPersonalNumberByIdUser(int idUser) throws DataIntegrityException {

        String personalNumber = null;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT numeroPersonal FROM Profesor WHERE idUsuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUser);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                personalNumber = resultSet.getString("numeroPersonal");
            }

            return personalNumber;

        } catch (SQLException e) {
            throw new DataIntegrityException("Error al obtener el numero de personal", e);
        }
    }

}
