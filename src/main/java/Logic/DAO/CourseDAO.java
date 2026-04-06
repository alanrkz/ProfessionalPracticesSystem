/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ICourseDAO;
import Logic.DTO.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ELLIN JV
 */
public class CourseDAO {
    public String registerCourse(Course course) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO Curso (nombreCurso) VALUES (?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseName());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return (affectedRows > 0)
                    ? "Curso registrado correctamente."
                    : "Error al registrar el curso.";

        } catch (SQLException e) {
            return "Problemas con la conexión.";
        }
    }

    public String updateCourse(Course course) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE Curso SET nombreCurso = ? WHERE idCurso = ?;";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getNrc());

            int affectedRows = ps.executeUpdate();

            return (affectedRows > 0)
                    ? "Curso actualizado correctamente."
                    : "Error al actualizar curso.";

        } catch (SQLException e) {
            return "Problemas con la conexión.";
        }
    }

    public String deleteCourse(int idCourse) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "DELETE FROM Curso WHERE idCurso = ?;";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idCourse);

            int affectedRows = ps.executeUpdate();

            return (affectedRows > 0)
                    ? "Curso eliminado."
                    : "Error al eliminar.";

        } catch (SQLException e) {
            return "Problemas con la conexión.";
        }
    }
}
