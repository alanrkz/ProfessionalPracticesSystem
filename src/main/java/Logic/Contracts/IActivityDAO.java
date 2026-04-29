package Logic.Contracts;


import Logic.DTO.Activity;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author alan rkz
 */
public interface IActivityDAO {
    
    public boolean addActivity(Activity activity) throws DataIntegrityException;   
    
    public boolean modifyActivity(Activity activity) throws DataIntegrityException;
    
}
