/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectApplicationsDAO;
import Logic.DTO.ProjectApplications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ELLIN JV
 */
public class ProjectApplicationsDAO implements IProjectApplicationsDAO{
    
     @Override
     public String registerApplication(ProjectApplications projectApplication) {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO AplicacionProyecto (idProyecto, matricula, estadoAsignacion) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, projectApplication.getIdProject());
            preparedStatement.setString(2, projectApplication.getEnrollment());
            preparedStatement.setBoolean(3, projectApplication.isAssignmentStatus());

            int affectedRows = preparedStatement.executeUpdate();
            
            if (affectedRows > 0) {
                return "Aplicación registrada correctamente.";
            } else {
                return "No fue posible registrar la aplicación.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }
}
