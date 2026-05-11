package Logic.DTO;


public class TechnicalManager {
    private int idProjectManager;
    private String managerName;
    private String managerPosition;
    private String managerEmail;
    private int idProject;

    
    public TechnicalManager() {
    }

    public TechnicalManager(int idProjectManager, String managerName, String managerPosition, String managerEmail, int idProject) {
        this.idProjectManager = idProjectManager;
        this.managerName = managerName;
        this.managerPosition = managerPosition;
        this.managerEmail = managerEmail;
        this.idProject = idProject;
    }

    public int getIdProjectManager() {
        return idProjectManager;
    }

    public void setIdProjectManager(int idProjectManager) {
        this.idProjectManager = idProjectManager;
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

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }
    
    
}
