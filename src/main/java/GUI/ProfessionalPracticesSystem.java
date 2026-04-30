package GUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfessionalPracticesSystem extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        
        Label label = new Label("a");
        Scene scene = new Scene(label, 600, 565);
    Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Views/FXMLManageProject.fxml"));
        scene.setRoot(parent);

        stage.setScene(scene);
        stage.setTitle("a");
        stage.show();
        
        /*
        
        Label label = new Label("a");
        Scene scene = new Scene(label, 600, 463);
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FXMLInsertProject.fxml"));
        scene.setRoot(parent);

        stage.setScene(scene);
        stage.setTitle("Insertar Proyecto");
        stage.show();
        */
    }
    
    public static void main(String[] args) {
        launch();
    }
}
