package Logic.DTO;


public class ProjectApplications {
    private int idProject;
    private String enrollment;
    private boolean assignmentStatus;

    
    public ProjectApplications() {
    }

    public ProjectApplications(int idProject, String enrollment, boolean assignmentStatus) {
        this.idProject = idProject;
        this.enrollment = enrollment;
        this.assignmentStatus = assignmentStatus;
    }

    
    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public boolean isAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(boolean assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }
    
}
