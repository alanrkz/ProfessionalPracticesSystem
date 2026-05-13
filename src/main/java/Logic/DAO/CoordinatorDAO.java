package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.ICoordinatorDAO;
import Logic.DTO.Coordinator;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoordinatorDAO implements ICoordinatorDAO {

    private static final Logger logger = Logger.getLogger(CoordinatorDAO.class.getName());

    @Override
    public boolean registerCoordinator(Coordinator coordinator) throws DataIntegrityException {
        
        boolean successfulRegister = false;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Coordinador VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, coordinator.getNumberStaff());
            preparedStatement.setDate(2, coordinator.getRegistrationDate());
            preparedStatement.setDate(3, coordinator.getTerminationDate());
            preparedStatement.setBoolean(4, coordinator.getStatusCoordinator());
            preparedStatement.setInt(5, coordinator.getIdUser());
            preparedStatement.setInt(6, coordinator.getIdRol());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                successfulRegister = true;
            }
            
            return successfulRegister;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar coordinador: " + coordinator.getNumberStaff(), e);
            throw new DataIntegrityException("Error al registrar coordinador", e);
        }
    }

    @Override
    public boolean deactivateCoordinator(User user, Coordinator coordinator) throws DataIntegrityException {
        
        boolean successfulDeactivate = false;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, coordinator.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                successfulDeactivate = true;
            }
            
            return successfulDeactivate;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar coordinador id: " + coordinator.getIdUser(), e);
            throw new DataIntegrityException("Error al desactivar coordinador", e);
        }
    }

    @Override
    public boolean existsCoordinator(int idUser) throws DataIntegrityException {
        
        boolean coordinadorExists = false;
        try (Connection connection = DatabaseConnection.connect()) {
            
            String query = "SELECT 1 FROM coordinador WHERE idUsuario = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                coordinadorExists = true;
            }
            
            return coordinadorExists;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar existencia del coordinador", e);
            throw new DataIntegrityException("Tuvimos problemas para verificar la ecistencia del coordinador. Intentalo mas tarde", e);
        }
    }

}
