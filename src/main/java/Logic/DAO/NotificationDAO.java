/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.INotificationDAO;
import Logic.DTO.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public class NotificationDAO implements INotificationDAO{
    
    @Override
    public String registerNotification(Notification notification) {
        
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Notificacion (destinatario, asunto, mensaje, numeroPersonal) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, notification.getRecipient());
            preparedStatement.setString(2, notification.getSubject());
            preparedStatement.setString(3, notification.getMessageBody());
            preparedStatement.setString(4, notification.getNumberStaff());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return "La organización fue registrada correctamente.";
            } else {
                return "No fue posible registrar la organización.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

    public List<Notification> getNotifications() {
        List<Notification> NotificationsList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Notificacion;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Notification notification = new Notification();
                notification.setIdNotification(rs.getInt("idNotificacion"));
                notification.setSubject(rs.getString("asunto"));

                NotificationsList.add(notification);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return NotificationsList;
    }
}
