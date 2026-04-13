package Logic.Contracts;

import Logic.DTO.LinkedOrganization;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ILinkedOrganizationDAO {
    
    public String registerOrganization(LinkedOrganization linkedOrganization);
    
    public List<LinkedOrganization> getOrganizations();
    
    public String deactivateOrganization(int OrganizationId);
}
