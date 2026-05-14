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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProjectDAO implements IProjectDAO {

    private static final Logger logger = Logger.getLogger(ProjectDAO.class.getName());

    @Override
    public boolean registerProject(Project project) throws DataIntegrityException {
        boolean successfulRegister = false;
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
                successfulRegister = true;
            }
            
            return successfulRegister;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar proyecto", e);
            throw new DataIntegrityException("Error al registrar proyecto", e);
        }
    }

    @Override
    public boolean deactivateProject(int idProject) throws DataIntegrityException {
        boolean successfulDeactivate = false;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Proyecto SET estado = false WHERE idProyecto = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idProject);

            int affectedRows = preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                successfulDeactivate = true;
            }
            
            return successfulDeactivate;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar proyecto id: " + idProject, e);
            throw new DataIntegrityException("Error al desactivar proyecto", e);
        }
    }
    
    @Override
    public boolean updateProject(Project project) throws DataIntegrityException {
        boolean successfulUpdate = false;
        
        try (Connection databaseConnection = DatabaseConnection.connect()) {
            String query = "UPDATE Proyecto SET nombreProyecto = ?, duracion = ?, descripcion = ?, cupo = ?, metodologiaProyecto = ?, "
                    + "idOrganizacionVinculada = ? WHERE idProyecto = ?;";
            
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getDuration());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setInt(4, project.getAvailableSpaces());
            preparedStatement.setString(5, project.getProjectMethodology());
            preparedStatement.setInt(6, project.getIdLikedOrganization());
            preparedStatement.setInt(7, project.getIdProject());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                successfulUpdate = true;
            }
            
            return successfulUpdate;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar proyecto", e);
            throw new DataIntegrityException("Error al actualizar proyecto", e);
        }
    }
    
    @Override
    public List<Project> getProjects () throws DataIntegrityException{
        List<Project> projectsList = new ArrayList();
        
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Proyecto WHERE estado = true;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();

                project.setIdProject(resultSet.getInt("idProyecto"));
                project.setProjectName(resultSet.getString("nombreProyecto"));
                project.setDuration(resultSet.getString("duracion"));
                project.setDescription(resultSet.getString("descripcion"));
                project.setAvailableSpaces(resultSet.getInt("cupo"));
                project.setStatus(resultSet.getBoolean("estado"));
                project.setProjectMethodology(resultSet.getString("metodologiaProyecto"));
                project.setIdLikedOrganization(resultSet.getInt("idOrganizacionVinculada"));

                projectsList.add(project);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener los proyectos", e);
            throw new DataIntegrityException("Error al obtener los proyectos", e);
        }
        
        return projectsList;
    }
    
}