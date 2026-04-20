package Logic.DTO;


public class PartialReport extends Report{
    private int reportNumber;
    private String partialReportFile;
    private String resultsObtained;
    private int idReport;

    
    public PartialReport() {
    }

    public PartialReport(int reportNumber, String partialReportFile, String resultsObtained, int idReport) {
        this.reportNumber = reportNumber;
        this.partialReportFile = partialReportFile;
        this.resultsObtained = resultsObtained;
        this.idReport = idReport;
    }

    
    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getPartialReportFile() {
        return partialReportFile;
    }

    public void setPartialReportFile(String partialReportFile) {
        this.partialReportFile = partialReportFile;
    }

    public String getResultsObtained() {
        return resultsObtained;
    }

    public void setResultsObtained(String resultsObtained) {
        this.resultsObtained = resultsObtained;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }
    
}
