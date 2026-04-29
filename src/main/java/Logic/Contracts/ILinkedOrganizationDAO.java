package Logic.Contracts;

import Logic.DTO.LinkedOrganization;
import Logic.Exceptions.DataIntegrityException;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ILinkedOrganizationDAO {
    
    public boolean registerOrganization(LinkedOrganization linkedOrganization) throws DataIntegrityException;
    
    public boolean deactivateOrganization(int organizationId) throws DataIntegrityException;

    public List<LinkedOrganization> getOrganizations() throws DataIntegrityException;
    
}
