package Logic.Validations;

import Logic.Exceptions.InvalidFormDataException;
import java.time.LocalDate;

/**
 *
 * @author ELLIN JV
 */
public class FormValidations {

    private FormValidations() {

    }

    public static void validateRequiredField(String fieldValue, String fieldName) throws InvalidFormDataException {

        if (fieldValue == null) {

            throw new InvalidFormDataException(fieldName + " es obligatorio.");
        }

        String cleanedFieldValue = fieldValue.trim();

        if (cleanedFieldValue.isBlank()) {

            throw new InvalidFormDataException(fieldName + " no puede estar vacío.");
        }
    }

    public static void validateMinimumLength(String fieldValue, int minimumLength, String fieldName) throws InvalidFormDataException {

        if (fieldValue.length() < minimumLength) {

            throw new InvalidFormDataException(fieldName + " no puede contener menos de " + minimumLength + " caracteres.");
        }
    }

    public static void validateMaximumLength(String fieldValue, int maximumLength, String fieldName) throws InvalidFormDataException {

        if (fieldValue.length() > maximumLength) {

            throw new InvalidFormDataException(fieldName + " excede la longitud permitida.");
        }
    }

    public static void validateUniqueLength(String fieldValue, int uniqueLength, String fieldName) throws InvalidFormDataException {

        if (fieldValue.length() > uniqueLength && fieldValue.length() < uniqueLength) {

            throw new InvalidFormDataException(fieldName + " debe tener " + uniqueLength + " caracteres.");
        }

    }

    public static void validateAllowedCharacters(String fieldValue, String allowedCharacters, String fieldName) throws InvalidFormDataException {

        boolean containsInvalidCharacters = !fieldValue.matches(allowedCharacters);

        if (containsInvalidCharacters) {

            throw new InvalidFormDataException(fieldName + " contiene caracteres inválidos.");
        }
    }

    public static void validateComboBoxSelection(String selectedValue, String fieldName) throws InvalidFormDataException {

        if (selectedValue == null) {

            throw new InvalidFormDataException("Debe seleccionar una opción de " + fieldName + ".");
        }
    }

    public static void validateDate(LocalDate date, String fieldName) throws InvalidFormDataException {

        if (date == null) {

            throw new InvalidFormDataException(fieldName + " no puede estar vacía.");
        }
    }

    public static void validateObjectSelection(Object selectedValue, String fieldName) throws InvalidFormDataException {
        if (selectedValue == null) {

            throw new InvalidFormDataException("Debe seleccionar una opción de " + fieldName + ".");
        }
    }
}
