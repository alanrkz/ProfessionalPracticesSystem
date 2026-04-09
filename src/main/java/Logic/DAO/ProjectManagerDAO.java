/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectManagerDAO;
import Logic.DTO.ProjectManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ELLIN JV
 */
public class ProjectManagerDAO implements IProjectManagerDAO {
    
    @Override
    public String registerManager(ProjectManager projectManager) {
        
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ResponsableProyecto (nombre, puesto, email, idProyecto) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, projectManager.getManagerName());
            preparedStatement.setString(2, projectManager.getManagerPosition());
            preparedStatement.setString(3, projectManager.getManagerEmail());
            preparedStatement.setInt(4, projectManager.getIdProject());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return "Responsable registrad correctamente.";
            } else {
                return "No fue posible registrar al responsable.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }
    
    @Override
    public String deactivateProjectManager(int projectManagerId) {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE ResponsableProyecto SET estado = 'Inactivo' WHERE idResponsable = ?;";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, projectManagerId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return "Responsable desactivado correctamente.";
            } else {
                return "No fue posible desactivar.";
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return "Error en la conexión.";
        }
    }
}
