package Logic.Contracts;

import Logic.DTO.PartialReport;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;


public interface IPartialReportDAO {
    
    public boolean registerPartialReport(PartialReport partialReport) throws DataIntegrityException;
    
    public boolean modifyPartialReport(PartialReport partialReport) throws DataIntegrityException;
    
    public List<PartialReport> getPartialReports() throws DataIntegrityException;
    
}
