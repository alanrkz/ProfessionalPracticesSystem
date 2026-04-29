package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IMonthlyReportDAO;
import Logic.DTO.MonthlyReport;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MonthlyReportDAO implements IMonthlyReportDAO {

    private static final Logger logger = Logger.getLogger(MonthlyReportDAO.class.getName());

    @Override
    public boolean registerMonthlyReport(MonthlyReport monthlyReport) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se inserto reporte mensual idReporte: " + monthlyReport.getIdReport());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar reporte mensual", e);
            throw new DataIntegrityException("Error al registrar reporte mensual", e);
        }
    }

    @Override
    public boolean modifyMonthlyReport(MonthlyReport monthlyReport) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se actualizo reporte mensual numero: " + monthlyReport.getReportNumber());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al modificar reporte mensual", e);
            throw new DataIntegrityException("Error al modificar reporte mensual", e);
        }
    }

    @Override
    public List<MonthlyReport> getMonthlyReports() throws DataIntegrityException {
        List<MonthlyReport> listMonthlyReports = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM ReporteMensual;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MonthlyReport monthlyReport = new MonthlyReport();
                monthlyReport.setReportNumber(resultSet.getInt("numeroReporte"));
                monthlyReport.setMontlyReportFile(resultSet.getString("archivoReporteMensual"));
                monthlyReport.setActivitiesPerformed(resultSet.getString("actividadesRealizadas"));
                monthlyReport.setIdReport(resultSet.getInt("idReporte"));

                listMonthlyReports.add(monthlyReport);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener reportes mensuales", e);
            throw new DataIntegrityException("Error al obtener reportes mensuales", e);
        }

        return listMonthlyReports;
    }
    
}