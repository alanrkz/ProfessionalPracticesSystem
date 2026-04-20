package Logic.Contracts;


import Logic.DTO.Student;
import Logic.DTO.User;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public interface IStudentDAO {

    public boolean registerStudent(Student student) throws DataIntegrityException;

    public boolean deactivateStudent(User user, Student student) throws DataIntegrityException;
    
    public List<Student> getStudents() throws DataIntegrityException;

}
