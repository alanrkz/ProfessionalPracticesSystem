/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Validations;

import javafx.scene.control.Alert;

/**
 *
 * @author ELLIN JV
 */
public class UsernamesValidations {
    
    public UsernamesValidations(String email, String password){
        if (email == null || email.isEmpty()) {
            showAlert("El correo es obligatorio");
            return;
        }

        if (password == null || password.isEmpty()) {
            showAlert("La contraseña es obligatoria");
            return;
        }

        if (!email.contains("@")) {
            showAlert("Correo inválido");
            return;
        }
    }
    
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
