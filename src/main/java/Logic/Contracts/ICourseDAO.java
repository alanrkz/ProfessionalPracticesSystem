/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
