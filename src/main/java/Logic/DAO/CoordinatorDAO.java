package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.ICoordinatorDAO;
import Logic.DTO.Coordinator;
import Logic.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CoordinatorDAO implements ICoordinatorDAO{
    
    @Override
    public String registerCoordinator(Coordinator coordinator) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO Coordinador VALUES (?, ?, ?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coordinator.getNumberStaff());
            preparedStatement.setDate(2, coordinator.getRegistrationDate());
            preparedStatement.setDate(3, coordinator.getTerminationDate());
            preparedStatement.setString(4, coordinator.getServiceTime());
            preparedStatement.setInt(5, coordinator.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El coordinador fue registrado correctamente.";
            } else {
                return "Hubo problemas para registrar al coordinador. Intente de nuevo mas tarde.";
            }

        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public String deactivateCoordinator(User user, Coordinator coordinator) {
        if (user.getIdUser() == coordinator.getIdUser()) {
            if (user.getStatus() == false) {
                try (Connection connection = DatabaseConnection.connect()) {
                    String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, coordinator.getIdUser());
                    
                    int affectedRows = preparedStatement.executeUpdate();
                    
                    connection.close();
                    preparedStatement.close();
                    
                    if (affectedRows > 0) {
                        return "El coordinador fue desactivado correctamente.";
                    } else {
                        return "Hubo problemas para desactivar al coordinador. Intente de nuevo mas tarde.";
                    }
                    
                } catch (SQLException e) {
                    return "Tenemos problemas con la conexion al sistema.";
                }
            } else {
                return "El coordinador ya esta inactivo.";
            }
        } else {
            return "Coordinador no encontrado.";
        }
    }
    
}
