package Logic.DTO;


public class TechnicalManager {
    private int idTechnicalManager;
    private String managerName;
    private String managerPosition;
    private String managerEmail;
    private int idLinkedOrganization;
    
    
    public TechnicalManager() {
    }

    public TechnicalManager(int idTechnicalManager, String managerName, String managerPosition, String managerEmail, int idLinkedOrganization) {
        this.idTechnicalManager = idTechnicalManager;
        this.managerName = managerName;
        this.managerPosition = managerPosition;
        this.managerEmail = managerEmail;
        this.idLinkedOrganization = idLinkedOrganization;
    }

    
    public int getIdTechnicalManager() {
        return idTechnicalManager;
    }

    public void setIdTechnicalManager(int idTechnicalManager) {
        this.idTechnicalManager = idTechnicalManager;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public int getIdLinkedOrganization() {
        return idLinkedOrganization;
    }

    public void setIdLinkedOrganization(int idLinkedOrganization) {
        this.idLinkedOrganization = idLinkedOrganization;
    }
    
}
