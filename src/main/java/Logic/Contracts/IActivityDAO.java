package Logic.Contracts;


import Logic.DTO.Activity;

/**
 *
 * @author alan rkz
 */
public interface IActivityDAO {
    
    public String addActivity(Activity activity) ;
    
    public String modifyActivity(Activity activity);
    
}
