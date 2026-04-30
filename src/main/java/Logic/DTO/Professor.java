package Logic.DTO;


import java.sql.Date;


public class Professor extends User{
    private String numberStaff;
    private String shift;
    private Date registrationDate;
    private Date terminationDate;
    private String serviceTime;
    private boolean isCoordinator;
    private int idUser;

    
    public Professor() {
    }

    public Professor(String numberStaff, String shift, Date registrationDate, Date terminationDate, String serviceTime, boolean isCoordinator, int idUser) {
        this.numberStaff = numberStaff;
        this.shift = shift;
        this.registrationDate = registrationDate;
        this.terminationDate = terminationDate;
        this.serviceTime = serviceTime;
        this.isCoordinator = isCoordinator;
        this.idUser = idUser;
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

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean isIsCoordinator() {
        return isCoordinator;
    }

    public void setIsCoordinator(boolean isCoordinator) {
        this.isCoordinator = isCoordinator;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return numberStaff;
    }
     
}
