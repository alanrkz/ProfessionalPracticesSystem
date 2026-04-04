package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IStudentDAO;
import Logic.DTO.Student;
import Logic.DTO.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO implements IStudentDAO{
    
    @Override
    public String registerStudent(Student student) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.buildConnection();
            String query = "INSERT INTO Practicante VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, student.getEnrollment());
            preparedStatement.setDate(2, student.getBirthdate());
            preparedStatement.setInt(3, student.getHoursCovered());
            preparedStatement.setBoolean(4, student.isIndigenousLanguage());
            preparedStatement.setString(5, student.getSocialSector());
            preparedStatement.setInt(6, student.getIdUser());
            preparedStatement.setString(7, student.getNrc());

            int affectedRows = preparedStatement.executeUpdate();

            databaseConnection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                return "El alumno fue agregado correctamente.";
            } else {
                return "Hubo problemas para guardar al alumno. Intente de nuevo mas tarde.";
            }

        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public String deactivateStudent(User user, Student student) {
        if (user.getIdUser() == student.getIdUser()) {
            if (user.getStatus() == false) {
                try {
                    DatabaseConnection databaseConnection = DatabaseConnection.buildConnection();
                    String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";
                    PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
                    preparedStatement.setInt(1, student.getIdUser());

                    int affectedRows = preparedStatement.executeUpdate();

                    databaseConnection.close();
                    preparedStatement.close();

                    if (affectedRows > 0) {
                        return "El alumno fue agregado correctamente.";
                    } else {
                        return "Hubo problemas para guardar al alumno. Intente de nuevo mas tarde.";
                    }

                } catch (SQLException e) {
                    return "Tenemos problemas con la conexion al sistema.";
                }
            } else {
                return "El Alumno ya esta inactivo";
            }
        } else {
            return "Alumno no encontrado";
        }
    }
    
    /**
    public List getStudents(){
        List <Student> listStudent = new ArrayList();
        
    }
    **/
    
}
