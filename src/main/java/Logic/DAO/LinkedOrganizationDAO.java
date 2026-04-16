package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ILinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public class LinkedOrganizationDAO implements ILinkedOrganizationDAO {

    @Override
    public void registerOrganization(LinkedOrganization linkedOrganization) throws DataAccessException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO OrganizacionVinculada (nombreEmpresa, sector, usuarioDirectos, usuariosIndirectos, correoElectronico, telefono, estado, ciudad, direccion, evaluacionOV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            int idGenerado = 0;
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error al insertar organización", e);
        }
    }

    @Override
    public List<LinkedOrganization> getOrganizations() {
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
            e.printStackTrace();
        }

        return OrganizationsList;
    }

    @Override
    public boolean deactivateOrganization(int organizationId) throws DataAccessException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE OrganizacionVinculada SET estado = 0 WHERE idOrganizacion = ?";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, organizationId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException exception) {
            throw new DataAccessException("Error al desactivar la organización", exception);
        }
    }
}
