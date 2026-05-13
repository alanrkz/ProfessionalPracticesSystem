package Logic.DTO;


import java.sql.Date;


public class Professor extends User{
    private String numberStaff;
    private String shift;
    private Date registrationDate;
    private Date terminationDate;
    private boolean isCoordinator;
    private int idRol;

    
    public Professor() {
    }

    public Professor(String numberStaff, String shift, Date registrationDate, Date terminationDate, boolean isCoordinator, int idRol) {
        this.numberStaff = numberStaff;
        this.shift = shift;
        this.registrationDate = registrationDate;
        this.terminationDate = terminationDate;
        this.isCoordinator = isCoordinator;
        this.idRol = idRol;
    }

    public Professor(String numberStaff, String shift, Date registrationDate, Date terminationDate, boolean isCoordinator, int idRol, int idUser, String firstName, String middleName, String paternalSurname, String maternalSurname, String email, String password, String gender, Boolean status) {
        super(idUser, firstName, middleName, paternalSurname, maternalSurname, email, password, gender, status);
        this.numberStaff = numberStaff;
        this.shift = shift;
        this.registrationDate = registrationDate;
        this.terminationDate = terminationDate;
        this.isCoordinator = isCoordinator;
        this.idRol = idRol;
    }

    public String getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(String numberStaff) {
        this.numberStaff = numberStaff;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public boolean isIsCoordinator() {
        return isCoordinator;
    }

    public void setIsCoordinator(boolean isCoordinator) {
        this.isCoordinator = isCoordinator;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return numberStaff + " - " + firstName + " " + paternalSurname + " " + maternalSurname;
    }
     
}
