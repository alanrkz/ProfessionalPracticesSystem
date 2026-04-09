package Logic.Contracts;


import Logic.DTO.Course;

/**
 *
 * @author ELLIN JV
 */
public interface ICourseDAO {
    public String registerCourse(Course course);
    
    public String updateCourse(Course course);
    
    public String deleteCourse(int idCourse);
}
