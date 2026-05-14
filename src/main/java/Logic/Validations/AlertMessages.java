package Logic.Validations;


import javafx.scene.control.Alert;

/**
 *
 * @author alan rkz
 */
public class AlertMessages {
    
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
