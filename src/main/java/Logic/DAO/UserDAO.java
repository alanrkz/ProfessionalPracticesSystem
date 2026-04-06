package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IUserDAO;
import Logic.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alan rkz
 */
public class UserDAO implements IUserDAO{
    
    @Override
    public String registerUser(User user){
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "INSERT INTO Proyecto (primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, estado, genero, correoElectronico, contraseña) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getMiddleName());
            preparedStatement.setString(3, user.getPaternalSurname());
            preparedStatement.setString(4, user.getMaternalSurname());
            preparedStatement.setBoolean(5, user.getStatus());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getPassword());
            
            int affectedRows = preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setIdUser(resultSet.getInt(1));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > 0) {
                return "El usuario fue registrado correctamente ";
            } else {
                return "Hubo problemas para registrar al usuario. Intente de nuevo mas tarde ";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema ";
        }
    }
    
    @Override
    public String modifyUser(User user) {
        try (Connection connection = DatabaseConnection.connect()) {
            String query = "UPDATE Usuario SET primerNombre = ?, segundoNombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, estado = ?, genero = ? WHERE idUser = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getMiddleName());
            preparedStatement.setString(3, user.getPaternalSurname());
            preparedStatement.setString(4, user.getMaternalSurname());
            preparedStatement.setBoolean(5, user.getStatus());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setInt(7, user.getIdUser());
            
            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El usuario fue modificado correctamente.";
            } else {
                return "Hubo problemas para modificar al usuario. Intente de nuevo mas tarde.";
            }
            
        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema.";
        }
    }
    
}
