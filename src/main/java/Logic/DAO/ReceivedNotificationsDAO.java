package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IReceivedNotificationsDAO;
import Logic.DTO.ReceivedNotifications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ELLIN JV
 */
public class ReceivedNotificationsDAO implements IReceivedNotificationsDAO {

    @Override
    public String registerReceived(ReceivedNotifications receivedNotification) {

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

            if (affectedRows > 0) {
                return "Notificación asignada.";
            } else {
                return "Error al asignar.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

}
