package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IFinalReportDAO;
import Logic.DTO.FinalReport;
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


public class FinalReportDAO implements IFinalReportDAO {

    private static final Logger logger = Logger.getLogger(FinalReportDAO.class.getName());

    @Override
    public boolean registerFinalReport(FinalReport finalReport) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se inserto reporte final idReporte: " + finalReport.getIdReport());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar reporte final", e);
            throw new DataIntegrityException("Error al registrar reporte final", e);
        }
    }

    @Override
    public boolean modifyFinalReport(FinalReport finalReport) throws DataIntegrityException {
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
                return true;
            } else {
                logger.warning("No se actualizo reporte final numero: " + finalReport.getReportNumber());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al modificar reporte final", e);
            throw new DataIntegrityException("Error al modificar reporte final", e);
        }
    }

    @Override
    public List<FinalReport> getFinalReports() throws DataIntegrityException {
        List<FinalReport> listFinalReports = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM ReporteFinal;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FinalReport finalReport = new FinalReport();
                finalReport.setReportNumber(resultSet.getInt("numeroReporte"));
                finalReport.setFinalReportFile(resultSet.getString("archivoReporteFinal"));
                finalReport.setFinalDeliverable(resultSet.getString("resultadoEntregable"));
                finalReport.setIdReport(resultSet.getInt("idReporte"));

                listFinalReports.add(finalReport);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener reportes finales", e);
            throw new DataIntegrityException("Error al obtener reportes finales", e);
        }

        return listFinalReports;
    }
    
}