package Logic.DAO;

import DataAccess.DatabaseConnection;
import Logic.Contracts.ICatalogSocialSectorDAO;
import Logic.DTO.CatalogSocialSector;
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
 * @author alan rkz
 */
public class CatalogSocialSectorDAO implements ICatalogSocialSectorDAO {
    
    private static final Logger logger = Logger.getLogger(CoordinatorDAO.class.getName());
    
    @Override
    public ArrayList<CatalogSocialSector> getSocialSectors() throws DataIntegrityException {
        ArrayList<CatalogSocialSector> listSocialSectors = new ArrayList<>();
        String query = "SELECT * FROM CatalogoSectorSocial;";

        try (Connection connection = DatabaseConnection.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CatalogSocialSector CatalogSocialSector = new CatalogSocialSector();
                CatalogSocialSector.setIdSector(resultSet.getInt("idSector"));
                CatalogSocialSector.setSectorName(resultSet.getString("nombreSector"));

                listSocialSectors.add(CatalogSocialSector);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener lista de profesores", e);
            throw new DataIntegrityException("Tuvimos problemas para obtener a los profesores. Intentelo mas tarde", e);
        }

        return listSocialSectors;
    }
    
}
