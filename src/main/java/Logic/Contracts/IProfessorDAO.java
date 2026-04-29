package Logic.Contracts;


import Logic.DTO.Professor;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.util.ArrayList;

/**
 *
 * @author alan rkz
 */
public interface IProfessorDAO {
    
    public boolean registerProfessor(Professor professor) throws DataIntegrityException;
    
    public boolean deactivateProfessor(User user, Professor professor) throws DataIntegrityException;
    
    public boolean updateProfessor(Professor professor) throws DataIntegrityException;
    
    public ArrayList<Professor> getProfessors() throws DataIntegrityException;
    
}
