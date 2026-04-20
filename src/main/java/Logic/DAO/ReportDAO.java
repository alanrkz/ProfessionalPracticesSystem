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

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Reporte (fechaEntrega, fechaRealizacion, descripcion, calificacion, comentariosPersonales, matricula) VALUES(?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, report.getReportSubmissionDate());
            preparedStatement.setDate(2, report.getReportCompletionDate());
            preparedStatement.setString(3, report.getDescription());
            preparedStatement.setDouble(4, report.getQualification());
            preparedStatement.setString(5, report.getPersonalComments());
            preparedStatement.setString(6, report.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                report.setIdReport(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se registro reporte");
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar reporte", e);
            throw new DataIntegrityException("Error al registrar reporte", e);
        }
    }

    @Override
    public boolean modifyReport(Report report) throws DataIntegrityException {

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Reporte SET fechaEntrega = ?, descripcion = ?, calificacion = ?, comentariosPersonales = ? WHERE idReporte = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, report.getReportSubmissionDate());
            preparedStatement.setString(2, report.getDescription());
            preparedStatement.setDouble(3, report.getQualification());
            preparedStatement.setString(4, report.getPersonalComments());
            preparedStatement.setInt(5, report.getIdReport());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se modifico reporte: " + report.getIdReport());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al modificar reporte", e);
            throw new DataIntegrityException("Error al modificar reporte", e);
        }
    }
    
}