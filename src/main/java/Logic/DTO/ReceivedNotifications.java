package Logic.DTO;


public class ReceivedNotifications {
    private int idNotification;
    private String enrollment;

    
    public ReceivedNotifications() {
    }

    public ReceivedNotifications(int idNotification, String enrollment) {
        this.idNotification = idNotification;
        this.enrollment = enrollment;
    }

    
    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
    
    
}
