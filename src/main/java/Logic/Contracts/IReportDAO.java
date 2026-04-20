package Logic.Contracts;


import Logic.DTO.Report;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author alan rkz
 */
public interface IReportDAO {
    
    public boolean registerReport(Report report) throws DataIntegrityException;
    
    public boolean modifyReport(Report report) throws DataIntegrityException;
    
}
