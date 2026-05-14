package Logic.DTO;


public class Notification {
    private int idNotification;
    private String receiver;
    private String subject;
    private String messageBody;
    private String numberStaff;

    
    public Notification() {
    }

    public Notification(int idNotification, String recipient, String subject, String messageBody, String numberStaff) {
        this.idNotification = idNotification;
        this.receiver = recipient;
        this.subject = subject;
        this.messageBody = messageBody;
        this.numberStaff = numberStaff;
    }

    
    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String recipient) {
        this.receiver = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(String numberStaff) {
        this.numberStaff = numberStaff;
    }
    
}
