package Logic.DTO;


import java.util.Date;


public class Report {
    private int idReport;
    private Date reportCompletionDate;
    private Date reportSubmissionDate;
    private String description;
    private String personalComments;
    private Double qualification;
    private String enrollment;

    
    public Report() {
    }

    public Report(int idReport, Date reportCompletionDate, Date reportSubmissionDate, String description, String personalComments, Double qualification, String enrollment) {
        this.idReport = idReport;
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
