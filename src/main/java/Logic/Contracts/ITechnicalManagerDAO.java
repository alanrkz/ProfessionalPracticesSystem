package Logic.Contracts;


import Logic.DTO.TechnicalManager;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author ELLIN JV
 */
public interface ITechnicalManagerDAO {
    
    public boolean registerManager(TechnicalManager projectManager) throws DataIntegrityException;
    
    public boolean deactivateProjectManager(int projectManagerId) throws DataIntegrityException;
    
}
