package Logic.DTO;

/**
 *
 * @author alan rkz
 */
public class CatalogDocumentType {
    private int idDocumentType;
    private String nameDocumentType;

    
    public CatalogDocumentType() {
    }

    public CatalogDocumentType(int idDocumentType, String nameDocumentType) {
        this.idDocumentType = idDocumentType;
        this.nameDocumentType = nameDocumentType;
    }

    
    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public String getNameDocumentType() {
        return nameDocumentType;
    }

    public void setNameDocumentType(String nameDocumentType) {
        this.nameDocumentType = nameDocumentType;
    }

    @Override
    public String toString() {
        return idDocumentType + " - " + nameDocumentType;
    }
    
}
