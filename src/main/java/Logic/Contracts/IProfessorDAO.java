package Logic.Contracts;


import Logic.DTO.Professor;
import Logic.DTO.User;


public interface IProfessorDAO {
    
    public String registerProfessor(Professor professor);
    
    public String deativateProfessor(User user, Professor professor);
    
}
