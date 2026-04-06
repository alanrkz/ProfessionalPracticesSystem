package Logic.Contracts;


import Logic.DTO.Student;
import Logic.DTO.User;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public interface IStudentDAO {

    public String registerStudent(Student student);

    public String deactivateStudent(User user, Student student);
    
    public List<Student> getStudents();

}
