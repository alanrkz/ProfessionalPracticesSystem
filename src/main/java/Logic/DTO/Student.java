package Logic.DTO;


import java.sql.Date;


public class Student extends User{
    
    private String enrollment;
    private Date birthdate;
    private int hoursCovered;
    private boolean indigenousLanguage;
    private String nrc;
    private int idRol;

    public Student() {
    }

    public Student(String enrollment, Date birthdate, int hoursCovered, boolean indigenousLanguage, String nrc, int idRol) {
        this.enrollment = enrollment;
        this.birthdate = birthdate;
        this.hoursCovered = hoursCovered;
        this.indigenousLanguage = indigenousLanguage;
        this.nrc = nrc;
        this.idRol = idRol;
    }

    public Student(String enrollment, Date birthdate, int hoursCovered, boolean indigenousLanguage, String nrc, int idRol, int idUser, String firstName, String middleName, String paternalSurname, String maternalSurname, String email, String password, String gender, Boolean status) {
        super(idUser, firstName, middleName, paternalSurname, maternalSurname, email, password, gender, status);
        this.enrollment = enrollment;
        this.birthdate = birthdate;
        this.hoursCovered = hoursCovered;
        this.indigenousLanguage = indigenousLanguage;
        this.nrc = nrc;
        this.idRol = idRol;
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
    
    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

}
