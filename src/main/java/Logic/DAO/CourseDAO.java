package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ICourseDAO;
import Logic.DTO.Course;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO implements ICourseDAO {

    private static final Logger logger = Logger.getLogger(CourseDAO.class.getName());

    @Override
    public boolean registerCourse(Course course) throws DataIntegrityException {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ExperienciaEducativa (nrc, nombreExperienciaEducativa, carrera, fechaInicio, fechaFin, numeroPersonal) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);

            preparedStatement.setString(1, course.getNrc());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getCareer());
            preparedStatement.setDate(4, new java.sql.Date(course.getStartDate().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(course.getEndDate().getTime()));
            preparedStatement.setString(6, course.getNumberStaff());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se inserto curso con NRC: " + course.getNrc());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar curso NRC: " + course.getNrc(), e);
            throw new DataIntegrityException("Error al registrar curso", e);
        }
    }

    @Override
    public List<Course> getCourses() throws DataIntegrityException {
        List<Course> CoursesList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM ExperienciaEducativa;";
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

            resultSet.close();
            preparedStatement.close();
            databaseConnection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener cursos", e);
            throw new DataIntegrityException("Error al obtener cursos", e);
        }

        return CoursesList;
    }
    
}