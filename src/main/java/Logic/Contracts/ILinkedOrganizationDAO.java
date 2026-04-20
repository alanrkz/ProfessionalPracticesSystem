package Logic.Contracts;

import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ILinkedOrganizationDAO {
    
     void registerOrganization(LinkedOrganization linkedOrganization) throws DataIntegrityException;
    
    List<LinkedOrganization> getOrganizations() throws DataIntegrityException;
    
    boolean deactivateOrganization(int organizationId) throws DataIntegrityException;
    
}
