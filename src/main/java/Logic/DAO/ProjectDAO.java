package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectDAO;
import Logic.DTO.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alan rkz
 */
public class ProjectDAO implements IProjectDAO{
    
    @Override
    public String registerProject (Project project){
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO Proyecto (nombreProyecto, duracion, descripcion, cupo, estado, metodologiaProyecto, idOrganizacionVinculada) VALUES(?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getDuration());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setInt(4, project.getAvailableSpaces());
            preparedStatement.setBoolean(5, project.isStatus());
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
                return "El proyecto fue registrado correctamente ";
            } else {
                return "Hubo problemas para registrar el proyecto. Intente de nuevo mas tarde ";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema ";
        }
    }
    
    @Override
    public String deactivateProject(int idProject){
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE Proyecto SET estado = false WHERE idProyecto = ?;";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idProject);
            
            int affectedRows = preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                return "El proyecto ha sido desactivado ";
            } else {
                return "Hubo problemas para desactivar el proyecto. Intente de nuevo mas tarde ";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema ";
        }  
    }
    
}

