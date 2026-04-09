/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logic.Contracts;

import Logic.DTO.ProjectManager;

/**
 *
 * @author ELLIN JV
 */
public interface IProjectManagerDAO {
    
    public String registerManager(ProjectManager projectManager);
    
    public String deactivateProjectManager(int projectManagerId);
}
