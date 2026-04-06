package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IReportDAO;
import Logic.DTO.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alan rkz
 */
public class ReportDAO implements IReportDAO{
    
    @Override
    public String registerReport(Report report){
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
                return "El reporte fue registrado correctamente ";
            } else {
                return "Hubo problemas para registrar el reporte. Intente de nuevo mas tarde ";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema ";
        }
    }
    
    @Override
    public String modifyReport(Report report) {
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
                return "El reporte fue modificado correctamente.";
            } else {
                return "Hubo problemas para modificar el reporte. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
}
