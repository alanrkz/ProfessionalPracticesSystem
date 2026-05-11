package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.DTO.TechnicalManager;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Logic.Contracts.ITechnicalManagerDAO;


public class TechnicalManagerDAO implements ITechnicalManagerDAO {

    private static final Logger logger = Logger.getLogger(TechnicalManagerDAO.class.getName());

    @Override
    public boolean registerManager(TechnicalManager projectManager) throws DataIntegrityException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ResponsableProyecto (nombre, puesto, email, idProyecto) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, projectManager.getManagerName());
            preparedStatement.setString(2, projectManager.getManagerPosition());
            preparedStatement.setString(3, projectManager.getManagerEmail());
            preparedStatement.setInt(4, projectManager.getIdProject());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                projectManager.setIdProjectManager(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se registro responsable de proyecto");
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar responsable de proyecto", e);
            throw new DataIntegrityException("Error al registrar responsable de proyecto", e);
        }
    }

    @Override
    public boolean deactivateProjectManager(int projectManagerId) throws DataIntegrityException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE ResponsableProyecto SET estado = 'Inactivo' WHERE idResponsable = ?;";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, projectManagerId);

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se desactivo responsable con id: " + projectManagerId);
                return false;
            }

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al desactivar responsable", exception);
            throw new DataIntegrityException("Error al desactivar responsable", exception);
        }
    }
    
}