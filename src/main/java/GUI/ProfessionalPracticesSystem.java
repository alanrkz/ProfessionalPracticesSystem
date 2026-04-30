package GUI;


import Logic.Validations.AlertMessages;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfessionalPracticesSystem extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Views/LogInWindow.fxml"));
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sistema de Prácticas Profesionales");
            stage.show();

        } catch (IOException e) {
            AlertMessages.showAlert("Error: No se encontro la ventana principal Login");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
