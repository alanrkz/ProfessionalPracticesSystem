package Logic.DTO;


public class MonthlyReport extends Report{
    private int reportNumber;
    private String montlyReportFile;
    private String ActivitiesPerformed;
    private int idReport;

    
    public MonthlyReport() {
    }

    public MonthlyReport(String montlyReportFile, String ActivitiesPerformed, int idReport) {
        this.montlyReportFile = montlyReportFile;
        this.ActivitiesPerformed = ActivitiesPerformed;
        this.idReport = idReport;
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

    public String getActivitiesPerformed() {
        return ActivitiesPerformed;
    }

    public void setActivitiesPerformed(String ActivitiesPerformed) {
        this.ActivitiesPerformed = ActivitiesPerformed;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }
     
}
