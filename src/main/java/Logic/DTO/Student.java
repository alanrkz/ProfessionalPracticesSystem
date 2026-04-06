package Logic.DTO;


import java.sql.Date;


public class Student extends User{
    
    private String enrollment;
    private Date birthdate;
    private int hoursCovered;
    private boolean indigenousLanguage;
    private String socialSector;
    private int idUser;
    private String nrc;

    
    public Student() {
    }

    public Student(String enrollment, Date birthdate, int hoursCovered, boolean indigenousLanguage, String socialSector, int idUser, String nrc) {
        this.enrollment = enrollment;
        this.birthdate = birthdate;
        this.hoursCovered = hoursCovered;
        this.indigenousLanguage = indigenousLanguage;
        this.socialSector = socialSector;
        this.idUser = idUser;
        this.nrc = nrc;
    }

    
    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getHoursCovered() {
        return hoursCovered;
    }

    public void setHoursCovered(int hoursCovered) {
        this.hoursCovered = hoursCovered;
    }

    public boolean isIndigenousLanguage() {
        return indigenousLanguage;
    }

    public void setIndigenousLanguage(boolean indigenousLanguage) {
        this.indigenousLanguage = indigenousLanguage;
    }

    public String getSocialSector() {
        return socialSector;
    }

    public void setSocialSector(String socialSector) {
        this.socialSector = socialSector;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }
   
}
