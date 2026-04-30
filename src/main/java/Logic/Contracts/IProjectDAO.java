package Logic.Contracts;


import Logic.DTO.Project;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public interface IProjectDAO {
    
    public boolean registerProject(Project project) throws DataIntegrityException;
    
    public boolean deactivateProject(int idProject) throws DataIntegrityException;
    
    public List<Project> getProjects () throws DataIntegrityException;
    
}
