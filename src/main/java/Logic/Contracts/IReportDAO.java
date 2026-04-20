package Logic.Contracts;


import Logic.DTO.Report;

/**
 *
 * @author alan rkz
 */
public interface IReportDAO {
    
    public String registerReport(Report report);
    
    public String modifyReport(Report report);
    
}
