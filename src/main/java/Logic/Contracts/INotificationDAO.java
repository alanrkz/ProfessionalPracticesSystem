/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logic.Contracts;

import Logic.DTO.Notification;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface INotificationDAO {
    
    public String registerNotification(Notification notification);
    
    public List<Notification> getNotifications();
}
