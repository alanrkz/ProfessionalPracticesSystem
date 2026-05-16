package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ICatalogGender;
import Logic.DTO.CatalogGender;
import Logic.Exceptions.DataIntegrityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ELLIN JV
 */
public class CatalogGenderDAO implements ICatalogGender {
    
    private static final Logger logger = Logger.getLogger(CoordinatorDAO.class.getName());
    
    @Override
    public ArrayList<CatalogGender> getCatalogGender() throws DataIntegrityException {
        
        ArrayList<CatalogGender> listGenders = new ArrayList<>();
        String query = "SELECT * FROM CatalogoGenero;";

        try (Connection connection = DatabaseConnection.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CatalogGender catalogGender = new CatalogGender();
                catalogGender.setGenderId(resultSet.getInt("idGenero"));
                catalogGender.setGenderName(resultSet.getString("nombreGenero"));

                listGenders.add(catalogGender);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            
            logger.log(Level.SEVERE, "Error al obtener lista de géneros", e);
            throw new DataIntegrityException("Tuvimos problemas para obtener la lista de géneros. Intentelo mas tarde", e);
        }

        return listGenders;
    }
    
}
