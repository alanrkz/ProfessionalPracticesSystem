package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IActivityDAO;
import Logic.DTO.Activity;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ActivityDAO implements IActivityDAO {

    private static final Logger logger = Logger.getLogger(ActivityDAO.class.getName());

    @Override
    public boolean addActivity(Activity activity) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se inserto actividad con nombre: " + activity.getActivityName());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar actividad: " + activity.getActivityName(), e);
            throw new DataIntegrityException("Error al registrar actividad", e);
        }
    }

    @Override
    public boolean modifyActivity(Activity activity) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se actualizo actividad con id: " + activity.getIdActivity());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar actividad id: " + activity.getIdActivity(), e);
            throw new DataIntegrityException("Error al actualizar actividad", e);
        }
    }
    
}