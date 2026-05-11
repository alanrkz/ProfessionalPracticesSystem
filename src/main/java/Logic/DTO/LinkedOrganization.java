package Logic.DTO;


public class LinkedOrganization {
    private int idLikedOrganization;
    private String companyName;
    private String sector;
    private String directUsers;
    private String indirectUsers;
    private String email;
    private String phone;
    private boolean status;
    private String address;

    
    public LinkedOrganization() {
    }

    public LinkedOrganization(int idLikedOrganization, String companyName, String sector, String directUsers, String indirectUsers, String email, String phone, boolean status, String address) {
        this.idLikedOrganization = idLikedOrganization;
        this.companyName = companyName;
        this.sector = sector;
        this.directUsers = directUsers;
        this.indirectUsers = indirectUsers;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.address = address;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return idLikedOrganization + " - " + companyName;
    }
    
}
