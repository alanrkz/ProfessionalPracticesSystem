package Logic.Contracts;


import Logic.DTO.Student;
import Logic.DTO.User;


public interface IStudentDAO {

    public String registerStudent(Student student);

    public String deactivateStudent(User user, Student student);

}
