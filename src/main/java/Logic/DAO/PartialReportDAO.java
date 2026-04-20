package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IPartialReportDAO;
import Logic.DTO.PartialReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public class PartialReportDAO implements IPartialReportDAO{
    
    @Override
    public String registerPartialReport(PartialReport partialReport) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO ReporteParcial (archivoReporteParcial, resultadosObtenidos, idReporte) VALUES (?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, partialReport.getPartialReportFile());
            preparedStatement.setString(2, partialReport.getResultsObtained());
            preparedStatement.setInt(3, partialReport.getIdReport());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                partialReport.setReportNumber(resultSet.getInt(1));
            }
            
            preparedStatement.close();
            resultSet.close();
            connection.close();
            
            if (affectedRows > 0) {
                return "El reporte parcial fue agregado correctamente.";
            } else {
                return "Hubo problemas para registrar el reporte parcial. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public String modifyPartialReport(PartialReport partialReport) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE ReporteParcial SET archivoReporteParcial = ?, resultadosObtenidos = ? WHERE numeroReporte = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, partialReport.getPartialReportFile());
            preparedStatement.setString(2, partialReport.getResultsObtained());
            preparedStatement.setInt(3, partialReport.getReportNumber());
            
            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El reporte parcial fue modificado correctamente.";
            } else {
                return "Hubo problemas para modificar el reporte parcial. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public List<PartialReport> getPartialReports() {
        List <PartialReport> listPartialReports = new ArrayList<>();
        
        String query = "SELECT * FROM ReporteParcial;";
        
        try (Connection connection = DatabaseConnection.connect()) {
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                PartialReport partialReport = new PartialReport();
                partialReport.setReportNumber(resultSet.getInt("numeroReporte"));
                partialReport.setPartialReportFile(resultSet.getString("archivoReporteParcial"));
                partialReport.setResultsObtained(resultSet.getString("resultadosObtenidos"));
                partialReport.setIdReport(resultSet.getInt("idReporte"));
                
                listPartialReports.add(partialReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listPartialReports;
    }
    
}
