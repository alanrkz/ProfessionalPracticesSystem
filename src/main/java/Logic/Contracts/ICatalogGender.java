package Logic.Contracts;


import Logic.DTO.CatalogGender;
import Logic.Exceptions.DataIntegrityException;
import java.util.ArrayList;

/**
 *
 * @author ELLIN JV
 */
public interface ICatalogGender {
    
    public ArrayList<CatalogGender> getCatalogGender() throws DataIntegrityException;   
}
