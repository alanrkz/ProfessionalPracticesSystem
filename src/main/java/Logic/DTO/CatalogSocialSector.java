package Logic.DTO;

/**
 *
 * @author alan rkz
 */
public class CatalogSocialSector {
    private int idSector;
    private String sectorName;

    public CatalogSocialSector() {
    }

    public CatalogSocialSector(int idSector, String sectorName) {
        this.idSector = idSector;
        this.sectorName = sectorName;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    @Override
    public String toString() {
        return idSector + " - " + sectorName;
    }
    
}
