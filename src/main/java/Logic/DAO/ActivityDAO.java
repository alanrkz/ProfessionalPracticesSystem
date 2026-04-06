package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.DTO.Activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;


public class ActivityDAO {
    
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.connect();
    
    public String addActivity(Activity activity){
        try {
            
            String query = "INSERT INTO Activity VALUES (?, ?, ?, ?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, activity.getActivityName());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setDouble(3, activity.getValue());
            preparedStatement.setDate(4, activity.getDueDateActivity());
            preparedStatement.setInt(5, activity.getIdActivityProject());
            preparedStatement.setInt(6, activity.getIdActivityReport());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
            if (affectedRows > 0) {
                return "El alumno fue agregado correctamente.";
            } else {
                return "Hubo problemas para guardar al alumno. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e){
            e.printStackTrace();
            return "Hubo problemas para guardar al alumno. Intente de nuevo mas tarde.";
        }
    }
    
}
