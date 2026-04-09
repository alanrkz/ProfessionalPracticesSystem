/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
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
public class NotificationDAO {
    public String registerNotification(Notification n) {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Notificacion (destinatario, asunto, mensaje, numeroPersonal) VALUES (?, ?, ?, ?);";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, n.getRecipient());
            ps.setString(2, n.getSubject());
            ps.setString(3, n.getMessageBody());
            ps.setString(4, n.getNumberStaff());

            int rows = ps.executeUpdate();

            return (rows > 0) ? "Notificación enviada." : "Error.";

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

    public List<Notification> getNotifications() {
        List<Notification> list = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Notificacion;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setIdNotification(rs.getInt("idNotificacion"));
                n.setSubject(rs.getString("asunto"));

                list.add(n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
