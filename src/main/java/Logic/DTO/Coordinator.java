package Logic.DTO;


import java.sql.Date;


public class Coordinator extends User{
    private String numberStaff;
    private Date registrationDate;
    private Date terminationDate;
    private boolean statusCoordinator;
    private int idRol;

    
    public Coordinator() {
    }

    public Coordinator(String numberStaff, Date registrationDate, Date terminationDate, boolean statusCoordinator, int idRol) {
        this.numberStaff = numberStaff;
        this.registrationDate = registrationDate;
        this.terminationDate = terminationDate;
        this.statusCoordinator = statusCoordinator;
        this.idRol = idRol;
    }

    public Coordinator(String numberStaff, Date registrationDate, Date terminationDate, boolean statusCoordinator, int idRol, int idUser, String firstName, String middleName, String paternalSurname, String maternalSurname, String email, String password, String gender, Boolean status) {
        super(idUser, firstName, middleName, paternalSurname, maternalSurname, email, password, gender, status);
        this.numberStaff = numberStaff;
        this.registrationDate = registrationDate;
        this.terminationDate = terminationDate;
        this.statusCoordinator = statusCoordinator;
        this.idRol = idRol;
    }

    public String getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(String numberStaff) {
        this.numberStaff = numberStaff;
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

    public boolean getStatusCoordinator() {
        return statusCoordinator;
    }

    public void setStatusCoordinator(boolean statusCoordinator) {
        this.statusCoordinator = statusCoordinator;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}
