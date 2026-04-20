package Logic.Contracts;


import Logic.DTO.MonthlyReport;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public interface IMonthlyReportDAO {
    
    public boolean registerMonthlyReport(MonthlyReport monthlyReport) throws DataIntegrityException;
    
    public boolean modifyMonthlyReport(MonthlyReport monthlyReport) throws DataIntegrityException;
    
    public List<MonthlyReport> getMonthlyReports() throws DataIntegrityException;
    
}
