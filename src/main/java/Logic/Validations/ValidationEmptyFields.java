package Logic.Validations;

import javafx.scene.control.TextInputControl;

/**
 *
 * @author alan rkz
 */
public class ValidationEmptyFields {

    public static boolean isEmptyField(TextInputControl field) {
        boolean emptyField = false;

        if (field.getText() == null || field.getText().trim().isEmpty()) {
            emptyField = true;
        }

        return emptyField;
    }

    public static boolean isEmptyField(TextInputControl... fields) {
        for (TextInputControl field : fields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }
    
}