package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectApplicationsDAO;
import Logic.DTO.ProjectApplications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ELLIN JV
 */
public class ProjectApplicationsDAO implements IProjectApplicationsDAO {

    @Override
    public String registerApplication(ProjectApplications projectApplication) {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO SolicitudesProyecto (idProyecto, matricula, estadoAsignacion) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, projectApplication.getIdProject());
            preparedStatement.setString(2, projectApplication.getEnrollment());
            preparedStatement.setBoolean(3, projectApplication.isAssignmentStatus());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                projectApplication.setIdProject(rs.getInt(1));
            }

            if (affectedRows > 0) {
                return "Aplicación registrada correctamente.";
            } else {
                return "No fue posible registrar la aplicación.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }
}
