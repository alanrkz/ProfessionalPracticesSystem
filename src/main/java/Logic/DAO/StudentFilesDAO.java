package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IStudentFilesDAO;
import Logic.DTO.StudentFiles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ELLIN JV
 */
public class StudentFilesDAO implements IStudentFilesDAO {

    @Override
    public String registerFiles(StudentFiles studentFiles) {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ArchivosEstudiante (horario, planTrabajo, cartaAsignacion, matricula) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, studentFiles.getInternSchedule());
            preparedStatement.setString(2, studentFiles.getActivitiesPlan());
            preparedStatement.setString(3, studentFiles.getAssigmentLetter());
            preparedStatement.setString(4, studentFiles.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                studentFiles.setIdStudentRecord(resultSet.getInt(1));
            }

            if (affectedRows > 0) {
                return "Archivos registrados correctamente.";
            } else {
                return "No fue posible registrar.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }
}
