/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.DTO.LinkedOrganization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public class LinkedOrganizationDAO {
    
    public String registerOrganization(LinkedOrganization linkedOrganization) {
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO OrganizacionVinculada (nombreEmpresa, sector, usuariosDirectos, usuariosIndirectos, email, telefono, estado, ciudad, direccion, evaluacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

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

            if (affectedRows > 0) {
                return "La organización fue registrada correctamente.";
            } else {
                return "No fue posible registrar la organización.";
            }

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

    public List<LinkedOrganization> getOrganizations() {
        List<LinkedOrganization> OrganizationsList = new ArrayList<>();

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM OrganizacionVinculada;";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LinkedOrganization linkedOrganization = new LinkedOrganization();
                linkedOrganization.setIdLikedOrganization(resultSet.getInt("idOrganizacion"));
                linkedOrganization.setCompanyName(resultSet.getString("nombreEmpresa"));

                OrganizationsList.add(linkedOrganization);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return OrganizationsList;
    }
}
