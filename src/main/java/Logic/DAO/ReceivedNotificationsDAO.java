/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IReceivedNotificationsDAO;
import Logic.DTO.ReceivedNotifications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ELLIN JV
 */
public class ReceivedNotificationsDAO implements IReceivedNotificationsDAO{
    
     @Override
     public String registerReceived(ReceivedNotifications receivedNotification) {
         
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO NotificacionesRecibidas (idNotificacion, matricula) VALUES (?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, receivedNotification.getIdNotification());
            preparedStatement.setString(2, receivedNotification.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows > 0) {
                return "Notificación asignada.";
            } else {
                return "Error al asignar notificación.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }
     
}
