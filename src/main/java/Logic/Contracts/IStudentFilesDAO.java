package Logic.Contracts;

import Logic.DTO.CatalogDocumentType;
import Logic.DTO.StudentFiles;
import Logic.Exceptions.DataIntegrityException;
import java.util.ArrayList;

/**
 *
 * @author ELLIN JV
 */
public interface IStudentFilesDAO {
    
    public boolean registerFiles(StudentFiles studentFiles) throws DataIntegrityException;
    
    public ArrayList<CatalogDocumentType> getDocumentTypes() throws DataIntegrityException;
    
}
