package Logic.DTO;

import java.sql.Date;


public class PartialReport extends Report{
    private int reportNumber;
    private String partialReportFile;
    private String plannedTime;
    private String realTime;

    
    public PartialReport() {
    }

    public PartialReport(int reportNumber, String partialReportFile, String plannedTime, String realTime) {
        this.reportNumber = reportNumber;
        this.partialReportFile = partialReportFile;
        this.plannedTime = plannedTime;
        this.realTime = realTime;
    }

    public PartialReport(int reportNumber, String partialReportFile, String plannedTime, String realTime, int idReport, String description, Date dueDate, Double qualification, String observations, String enrollment) {
        super(idReport, description, dueDate, qualification, observations, enrollment);
        this.reportNumber = reportNumber;
        this.partialReportFile = partialReportFile;
        this.plannedTime = plannedTime;
        this.realTime = realTime;
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

    public String getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }
    
}
