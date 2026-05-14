package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.INotificationDAO;
import Logic.DTO.Notification;
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


public class NotificationDAO implements INotificationDAO {

    private static final Logger logger = Logger.getLogger(NotificationDAO.class.getName());

    @Override
    public boolean registerNotification(Notification notification) throws DataIntegrityException {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Notificacion (destinatario, asunto, cuerpoNotificacion, numeroPersonal) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, notification.getReceiver());
            preparedStatement.setString(2, notification.getSubject());
            preparedStatement.setString(3, notification.getMessageBody());
            preparedStatement.setString(4, notification.getNumberStaff());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                notification.setIdNotification(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se insertó notificacion para: " + notification.getReceiver());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar notificacion", e);
            throw new DataIntegrityException("Error al registrar notificacion", e);
        }
    }

    @Override
    public List<Notification> getNotifications() throws DataIntegrityException {
        List<Notification> NotificationsList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Notificacion;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Notification notification = new Notification();
                notification.setIdNotification(resultSet.getInt("idNotificacion"));
                notification.setSubject(resultSet.getString("asunto"));

                NotificationsList.add(notification);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener notificaciones", e);
            throw new DataIntegrityException("Error al obtener notificaciones", e);
        }

        return NotificationsList;
    }
    
}