package Logic.DTO;


import java.sql.Date;


public class FinalReport extends Report{
    private int reportNumber;
    private String finalReportFile;
    private String resultObtained;
    
    
    public FinalReport() {
    }

    public FinalReport(int reportNumber, String finalReportFile, String resultObtained) {
        this.reportNumber = reportNumber;
        this.finalReportFile = finalReportFile;
        this.resultObtained = resultObtained;
    }

    public FinalReport(int reportNumber, String finalReportFile, String resultObtained, int idReport, String description, Date dueDate, Double qualification, String observations, String enrollment) {
        super(idReport, description, dueDate, qualification, observations, enrollment);
        this.reportNumber = reportNumber;
        this.finalReportFile = finalReportFile;
        this.resultObtained = resultObtained;
    }

    
    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getFinalReportFile() {
        return finalReportFile;
    }

    public void setFinalReportFile(String finalReportFile) {
        this.finalReportFile = finalReportFile;
    }

    public String getResultObtained() {
        return resultObtained;
    }

    public void setResultObtained(String resultObtained) {
        this.resultObtained = resultObtained;
    }

}
