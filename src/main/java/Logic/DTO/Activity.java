 package Logic.DTO;


import java.util.Date;


public class Activity {
    private int idActivity;
    private String activityName;
    private Date dueDateActivity;
    private String description;
    private double value;
    private int idActivityProject;
    private int idActivityReport;

    
    public Activity() {
    }

    public Activity(int idActivity, String activityName, Date dueDateActivity, String description, double value, int idActivityProject, int idActivityReport) {
        this.idActivity = idActivity;
        this.activityName = activityName;
        this.dueDateActivity = dueDateActivity;
        this.description = description;
        this.value = value;
        this.idActivityProject = idActivityProject;
        this.idActivityReport = idActivityReport;
    }

    
    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
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

    public int getIdActivityProject() {
        return idActivityProject;
    }

    public void setIdActivityProject(int idActivityProject) {
        this.idActivityProject = idActivityProject;
    }

    public int getIdActivityReport() {
        return idActivityReport;
    }

    public void setIdActivityReport(int idActivityReport) {
        this.idActivityReport = idActivityReport;
    }
      
}
