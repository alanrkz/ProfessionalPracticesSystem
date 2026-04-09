package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ILinkedOrganizationDAO;
import Logic.DTO.LinkedOrganization;
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
    public String registerOrganization(LinkedOrganization linkedOrganization) {
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

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            return "Registro exitoso. ID generado: " + idGenerado;

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar organización.";
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
    public String deactivateOrganization(int organizationId) {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String updateQuery = "UPDATE OrganizacionVinculada SET estado = 0 WHERE idOrganizacion = ?;";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, organizationId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return "La organización fue desactivada correctamente.";
            } else {
                return "No fue posible desactivar la organización.";
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return "Error en la conexión.";
        }
    }
}
