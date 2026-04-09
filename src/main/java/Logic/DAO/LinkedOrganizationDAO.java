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
    
    public String registerOrganization(LinkedOrganization org) {
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO OrganizacionVinculada (nombreEmpresa, sector, usuariosDirectos, usuariosIndirectos, email, telefono, estado, ciudad, direccion, evaluacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, org.getCompanyName());
            ps.setString(2, org.getSector());
            ps.setString(3, org.getDirectUsers());
            ps.setString(4, org.getIndirectUsers());
            ps.setString(5, org.getEmail());
            ps.setString(6, org.getPhone());
            ps.setString(7, org.getStatus());
            ps.setString(8, org.getCity());
            ps.setString(9, org.getAddress());
            ps.setString(10, org.getEvaluation());

            int rows = ps.executeUpdate();

            return (rows > 0) ? "Organización registrada." : "Error al registrar.";

        } catch (SQLException e) {
            return "Error de conexión.";
        }
    }

    public List<LinkedOrganization> getOrganizations() {
        List<LinkedOrganization> list = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM OrganizacionVinculada;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LinkedOrganization org = new LinkedOrganization();
                org.setIdLikedOrganization(rs.getInt("idOrganizacion"));
                org.setCompanyName(rs.getString("nombreEmpresa"));

                list.add(org);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
