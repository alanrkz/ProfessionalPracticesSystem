package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IStudentFilesDAO;
import Logic.DTO.StudentFiles;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StudentFilesDAO implements IStudentFilesDAO {

    private static final Logger logger = Logger.getLogger(StudentFilesDAO.class.getName());

    @Override
    public boolean registerFiles(StudentFiles studentFiles) throws DataIntegrityException {

        boolean successfulRegister = false;
        int unaffectedRows = 0;
        try (Connection databaseConnection = DatabaseConnection.connect()) {

            String query = "INSERT INTO ExpedienteAlumno (nombreDocumento, direccionDocumento, tipoDocumento, matricula) VALUES (?, ?, ?, ?);";

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, studentFiles.getDocumentName());
            preparedStatement.setString(2, studentFiles.getDocumentURL());
            preparedStatement.setString(3, studentFiles.getDocumentType());
            preparedStatement.setString(4, studentFiles.getEnrollment());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                studentFiles.setIdDocument(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            databaseConnection.close();

            if (affectedRows > unaffectedRows) {
                successfulRegister = true;
            }
            
            return successfulRegister;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar archivos de estudiante", e);
            throw new DataIntegrityException("Error al registrar archivos", e);
        }
    }
    
}