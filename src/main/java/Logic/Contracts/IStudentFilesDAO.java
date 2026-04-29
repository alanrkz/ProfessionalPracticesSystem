package Logic.Contracts;

import Logic.DTO.StudentFiles;
import Logic.Exceptions.DataIntegrityException;

/**
 *
 * @author ELLIN JV
 */
public interface IStudentFilesDAO {
    
    public boolean registerFiles(StudentFiles studentFiles) throws DataIntegrityException;
}
