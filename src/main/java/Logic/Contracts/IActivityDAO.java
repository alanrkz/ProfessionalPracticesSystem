package Logic.Contracts;

import Logic.DTO.Activity;


public interface IActivityDAO {
    
    public String addActivity(Activity activity);
    
    public String modifyActivity(Activity activity);
    
}
