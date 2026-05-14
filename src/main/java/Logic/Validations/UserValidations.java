package Logic.Validations;

import Logic.DTO.User;
import Logic.Exceptions.InvalidFormDataException;

/**
 *
 * @author ELLIN JV
 */
public class UserValidations {

    private static final int MINIMUM_NAME_LENGTH = 2;
    private static final int MAXIMUM_NAME_LENGTH = 50;
    private static final String NAME_PATTERN = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$";
    private static final String FIRST_NAME_LABEL = "El primer nombre";
    private static final String MIDDLE_NAME_LABEL = "El segundo nombre";
    private static final String PATERNAL_SURNAME_LABEL = "El apellido Paterno";
    private static final String MATERNAL_SURNAME_LABEL = "El apellido Materno";

    private static final int MINIMUM_EMAIL_LENGTH = 6;
    private static final int MAXIMUM_EMAIL_LENGTH = 100;
    private static final String EMAIL_LABEL = "El correo electrónico";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    private static final int MINIMUM_PASSWORD_LENGTH = 10;
    private static final String PASSWORD_LABEL = "La contraseña";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{10,}$";
    
    private static final String GENDER_LABEL = "El género";

    private UserValidations() {

    }

    public static void validateUserRegistrationData(User user) throws InvalidFormDataException {

        validateUserName(user.getFirstName(),FIRST_NAME_LABEL);

        validateOptionalUserName(user.getMiddleName(),MIDDLE_NAME_LABEL);

        validateUserName(user.getPaternalSurname(),PATERNAL_SURNAME_LABEL);

        validateUserName(user.getMaternalSurname(),MATERNAL_SURNAME_LABEL);

        validateGender(user.getGender());

        validateUserEmail(user.getEmail());

        validateUserPassword(user.getPassword());
    }

    public static void validateUserName(String name, String fieldName) throws InvalidFormDataException {

        FormValidations.validateRequiredField(name,fieldName);

        String cleanedName = name.trim();

        FormValidations.validateMinimumLength(cleanedName, MINIMUM_NAME_LENGTH, fieldName);

        FormValidations.validateMaximumLength(cleanedName, MAXIMUM_NAME_LENGTH, fieldName);

        FormValidations.validateAllowedCharacters(cleanedName, NAME_PATTERN, fieldName);
    }

    public static void validateOptionalUserName(String name, String fieldName) throws InvalidFormDataException {

        if (name == null || name.trim().isBlank()) {

            return;
        }

        validateUserName(name, fieldName);
    }

    public static void validateUserEmail(String userEmail) throws InvalidFormDataException {

        FormValidations.validateRequiredField(userEmail, EMAIL_LABEL);

        String cleanedUserEmail = userEmail.trim();

        FormValidations.validateMinimumLength(cleanedUserEmail,MINIMUM_EMAIL_LENGTH, EMAIL_LABEL);

        FormValidations.validateMaximumLength(cleanedUserEmail,MAXIMUM_EMAIL_LENGTH, EMAIL_LABEL);

        FormValidations.validateAllowedCharacters(cleanedUserEmail,EMAIL_PATTERN, EMAIL_LABEL);
    }

    public static void validateUserPassword(String password) throws InvalidFormDataException {

        FormValidations.validateRequiredField(password,PASSWORD_LABEL);

        FormValidations.validateMinimumLength(password, MINIMUM_PASSWORD_LENGTH, PASSWORD_LABEL);

        FormValidations.validateAllowedCharacters(password,PASSWORD_PATTERN, PASSWORD_LABEL);
    }

    public static void validateGender(String selectedGender) throws InvalidFormDataException {

        FormValidations.validateComboBoxSelection(selectedGender,GENDER_LABEL);
    }

}
