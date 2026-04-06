package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IStudentDAO;
import Logic.DTO.Student;
import Logic.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.connect();
    
    @Override
    public String registerStudent(Student student) {
        try {
            String query = "INSERT INTO Practicante VALUES (?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getEnrollment());
            preparedStatement.setDate(2, student.getBirthdate());
            preparedStatement.setInt(3, student.getHoursCovered());
            preparedStatement.setBoolean(4, student.isIndigenousLanguage());
            preparedStatement.setString(5, student.getSocialSector());
            preparedStatement.setInt(6, student.getIdUser());
            preparedStatement.setString(7, student.getNrc());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            
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
                    String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, student.getIdUser());
                    
                    int affectedRows = preparedStatement.executeUpdate();
                    
                    connection.close();
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
    
    @Override
    public List<Student> getStudents() {
        List <Student> listStudents = new ArrayList<>();
        
        String query = "SELECT * FROM Practicante;";
        
        try {
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                Student student = new Student();
                student.setEnrollment(resultSet.getString("matricula"));
                student.setBirthdate(resultSet.getDate("fechaNacimiento"));
                student.setHoursCovered(resultSet.getInt("horasCubiertas"));
                student.setSocialSector(resultSet.getString("sectorSocial"));
                student.setIndigenousLanguage(resultSet.getBoolean("lenguaIndigena"));
                student.setIdUser(resultSet.getInt("idUsuario"));
                student.setNrc(resultSet.getString("nrc"));
                
                listStudents.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listStudents;
    }
    
}
