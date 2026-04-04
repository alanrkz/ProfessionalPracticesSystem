package Logic.DTO;


import java.util.Date;


public class Activity {
    private int id;
    private String activityName;
    private Date dueDateActivity;
    private String description;
    private double value;
    private int idProject;
    private int idReport;

    
    public Activity() {
    }

    public Activity(int id, String activityName, Date dueDateActivity, String description, double value, int idProject, int idReport) {
        this.id = id;
        this.activityName = activityName;
        this.dueDateActivity = dueDateActivity;
        this.description = description;
        this.value = value;
        this.idProject = idProject;
        this.idReport = idReport;
    }

    
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getDueDateActivity() {
        return dueDateActivity;
    }

    public void setDueDateActivity(Date dueDateActivity) {
        this.dueDateActivity = dueDateActivity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }
      
}
