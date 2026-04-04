package Logic.DTO;


import java.util.Date;


public class Coordinator extends User{
    private String numberStaff;
    private Date registrionDate;
    private Date terminationDate;
    private String serviceTime;
    private int idUser;

    
    public Coordinator() {
    }

    public Coordinator(String numberStaff, Date registrionDate, Date terminationDate, String serviceTime, int idUser) {
        this.numberStaff = numberStaff;
        this.registrionDate = registrionDate;
        this.terminationDate = terminationDate;
        this.serviceTime = serviceTime;
        this.idUser = idUser;
    }
    

    public String getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(String numberStaff) {
        this.numberStaff = numberStaff;
    }

    public Date getRegistrionDate() {
        return registrionDate;
    }

    public void setRegistrionDate(Date registrionDate) {
        this.registrionDate = registrionDate;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
