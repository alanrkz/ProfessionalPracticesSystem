package Logic.Contracts;

import Logic.DTO.LinkedOrganization;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ILinkedOrganizationDAO {
    
     void registerOrganization(LinkedOrganization linkedOrganization) throws SQLException;
    
    List<LinkedOrganization> getOrganizations() throws SQLException;
    
    boolean deactivateOrganization(int organizationId) throws SQLException;
    
}
