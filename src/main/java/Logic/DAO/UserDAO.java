package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.IUserDAO;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    @Override
    public boolean registerUser(User user) throws DataIntegrityException {

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "INSERT INTO Usuario (primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, estado, genero, correoElectronico, contraseña) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

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
                int idGenerado = resultSet.getInt(1);
                user.setIdUser(idGenerado);
                System.out.println("ID GENERADO USER: " + idGenerado);
            } else {
                throw new SQLException("No se obtuvo el ID generado");
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();

            if (affectedRows > 0) {
                return true;
            } else {
                logger.warning("No se registro usuario: " + user.getEmail());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar usuario", e);
            throw new DataIntegrityException("Error al registrar usuario", e);
        }
    }

    @Override
    public boolean modifyUser(User user) throws DataIntegrityException {

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "UPDATE Usuario SET primerNombre = ?, segundoNombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, estado = ?, genero = ? WHERE idUsuario = ?;";

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
                return true;
            } else {
                logger.warning("No se modifico usuario con id: " + user.getIdUser());
                return false;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al modificar usuario", e);
            throw new DataIntegrityException("Error al modificar usuario", e);
        }
    }

    public User login(String email, String password) throws DataIntegrityException {

        try (Connection connection = DatabaseConnection.connect()) {

            String query = "SELECT * FROM Usuario WHERE correoElectronico = ? AND contraseña = ? AND estado = true";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("idUsuario"));
                user.setFirstName(resultSet.getString("primerNombre"));
                user.setEmail(resultSet.getString("correoElectronico"));
                user.setGender(resultSet.getString("genero"));
                user.setStatus(resultSet.getBoolean("estado"));

                return user;
            }

            resultSet.close();
            preparedStatement.close();

            return null;

        } catch (SQLException e) {
            throw new DataIntegrityException("Error en login", e);
        }
    }

}
