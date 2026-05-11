package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IStudentDAO;
import Logic.DTO.Student;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StudentDAO implements IStudentDAO {

    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    @Override
    public boolean registerStudent(Student student) throws DataIntegrityException {
        boolean successfulRegister = false;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Practicante (matricula, fechaNacimiento, horasCubiertas, sectorSocial, lenguaIndigena, idUsuario, nrc) VALUES (?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getEnrollment());
            preparedStatement.setDate(2, student.getBirthdate());
            preparedStatement.setInt(3, student.getHoursCovered());
            preparedStatement.setString(4, student.getSocialSector());
            preparedStatement.setBoolean(5, student.isIndigenousLanguage());
            preparedStatement.setInt(6, student.getIdUser());
            preparedStatement.setString(7, student.getNrc());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                successfulRegister = true;
            }
            
            return successfulRegister;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar estudiante", e);
            throw new DataIntegrityException("Error al registrar estudiante", e);
        }
    }

    @Override
    public boolean deactivateStudent(User user, Student student) throws DataIntegrityException {
        boolean successfulDeactivate = false;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                successfulDeactivate = true;
            }
            
            return successfulDeactivate;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar estudiante", e);
            throw new DataIntegrityException("Error al desactivar estudiante", e);
        }
    }

    @Override
    public List<Student> getStudents() throws DataIntegrityException {

        List<Student> listStudents = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Practicante;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener estudiantes", e);
            throw new DataIntegrityException("Error al obtener estudiantes", e);
        }

        return listStudents;
    }
    
    @Override
    public boolean existsStudent(int idUser) throws DataIntegrityException {
        boolean studentExists = false;
        try (Connection connection = DatabaseConnection.connect()){
            
            String query = "SELECT 1 FROM practicante WHERE idUsuario = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                studentExists = true;
            } 
            
            return studentExists;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar la ecistencia del estudiante", e);
            throw new DataIntegrityException("Tuvimos problemas para verificar la ecistencia del estudiante. Intentalo mas tarde", e);
        }
    }
    
}