/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logic.Contracts;

import Logic.DTO.LinkedOrganization;
import java.util.List;

/**
 *
 * @author ELLIN JV
 */
public interface ILinkedOrganizationDAO {
    public String registerOrganization(LinkedOrganization org);
    public List<LinkedOrganization> getOrganizations();
}
