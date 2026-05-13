package Logic.DTO;

import java.sql.Date;


public class MonthlyReport extends Report{
    private int reportNumber;
    private String montlyReportFile;
    private String month;
    private int hoursReported;

    
    public MonthlyReport() {
    }

    public MonthlyReport(int reportNumber, String montlyReportFile, String month, int hoursReported) {
        this.reportNumber = reportNumber;
        this.montlyReportFile = montlyReportFile;
        this.month = month;
        this.hoursReported = hoursReported;
    }

    public MonthlyReport(int reportNumber, String montlyReportFile, String month, int hoursReported, int idReport, String description, Date dueDate, Double qualification, String observations, String enrollment) {
        super(idReport, description, dueDate, qualification, observations, enrollment);
        this.reportNumber = reportNumber;
        this.montlyReportFile = montlyReportFile;
        this.month = month;
        this.hoursReported = hoursReported;
    }

    
    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getMontlyReportFile() {
        return montlyReportFile;
    }

    public void setMontlyReportFile(String montlyReportFile) {
        this.montlyReportFile = montlyReportFile;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getHoursReported() {
        return hoursReported;
    }

    public void setHoursReported(int hoursReported) {
        this.hoursReported = hoursReported;
    }
     
}
