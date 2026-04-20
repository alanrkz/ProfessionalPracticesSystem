package Logic.Contracts;


import Logic.DTO.Course;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ICourseDAO {
    public String registerCourse(Course course);
    public List<Course> getCourses();
    
}
