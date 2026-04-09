package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ICourseDAO;
import Logic.DTO.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements ICourseDAO {

    @Override
    public String registerCourse(Course course) {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO experienciaeducativa (nrc, nombreExperienciaEducativa, carrera, fechaInicio, fechaFin, numeroPersonal) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            
            preparedStatement.setString(1, course.getNrc());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getCareer());
            preparedStatement.setDate(4, new java.sql.Date(course.getStartDate().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(course.getEndDate().getTime()));
            preparedStatement.setString(6, course.getNumberStaff());

            int affectedRows = preparedStatement.executeUpdate();          

            if (affectedRows > 0) {
                return "Curso registrado coreectamente.";
            } else {
                return "Error al registrar .";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error de conexión.";
        }
    }
    
    @Override
    public List<Course> getCourses() {
        List<Course> CoursesList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Curso;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setNrc(resultSet.getString("nrc"));
                course.setCourseName(resultSet.getString("nombreExperienciaEducativa"));
                course.setCareer(resultSet.getString("carrera"));
                course.setStartDate(resultSet.getDate("fechaInicio"));
                course.setEndDate(resultSet.getDate("fechaFin"));
                course.setNumberStaff(resultSet.getString("numeroPersonal"));

                CoursesList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CoursesList;
    }

}
