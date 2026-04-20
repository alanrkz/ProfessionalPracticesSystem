package Logic.Contracts;

import Logic.DTO.FinalReport;
import java.util.List;


public interface IFinalReportDAO {
    
    public String registerFinalReport(FinalReport finalReport);
    
    public String modifyFinalReport(FinalReport finalReport);
    
    public List<FinalReport> getFinalReports();
    
}
