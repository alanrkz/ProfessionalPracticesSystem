package Logic.DTO;

import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public class SelfAssessment {
    
    private String studentEnrollment;
    private int projectId;
    private List<Integer> ratings;

    public SelfAssessment() {
    }
    
    public SelfAssessment(String studentEnrollment, int projectId, List ratings) {
        this.studentEnrollment = studentEnrollment;
        this.projectId = projectId;
        this.ratings = ratings;
    }

    public String getStudentEnrollment() {
        return studentEnrollment;
    }

    public void setStudentId(String studentEnrollment) {
        this.studentEnrollment = studentEnrollment;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List getRatings() {
        return ratings;
    }

    public void setRatings(List ratings) {
        this.ratings = ratings;
    }        
    
}
