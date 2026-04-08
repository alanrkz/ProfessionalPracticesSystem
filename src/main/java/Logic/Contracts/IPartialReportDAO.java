package Logic.Contracts;

import Logic.DTO.PartialReport;
import java.util.List;


public interface IPartialReportDAO {
    
    public String registerPartialReport(PartialReport partialReport);
    
    public String modifyPartialReport(PartialReport partialReport);
    
    public List<PartialReport> getPartialReports();
    
}
