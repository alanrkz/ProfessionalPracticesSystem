package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfessionalPracticesSystem extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentFormW.fxml"));

        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Registro de alumno");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
