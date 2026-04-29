package Logic.Contracts;


import Logic.DTO.Course;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ICourseDAO {
    
    public boolean registerCourse(Course course) throws DataIntegrityException;
    
    public List<Course> getCourses() throws DataIntegrityException;
    
}
