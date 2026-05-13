package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IPartialReportDAO;
import Logic.DTO.PartialReport;
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


public class PartialReportDAO implements IPartialReportDAO {

    private static final Logger logger = Logger.getLogger(PartialReportDAO.class.getName());

    @Override
    public boolean registerPartialReport(PartialReport partialReport) throws DataIntegrityException {

        boolean successfulRegister = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ReporteParcial (archivoReporteParcial, tiempoPlaneado, tiempoReal, idReporte) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, partialReport.getPartialReportFile());
            preparedStatement.setString(2, partialReport.getPlannedTime());
            preparedStatement.setString(3, partialReport.getRealTime());
            preparedStatement.setInt(4, partialReport.getIdReport());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                partialReport.setReportNumber(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > unaffectedRows) {
                successfulRegister = true;
            }
            
            return successfulRegister;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar reporte parcial", e);
            throw new DataIntegrityException("Error al registrar reporte parcial", e);
        }
    }

    @Override
    public boolean modifyPartialReport(PartialReport partialReport) throws DataIntegrityException {

        boolean successfulModify = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE ReporteParcial SET archivoReporteParcial = ?, tiempoPlenado = ?, tiempoReal = ? WHERE numeroReporte = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, partialReport.getPartialReportFile());
            preparedStatement.setString(2, partialReport.getPlannedTime());
            preparedStatement.setString(3, partialReport.getRealTime());
            preparedStatement.setInt(4, partialReport.getReportNumber());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > unaffectedRows) {
                successfulModify = true;
            }
            
            return successfulModify;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al modificar reporte parcial", e);
            throw new DataIntegrityException("Error al modificar reporte parcial", e);
        }
    }
    
    @Override
    public List<PartialReport> getPartialReports() throws DataIntegrityException{
        
        List <PartialReport> listPartialReports = new ArrayList<>();
        
        String query = "SELECT * FROM ReporteParcial;";
        
        try (Connection connection = DatabaseConnection.connect()) {
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                PartialReport partialReport = new PartialReport();
                partialReport.setReportNumber(resultSet.getInt("numeroReporte"));
                partialReport.setPartialReportFile(resultSet.getString("archivoReporteParcial"));
                partialReport.setPlannedTime(resultSet.getString("tiempoPlaneado"));
                partialReport.setRealTime(resultSet.getString("tiempoReal"));
                partialReport.setIdReport(resultSet.getInt("idReporte"));
                
                listPartialReports.add(partialReport);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener reportes parciales", e);
            throw new DataIntegrityException("Error al obtener reportes parciales", e);
        }
        
        return listPartialReports;
    }
    
}
