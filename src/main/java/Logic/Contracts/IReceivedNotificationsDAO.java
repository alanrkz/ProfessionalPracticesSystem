package Logic.Contracts;


import Logic.DTO.ReceivedNotifications;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author ELLIN JV
 */
public interface IReceivedNotificationsDAO {
    
    public boolean registerReceived(ReceivedNotifications receivedNotification) throws DataIntegrityException;
    
}
