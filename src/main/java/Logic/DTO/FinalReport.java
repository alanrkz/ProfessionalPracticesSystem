package Logic.DTO;


public class FinalReport {
    private int reportNumber;
    private String finalReportFile;
    private String finalDeliverable;
    private int idReport;

    
    public FinalReport() {
    }

    public FinalReport(int reportNumber, String finalReportFile, String finalDeliverable, int idReport) {
        this.reportNumber = reportNumber;
        this.finalReportFile = finalReportFile;
        this.finalDeliverable = finalDeliverable;
        this.idReport = idReport;
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

    public String getFinalDeliverable() {
        return finalDeliverable;
    }

    public void setFinalDeliverable(String finalDeliverable) {
        this.finalDeliverable = finalDeliverable;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }
    
}
