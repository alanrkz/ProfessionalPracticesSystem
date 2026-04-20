package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectManagerDAO;
import Logic.DTO.ProjectManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ELLIN JV
 */
public class ProjectManagerDAO implements IProjectManagerDAO {

    @Override
    public String registerManager(ProjectManager projectManager) {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ResponsableProyecto (nombre, puesto, email, idProyecto) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, projectManager.getManagerName());
            preparedStatement.setString(2, projectManager.getManagerPosition());
            preparedStatement.setString(3, projectManager.getManagerEmail());
            preparedStatement.setInt(4, projectManager.getIdProject());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                projectManager.setIdProjectManager(resultSet.getInt(1));
            }

            if (affectedRows > 0) {
                return "Responsable registrado correctamente.";
            } else {
                return "No fue posible registrar.";
            }
        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

    @Override
    public String deactivateProjectManager(int projectManagerId) {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE ResponsableProyecto SET estado = 'Inactivo' WHERE idResponsable = ?;";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, projectManagerId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return "Responsable desactivado correctamente.";
            } else {
                return "No fue posible desactivar.";
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return "Error en la conexión.";
        }
    }
}
