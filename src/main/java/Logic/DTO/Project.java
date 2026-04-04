package Logic.DTO;


public class Project {
    private int idProject;
    private String projectName;
    private String duration;
    private String description;
    private int availableSpaces;
    private boolean status;
    private String projectMethodology;
    private int idLikedOrganization;

    
    public Project() {
    }

    public Project(int idProject, String projectName, String duration, String description, int availableSpaces, boolean status, String projectMethodology, int idLikedOrganization) {
        this.idProject = idProject;
        this.projectName = projectName;
        this.duration = duration;
        this.description = description;
        this.availableSpaces = availableSpaces;
        this.status = status;
        this.projectMethodology = projectMethodology;
        this.idLikedOrganization = idLikedOrganization;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProjectMethodology() {
        return projectMethodology;
    }

    public void setProjectMethodology(String projectMethodology) {
        this.projectMethodology = projectMethodology;
    }

    public int getIdLikedOrganization() {
        return idLikedOrganization;
    }

    public void setIdLikedOrganization(int idLikedOrganization) {
        this.idLikedOrganization = idLikedOrganization;
    }
    
    
}
