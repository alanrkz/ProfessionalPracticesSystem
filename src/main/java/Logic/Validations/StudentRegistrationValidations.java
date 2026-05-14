package Logic.Validations;

import Logic.DTO.Course;
import Logic.DTO.Student;
import Logic.Exceptions.InvalidFormDataException;
import static Logic.Validations.FormValidations.validateDate;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author ELLIN JV
 */
public class StudentRegistrationValidations {

    private static final int MINIMUM_STUDENT_AGE = 18;
    private static final int MAXIMUM_STUDENT_AGE = 90;
    private static final String BIRTHDATE_LABEL = "La fecha de nacimiento";

    //private static final String NRC_PATTERN = "\"^[0-9]+$\"";
    //private static final int NRC_LENGTH = 5;
    private static final String NRC_LABEL = "El NRC";

    private static final String ENROLLMENT_PATTERN = "^[a-zA-Z0-9]+$";
    private static final int ENROLLMENT_LENGTH = 9;
    private static final String ENROLLMENT_LABEL = "La matrícula";

    private StudentRegistrationValidations() {

    }

    public static void validateStudentRegistrationData(Student student) throws InvalidFormDataException {

        validateEnrollment(student.getEnrollment());

        validateNrc(student.getNrc());

        validateBirthdate(student.getBirthdate().toLocalDate());
    }

    public static void validateEnrollment(String enrollment) throws InvalidFormDataException {

        FormValidations.validateRequiredField(enrollment, ENROLLMENT_LABEL);

        String cleanedEnrollment = enrollment.trim();

        FormValidations.validateUniqueLength(cleanedEnrollment, ENROLLMENT_LENGTH, ENROLLMENT_LABEL);

        FormValidations.validateAllowedCharacters(cleanedEnrollment, ENROLLMENT_PATTERN, ENROLLMENT_LABEL);
    }

    public static void validateCourseSelection(Course selectedCourse) throws InvalidFormDataException {

        FormValidations.validateObjectSelection(selectedCourse, "Experiencia Educativa");


        if (selectedCourse.getNrc() == null || selectedCourse.getNrc().isBlank()) {
            throw new InvalidFormDataException("El curso seleccionado no tiene un NRC válido.");
        }
    }

    public static void validateNrc(String courseNrc) throws InvalidFormDataException {

        FormValidations.validateComboBoxSelection(courseNrc, NRC_LABEL);

        //FormValidations.validateUniqueLength(courseNrc,NRC_LENGTH, NRC_LABEL);
        //FormValidations.validateAllowedCharacters(courseNrc,NRC_PATTERN, NRC_LABEL);
    }

    public static void validateBirthdate(LocalDate birthdate) throws InvalidFormDataException {

        validateDate(birthdate, BIRTHDATE_LABEL);

        LocalDate currentDate = LocalDate.now();

        int studentAge = Period.between(birthdate, currentDate).getYears();

        if (studentAge < MINIMUM_STUDENT_AGE) {

            throw new InvalidFormDataException("El estudiante debe ser mayor de edad.");
        }

        if (studentAge > MAXIMUM_STUDENT_AGE) {

            throw new InvalidFormDataException("La fecha de nacimiento es inválida.");
        }
    }
}
