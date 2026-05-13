package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IReportDAO;
import Logic.DTO.Report;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReportDAO implements IReportDAO {

    private static final Logger logger = Logger.getLogger(ReportDAO.class.getName());

    @Override
    public boolean registerReport(Report report) throws DataIntegrityException {

        boolean successfulRegister = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Reporte (descripcion, fechaEntrega, calificacion, observaciones, matricula) VALUES(?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, report.getDescription());
            preparedStatement.setDate(2, report.getDueDate());
            preparedStatement.setDouble(3, report.getQualification());
            preparedStatement.setString(4, report.getObservations());
            preparedStatement.setString(5, report.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                report.setIdReport(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > unaffectedRows) {
                successfulRegister = true;
            }
            
            return successfulRegister;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar reporte", e);
            throw new DataIntegrityException("Error al registrar reporte", e);
        }
    }

    @Override
    public boolean modifyReport(Report report) throws DataIntegrityException {

        boolean successfulModify = false;
        int unaffectedRows = 0;
        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Reporte SET descripcion = ?, fechaEntrega = ?, calificacion = ?, observaciones = ? WHERE idReporte = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, report.getDescription());
            preparedStatement.setDate(2, report.getDueDate());
            preparedStatement.setDouble(3, report.getQualification());
            preparedStatement.setString(4, report.getObservations());
            preparedStatement.setInt(5, report.getIdReport());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > unaffectedRows) {
                successfulModify = true;
            }
            
            return successfulModify;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al modificar reporte", e);
            throw new DataIntegrityException("Error al modificar reporte", e);
        }
    }
    
}