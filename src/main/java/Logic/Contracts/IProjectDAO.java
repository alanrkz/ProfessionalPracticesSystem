package Logic.Contracts;


import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author alan rkz
 */
public interface IProjectDAO {
    
    public boolean registerProject(Project project) throws DataIntegrityException;
    
    public boolean deactivateProject(int idProject) throws DataIntegrityException;
    
}
