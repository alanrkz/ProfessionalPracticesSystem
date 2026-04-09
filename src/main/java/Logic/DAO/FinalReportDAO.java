package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IFinalReportDAO;
import Logic.DTO.FinalReport;
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
public class FinalReportDAO implements IFinalReportDAO{
    
    @Override
    public String registerFinalReport(FinalReport finalReport) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO ReporteFinal (archivoReporteFinal, resultadoEntregable, idReporte) VALUES (?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, finalReport.getFinalReportFile());
            preparedStatement.setString(2, finalReport.getFinalDeliverable());
            preparedStatement.setInt(3, finalReport.getIdReport());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                finalReport.setReportNumber(resultSet.getInt(1));
            }
            
            preparedStatement.close();
            resultSet.close();
            connection.close();
            
            if (affectedRows > 0) {
                return "El reporte final fue agregado correctamente.";
            } else {
                return "Hubo problemas para registrar el reporte final. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public String modifyFinalReport(FinalReport finalReport) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE ReporteFinal SET archivoReporteFinal = ?, resultadosEntregable = ? WHERE numeroReporte = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, finalReport.getFinalReportFile());
            preparedStatement.setString(2, finalReport.getFinalDeliverable());
            preparedStatement.setInt(3, finalReport.getReportNumber());
            
            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El reporte final fue modificado correctamente.";
            } else {
                return "Hubo problemas para modificar el reporte final. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public List<FinalReport> getFinalReports() {
        List <FinalReport> listFinalReports = new ArrayList<>();
        
        String query = "SELECT * FROM ReporteFinal;";
        
        try (Connection connection = DatabaseConnection.connect()) {
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                FinalReport finalReport = new FinalReport();
                finalReport.setReportNumber(resultSet.getInt("numeroReporte"));
                finalReport.setFinalReportFile(resultSet.getString("archivoReporteFinal"));
                finalReport.setFinalDeliverable(resultSet.getString("resultadoEntregable"));
                finalReport.setIdReport(resultSet.getInt("idReporte"));
                
                listFinalReports.add(finalReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listFinalReports;
    }
    
}
