package Logic.DTO;

public class LinkedOrganization {
    private int idLikedOrganization;
    private String companyName;
    private String sector;
    private String directUsers;
    private String indirectUsers;
    private String email;
    private String phone;
    private String status;
    private String city;
    private String address;
    private String evaluationOV;

    
    public LinkedOrganization() {
    }

    public LinkedOrganization(int idLikedOrganization, String companyName, String sector, String directUsers, String indirectUsers, String email, String phone, String status, String city, String address, String evaluationOV) {
        this.idLikedOrganization = idLikedOrganization;
        this.companyName = companyName;
        this.sector = sector;
        this.directUsers = directUsers;
        this.indirectUsers = indirectUsers;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.city = city;
        this.address = address;
        this.evaluationOV = evaluationOV;
    }
    
    public int getIdLikedOrganization() {
        return idLikedOrganization;
    }

    public void setIdLikedOrganization(int idLikedOrganization) {
        this.idLikedOrganization = idLikedOrganization;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDirectUsers() {
        return directUsers;
    }

    public void setDirectUsers(String directUsers) {
        this.directUsers = directUsers;
    }

    public String getIndirectUsers() {
        return indirectUsers;
    }

    public void setIndirectUsers(String indirectUsers) {
        this.indirectUsers = indirectUsers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEvaluationOV() {
        return evaluationOV;
    }

    public void setEvaluationOV(String evaluationOV) {
        this.evaluationOV = evaluationOV;
    }
    
}
