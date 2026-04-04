package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectDAO;
import Logic.DTO.Project;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectDAO implements IProjectDAO{
    
    @Override
    public String registerProject (Project project){
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.buildConnection();
            String query = "INSERT INTO Proyecto VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, project.getIdProject());
            preparedStatement.setString(2, project.getProjectName());
            preparedStatement.setString(3, project.getDuration());
            preparedStatement.setString(4, project.getDescription());
            preparedStatement.setInt(5, project.getAvailableSpaces());
            preparedStatement.setBoolean(6, project.isStatus());
            preparedStatement.setString(7, project.getProjectMethodology());
            preparedStatement.setInt(8, project.getIdLikedOrganization());
            
            int affectedRows = preparedStatement.executeUpdate();

            databaseConnection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                return "El Proyecto fue registrado correctamente ";
            } else {
                return "Hubo problemas para registrar el Proyecto. Intente de nuevo mas tarde ";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema ";
        }
    }
    
    public String deactivateProject(int idProject){
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.buildConnection();
            String query = "UPDATE Proyecto SET estado = false WHERE idProyecto = ?;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, idProject);
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema ";
        }
        return null;
        
    }
}

