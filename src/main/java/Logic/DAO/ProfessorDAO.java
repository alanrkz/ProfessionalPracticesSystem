package Logic.DAO;


import DataAccess.DatabaseConnection;
import Logic.Contracts.IProfessorDAO;
import Logic.DTO.Professor;
import Logic.DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProfessorDAO implements IProfessorDAO{
    
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.connect();
    
    @Override
        public String registerProfessor(Professor professor) {
        try {
            String query = "INSERT INTO Profesor VALUES (?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getNumberStaff());
            preparedStatement.setString(2, professor.getShift());
            preparedStatement.setDate(3, professor.getRegistrationDate());
            preparedStatement.setDate(4, professor.getTerminationDate());
            preparedStatement.setString(5, professor.getServiceTime());
            preparedStatement.setBoolean(6, professor.isIsCoordinator());
            preparedStatement.setInt(7, professor.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El profesor fue registrado correctamente";
            } else {
                return "Hubo problemas para registrar al Profesor. Intente de nuevo mas tarde";
            }

        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema";
        }
    }

    @Override
    public String deactivateProfessor(User user, Professor professor) {
        if (user.getIdUser() == professor.getIdUser()) {
            if (user.getStatus() == false) {
                try {
                    String query = "UPDATE Usuario SET estado = false WHERE id = ?;";
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, professor.getIdUser());

                    int affectedRows = preparedStatement.executeUpdate();

                    connection.close();
                    preparedStatement.close();

                    if (affectedRows > 0) {
                        return "El Profesor fue inactivado correctamente";
                    } else {
                        return "Hubo problemas para inactivar al Profesor. Intente de nuevo mas tarde";
                    }

                } catch (SQLException e) {
                    return "Tenemos problemas con la conexion al sistema";
                }
            } else {
                return "El Profesor ya esta inactivo";
            }
        } else {
            return "Profesor no encontrado";
        }
    }
    
    /**
    public String updateProfessor(int numberStaff){
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.connect();
            String query = "UPDATE Profesor SET turno = ?, esCoordinador = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getNumberStaff());
            preparedStatement.setString(2, professor.getShift());
            preparedStatement.setDate(3, professor.getRegistrationDate());
            preparedStatement.setDate(4, professor.getTerminationDate());
            preparedStatement.setString(5, professor.getServiceTime());
            preparedStatement.setBoolean(6, professor.isIsCoordinator());
            preparedStatement.setInt(7, professor.getIdUser());

            int affectedRows = preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();

            if (affectedRows > 0) {
                return "El profesor fue registrado correctamente";
            } else {
                return "Hubo problemas para registrar al Profesor. Intente de nuevo mas tarde";
            }

        } catch (SQLException e) {
            return "Tenemos problemas con la conexion al sistema";
        }
        
    }
    **/
    
}
