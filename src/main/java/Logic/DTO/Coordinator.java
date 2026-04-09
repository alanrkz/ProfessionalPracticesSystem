package Logic.DTO;


import java.sql.Date;


public class Coordinator extends User{
    private String numberStaff;
    private Date registrationDate;
    private Date terminationDate;
    private String serviceTime; //Calcular a partir de las fechas de registro
    private int idUser;

    
    public Coordinator() {
    }

    public Coordinator(String numberStaff, Date registrationDate, Date terminationDate, String serviceTime, int idUser) {
        this.numberStaff = numberStaff;
        this.registrationDate = registrationDate;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
