package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IProjectApplicationsDAO;
import Logic.DTO.ProjectApplications;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProjectApplicationsDAO implements IProjectApplicationsDAO {

    private static final Logger logger = Logger.getLogger(ProjectApplicationsDAO.class.getName());

    @Override
    public boolean registerApplication(ProjectApplications projectApplication) throws DataIntegrityException {

        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO SolicitudesProyecto (idProyecto, matricula, estadoAsignacion) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, projectApplication.getIdProject());
            preparedStatement.setString(2, projectApplication.getEnrollment());
            preparedStatement.setBoolean(3, projectApplication.isAssignmentStatus());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                projectApplication.setIdProject(rs.getInt(1));
            }

            preparedStatement.close();
            rs.close();
            databaseConnection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se registro aplicacion para proyecto con ID: " + projectApplication.getIdProject());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar aplicacion a proyecto", e);
            throw new DataIntegrityException("Error al registrar aplicacion a proyecto", e);
        }
    }
    
}