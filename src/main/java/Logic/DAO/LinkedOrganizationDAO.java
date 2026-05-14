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
        boolean successfulRegister = false;
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO OrganizacionVinculada (nombreEmpresa, sector, usuarioDirectos, usuariosIndirectos, correoElectronico, telefono, estado, direccion) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, linkedOrganization.getCompanyName());
            preparedStatement.setString(2, linkedOrganization.getSector());
            preparedStatement.setString(3, linkedOrganization.getDirectUsers());
            preparedStatement.setString(4, linkedOrganization.getIndirectUsers());
            preparedStatement.setString(5, linkedOrganization.getEmail());
            preparedStatement.setString(6, linkedOrganization.getPhone());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, linkedOrganization.getAddress());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                successfulRegister = true;
            }
            
            return successfulRegister;

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al registrar organizacion", exception);
            throw new DataIntegrityException("Error al insertar organizacion", exception);
        }
    }

    @Override
    public boolean deactivateOrganization(int organizationId) throws DataIntegrityException {
        boolean successfulDeactivate = false;
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE OrganizacionVinculada SET estado = false WHERE idOrganizacionVinculada = ?";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, organizationId);

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                successfulDeactivate = true;
            }
            
            return successfulDeactivate;

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al desactivar organizacion", exception);
            throw new DataIntegrityException("Error al desactivar organizacion", exception);
        }
    }

    @Override
    public boolean updateOrganization(LinkedOrganization linkedOrganization) throws DataIntegrityException {
        boolean successfulUpdate = false;
        try (Connection databaseConnection = DatabaseConnection.connect()) {
            String query = "UPDATE OrganizacionVinculada SET nombreEmpresa = ?, sector = ?, usuarioDirectos = ?, usuariosIndirectos = ?, correoElectronico = ?, "
                    + "telefono = ?, direccion = ? WHERE idOrganizacionVinculada = ?;";
            
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, linkedOrganization.getCompanyName());
            preparedStatement.setString(2, linkedOrganization.getSector());
            preparedStatement.setString(3, linkedOrganization.getDirectUsers());
            preparedStatement.setString(4, linkedOrganization.getIndirectUsers());
            preparedStatement.setString(5, linkedOrganization.getEmail());
            preparedStatement.setString(6, linkedOrganization.getPhone());
            preparedStatement.setString(7, linkedOrganization.getAddress());
            preparedStatement.setInt(8, linkedOrganization.getIdLikedOrganization());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                successfulUpdate = true;
            }
            
            return successfulUpdate;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar organizacion", e);
            throw new DataIntegrityException("Error al actualizar organizacion", e);
        }
    }

    @Override
    public List<LinkedOrganization> getOrganizations() throws DataIntegrityException {
        List<LinkedOrganization> organizationsList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM OrganizacionVinculada WHERE estado = true;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LinkedOrganization linkedOrganization = new LinkedOrganization();

                linkedOrganization.setIdLikedOrganization(resultSet.getInt("idOrganizacionVinculada"));
                linkedOrganization.setCompanyName(resultSet.getString("nombreEmpresa"));
                linkedOrganization.setSector(resultSet.getString("sector"));
                linkedOrganization.setDirectUsers(resultSet.getString("usuarioDirectos"));
                linkedOrganization.setIndirectUsers(resultSet.getString("usuariosIndirectos"));
                linkedOrganization.setEmail(resultSet.getString("correoElectronico"));
                linkedOrganization.setPhone(resultSet.getString("telefono"));
                linkedOrganization.setStatus(resultSet.getBoolean("estado"));
                linkedOrganization.setAddress(resultSet.getString("direccion"));

                organizationsList.add(linkedOrganization);
            }

        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "Error al obtener las organizaciones", exception);
            throw new DataIntegrityException("Error al obtener las organizaciones", exception);
        }
        
        return organizationsList;
    }

}
