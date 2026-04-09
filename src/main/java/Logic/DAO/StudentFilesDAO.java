/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IStudentFilesDAO;
import Logic.DTO.StudentFiles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ELLIN JV
 */
public class StudentFilesDAO implements IStudentFilesDAO{
    
     @Override
     public String registerFiles(StudentFiles studentFiles) {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ArchivosEstudiante (horario, planTrabajo, cartaAsignacion, matricula) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, studentFiles.getInternSchedule());
            preparedStatement.setString(2, studentFiles.getWorkPlan());
            preparedStatement.setString(3, studentFiles.getAssigmentLetter());
            preparedStatement.setString(4, studentFiles.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows > 0) {
                return "Archivos de espedinete registrados correctamente.";
            } else {
                return "No fue posible registrar los archivos.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }
}
