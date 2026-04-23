package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectDAO;
import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProjectDAO implements IProjectDAO {

    private static final Logger logger = Logger.getLogger(ProjectDAO.class.getName());

    @Override
    public boolean registerProject(Project project) throws DataIntegrityException {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Proyecto (nombreProyecto, duracion, descripcion, cupo, estado, metodologiaProyecto, idOrganizacionVinculada) VALUES(?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getDuration());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setInt(4, project.getAvailableSpaces());
            preparedStatement.setBoolean(5, true);
            preparedStatement.setString(6, project.getProjectMethodology());
            preparedStatement.setInt(7, project.getIdLikedOrganization());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                project.setIdProject(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se inserto proyecto: " + project.getProjectName());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar proyecto", e);
            throw new DataIntegrityException("Error al registrar proyecto", e);
        }
    }

    @Override
    public boolean deactivateProject(int idProject) throws DataIntegrityException {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Proyecto SET estado = false WHERE idProyecto = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idProject);

            int affectedRows = preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se desactivo proyecto id: " + idProject);
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar proyecto id: " + idProject, e);
            throw new DataIntegrityException("Error al desactivar proyecto", e);
        }
    }
    
}