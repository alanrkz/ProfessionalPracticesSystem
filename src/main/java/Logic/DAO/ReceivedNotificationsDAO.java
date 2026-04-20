package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IReceivedNotificationsDAO;
import Logic.DTO.ReceivedNotifications;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReceivedNotificationsDAO implements IReceivedNotificationsDAO {

    private static final Logger logger = Logger.getLogger(ReceivedNotificationsDAO.class.getName());

    @Override
    public boolean registerReceived(ReceivedNotifications receivedNotification) throws DataIntegrityException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO NotificacionesRecibidas (idNotificacion, matricula) VALUES (?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, receivedNotification.getIdNotification());
            preparedStatement.setString(2, receivedNotification.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                receivedNotification.setIdNotification(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se registro notificacion recibida");
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar notificacion recibida", e);
            throw new DataIntegrityException("Error al registrar notificacion recibida", e);
        }
    }
    
}