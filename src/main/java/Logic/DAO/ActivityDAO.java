package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IActivityDAO;
import Logic.DTO.Activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author alan rkz
 */
public class ActivityDAO implements IActivityDAO{

    @Override
    public String addActivity(Activity activity) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO Activity (nombreActividad, descripcion, valor, fechaEntregaActividad, idProyecto, idReporte) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, activity.getActivityName());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setDouble(3, activity.getValue());
            preparedStatement.setDate(4, activity.getDueDateActivity());
            preparedStatement.setInt(5, activity.getIdActivityProject());
            preparedStatement.setInt(6, activity.getIdActivityReport());

            int affectedRows = preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                activity.setIdActivity(resultSet.getInt(1));
            }
            
            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > 0) {
                return "La actividad fue agregada correctamente.";
            } else {
                return "Hubo problemas para agregar la actividad. Intente de nuevo mas tarde.";
            }

        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }

    @Override
    public String modifyActivity(Activity activity) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE Activity SET nombreActividad = ?, descripcion = ?, valor = ?, fechaEntregaActividad = ? WHERE idActividad = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, activity.getActivityName());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setDouble(3, activity.getValue());
            preparedStatement.setDate(4, activity.getDueDateActivity());
            preparedStatement.setInt(5, activity.getIdActivity());
            
            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "La actividad fue actualizada correctamente.";
            } else {
                return "Hubo problemas para actualizar la actividad. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
}
