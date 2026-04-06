package Logic.Contracts;


import Logic.DTO.MonthlyReport;
import java.util.List;

/**
 *
 * @author alan rkz
 */
public interface IMonthlyReportDAO {
    
    public String registerMonthlyReport(MonthlyReport monthlyReport);
    
    public String modifyReport(MonthlyReport monthlyReport);
    
    public List<MonthlyReport> getMonthlyReports();
    
}
