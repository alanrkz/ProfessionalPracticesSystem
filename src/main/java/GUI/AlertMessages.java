package GUI;


import javafx.scene.control.Alert;
/**
 *
 * @author ELLIN JV
 */

public class AlertMessages {

    private AlertMessages() {

    }

    public static void showInformationAlert(String title, String message) {

        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(title);
        informationAlert.setHeaderText(null);
        informationAlert.setContentText(message);
        informationAlert.showAndWait();
    }

    public static void showWarningAlert(String title, String message) {

        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle(title);
        warningAlert.setHeaderText(null);
        warningAlert.setContentText(message);
        warningAlert.showAndWait();
    }

    public static void showErrorAlert(String title, String message) {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
}
