/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Logic.Contracts;

import Logic.DTO.ProjectApplications;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author ELLIN JV
 */
public interface IProjectApplicationsDAO {
    
    public boolean registerApplication(ProjectApplications projectApplication) throws DataIntegrityException;
    
}
