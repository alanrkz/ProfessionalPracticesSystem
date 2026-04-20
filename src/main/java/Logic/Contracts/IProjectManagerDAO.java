package Logic.Contracts;


import Logic.DTO.ProjectManager;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author ELLIN JV
 */
public interface IProjectManagerDAO {
    
    public boolean registerManager(ProjectManager projectManager) throws DataIntegrityException;
    
    public boolean deactivateProjectManager(int projectManagerId) throws DataIntegrityException;
    
}
