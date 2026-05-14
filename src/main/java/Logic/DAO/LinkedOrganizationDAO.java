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
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, linkedOrganization.getCity());
            preparedStatement.setString(9, linkedOrganization.getAddress());
            preparedStatement.setString(10, null);

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se inserto organizacion: " + linkedOrganization.getCompanyName());
                return false;
            }

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al registrar organizacion", exception);
            throw new DataIntegrityException("Error al insertar organizacion", exception);
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

    public List<LinkedOrganization> getOrganizations() throws DataIntegrityException {
        List<LinkedOrganization> organizationsList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM OrganizacionVinculada;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LinkedOrganization org = new LinkedOrganization();

                org.setIdLikedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                org.setCompanyName(resultSet.getString("nombreEmpresa"));
                org.setSector(resultSet.getString("sector"));
                org.setDirectUsers(resultSet.getString("usuarioDirectos"));
                org.setIndirectUsers(resultSet.getString("usuariosIndirectos"));
                org.setEmail(resultSet.getString("correoElectronico"));
                org.setPhone(resultSet.getString("telefono"));
                org.setStatus(resultSet.getBoolean("estado"));
                org.setCity(resultSet.getString("ciudad"));
                org.setAddress(resultSet.getString("direccion"));
                org.setEvaluation(resultSet.getString("evaluacionOV"));

                organizationsList.add(org);
            }

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al obtener las organizaciones", exception);
            throw new DataIntegrityException("Error al obtener las organizaciones", exception);
        }

        return organizationsList;
    }

}
