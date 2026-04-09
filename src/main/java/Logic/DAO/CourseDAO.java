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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseDAO implements ICourseDAO{
     @Override
    public String registerCourse(Course course) {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Curso (nrc, nombreCurso, carrera, fechaInicio, fechaFin, numeroPersonal) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, course.getNrc());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getCareer());
            ps.setDate(4, new java.sql.Date(course.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(course.getEndDate().getTime()));
            ps.setString(6, course.getNumberStaff());

            int rows = ps.executeUpdate();

            return (rows > 0) ? "Curso registrado correctamente." : "Error al registrar curso.";

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

    public List<Course> getCourses() {
        List<Course> list = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Curso;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setNrc(rs.getString("nrc"));
                c.setCourseName(rs.getString("nombreCurso"));
                c.setCareer(rs.getString("carrera"));
                c.setStartDate(rs.getDate("fechaInicio"));
                c.setEndDate(rs.getDate("fechaFin"));
                c.setNumberStaff(rs.getString("numeroPersonal"));

                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}