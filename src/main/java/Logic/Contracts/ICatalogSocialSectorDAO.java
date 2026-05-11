package Logic.Contracts;

import Logic.DTO.CatalogSocialSector;
import Logic.Exceptions.DataIntegrityException;
import java.util.ArrayList;

/**
 *
 * @author alan rkz
 */
public interface ICatalogSocialSectorDAO {
    
    public ArrayList<CatalogSocialSector> getSocialSectors() throws DataIntegrityException;
    
}
