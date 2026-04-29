package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.ICoordinatorDAO;
import Logic.DTO.Coordinator;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CoordinatorDAO implements ICoordinatorDAO {

    private static final Logger logger = Logger.getLogger(CoordinatorDAO.class.getName());

    @Override
    public boolean registerCoordinator(Coordinator coordinator) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se inserto coordinador con numero: " + coordinator.getNumberStaff());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar coordinador: " + coordinator.getNumberStaff(), e);
            throw new DataIntegrityException("Error al registrar coordinador", e);
        }
    }

    @Override
    public boolean deactivateCoordinator(User user, Coordinator coordinator) throws DataIntegrityException {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Usuario SET estado = false WHERE idUsuario = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, coordinator.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se desactivo coordinador con id: " + coordinator.getIdUser());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al desactivar coordinador id: " + coordinator.getIdUser(), e);
            throw new DataIntegrityException("Error al desactivar coordinador", e);
        }
    }
    
}