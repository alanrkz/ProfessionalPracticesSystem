package Logic.DTO;


import java.sql.Date;


public class Report {
    protected int idReport;
    protected Date reportCompletionDate;
    protected Date reportSubmissionDate;
    protected String description;
    protected String personalComments;
    protected Double qualification;
    protected String enrollment;

    
    public Report() {
    }

    public Report(Date reportCompletionDate, Date reportSubmissionDate, String description, String personalComments, Double qualification, String enrollment) {
        this.reportCompletionDate = reportCompletionDate;
        this.reportSubmissionDate = reportSubmissionDate;
        this.description = description;
        this.personalComments = personalComments;
        this.qualification = qualification;
        this.enrollment = enrollment;
    }
    

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public Date getReportCompletionDate() {
        return reportCompletionDate;
    }

    public void setReportCompletionDate(Date reportCompletionDate) {
        this.reportCompletionDate = reportCompletionDate;
    }

    public Date getReportSubmissionDate() {
        return reportSubmissionDate;
    }

    public void setReportSubmissionDate(Date reportSubmissionDate) {
        this.reportSubmissionDate = reportSubmissionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonalComments() {
        return personalComments;
    }

    public void setPersonalComments(String personalComments) {
        this.personalComments = personalComments;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }    
    
}
