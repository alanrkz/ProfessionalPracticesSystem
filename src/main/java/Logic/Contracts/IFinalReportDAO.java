package Logic.Contracts;

import Logic.DTO.FinalReport;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;


public interface IFinalReportDAO {
    
    public boolean registerFinalReport(FinalReport finalReport) throws DataIntegrityException;
    
    public boolean modifyFinalReport(FinalReport finalReport) throws DataIntegrityException;
    
    public List<FinalReport> getFinalReports() throws DataIntegrityException;
    
}
