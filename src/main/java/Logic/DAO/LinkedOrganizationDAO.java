package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ILinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinkedOrganizationDAO implements ILinkedOrganizationDAO {

    private static final Logger logger = Logger.getLogger(LinkedOrganizationDAO.class.getName());

    @Override
    public boolean registerOrganization(LinkedOrganization linkedOrganization) throws DataIntegrityException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO OrganizacionVinculada (nombreEmpresa, sector, usuarioDirectos, usuariosIndirectos, correoElectronico, telefono, estado, ciudad, direccion, evaluacionOV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, linkedOrganization.getCompanyName());
            preparedStatement.setString(2, linkedOrganization.getSector());
            preparedStatement.setString(3, linkedOrganization.getDirectUsers());
            preparedStatement.setString(4, linkedOrganization.getIndirectUsers());
            preparedStatement.setString(5, linkedOrganization.getEmail());
            preparedStatement.setString(6, linkedOrganization.getPhone());
            preparedStatement.setString(7, linkedOrganization.getStatus());
            preparedStatement.setString(8, linkedOrganization.getCity());
            preparedStatement.setString(9, linkedOrganization.getAddress());
            preparedStatement.setString(10, linkedOrganization.getEvaluation());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se inserto organizacion: " + linkedOrganization.getCompanyName());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar organizacion", e);
            throw new DataIntegrityException("Error al insertar organizacion", e);
        }
    }

    @Override
    public boolean deactivateOrganization(int organizationId) throws DataIntegrityException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE OrganizacionVinculada SET estado = 0 WHERE idOrganizacion = ?";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, organizationId);

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se desactivo organizacion con id: " + organizationId);
                return false;
            }

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al desactivar organizacion", exception);
            throw new DataIntegrityException("Error al desactivar organizacion", exception);
        }
    }
    
    @Override
    public List<LinkedOrganization> getOrganizations() throws DataIntegrityException{
        List<LinkedOrganization> OrganizationsList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM OrganizacionVinculada;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LinkedOrganization linkedOrganization = new LinkedOrganization();
                linkedOrganization.setIdLikedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                linkedOrganization.setCompanyName(resultSet.getString("nombreEmpresa"));

                OrganizationsList.add(linkedOrganization);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener las organizaciones", e);
            throw new DataIntegrityException("Error al obtener las organizaciones", e);
        }

        return OrganizationsList;
    }
    
}