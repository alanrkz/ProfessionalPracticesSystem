package Logic.Contracts;


import Logic.DTO.Professor;
import Logic.DTO.User;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public interface IProfessorDAO {
    
    public String registerProfessor(Professor professor);
    
    public String deactivateProfessor(User user, Professor professor);
    
    public String updateProfessor(Professor professor);
    
    public List<Professor> getProfessors();
    
}
