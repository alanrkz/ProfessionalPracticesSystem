package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IMonthlyReportDAO;
import Logic.DTO.MonthlyReport;
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
public class MonthlyReportDAO implements IMonthlyReportDAO{
    
    @Override
    public String registerMonthlyReport(MonthlyReport monthlyReport) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO ReporteMensual (archivoReporteMensual, actividadesRealizadas, idReporte) VALUES (?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, monthlyReport.getMontlyReportFile());
            preparedStatement.setString(2, monthlyReport.getActivitiesPerformed());
            preparedStatement.setInt(3, monthlyReport.getIdReport());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                monthlyReport.setReportNumber(resultSet.getInt(1));
            }
            
            preparedStatement.close();
            resultSet.close();
            connection.close();
            
            if (affectedRows > 0) {
                return "El reporte mensual fue agregado correctamente.";
            } else {
                return "Hubo problemas para registrar el reporte mensual. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public String modifyMonthlyReport(MonthlyReport monthlyReport) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE ReporteMensual SET archivoReporteMensual = ?, actividadesRealizadas = ? WHERE numeroReporte = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, monthlyReport.getMontlyReportFile());
            preparedStatement.setString(2, monthlyReport.getActivitiesPerformed());
            preparedStatement.setInt(3, monthlyReport.getReportNumber());
            
            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El reporte mensual fue modificado correctamente.";
            } else {
                return "Hubo problemas para modificar el reporte mensual. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
    @Override
    public List<MonthlyReport> getMonthlyReports() {
        List <MonthlyReport> listMonthlyReports = new ArrayList<>();
        
        String query = "SELECT * FROM ReporteMensual;";
        
        try (Connection connection = DatabaseConnection.connect()) {
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                MonthlyReport monthlyReport = new MonthlyReport();
                monthlyReport.setReportNumber(resultSet.getInt("numeroReporte"));
                monthlyReport.setMontlyReportFile(resultSet.getString("archivoReporteMensual"));
                monthlyReport.setActivitiesPerformed(resultSet.getString("actividadesRealizadas"));
                monthlyReport.setIdReport(resultSet.getInt("idReporte"));
                
                listMonthlyReports.add(monthlyReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listMonthlyReports;
    }
    
}
