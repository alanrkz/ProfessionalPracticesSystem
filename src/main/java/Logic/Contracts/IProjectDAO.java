package Logic.Contracts;


import Logic.DTO.Project;

/**
 *
 * @author alan rkz
 */
public interface IProjectDAO {
    
    public String registerProject (Project project);
    
    public String deactivateProject(int idProject);
    
}
