package Logic.DTO;


import java.sql.Date;


public class Report {
    protected int idReport;
    protected String description;
    protected Date dueDate;
    protected Double qualification;
    protected String observations;
    protected String enrollment;

    
    public Report() {
    }

    public Report(int idReport, String description, Date dueDate, Double qualification, String observations, String enrollment) {
        this.idReport = idReport;
        this.description = description;
        this.dueDate = dueDate;
        this.qualification = qualification;
        this.observations = observations;
        this.enrollment = enrollment;
    }

    
    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
    
}
