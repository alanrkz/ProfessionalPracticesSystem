package Logic.DTO;


public class Notification {
    private int idNotification;
    private String recipient;
    private String subject;
    private String messageBody;
    private String numberStaff;

    
    public Notification() {
    }

    public Notification(int idNotification, String recipient, String subject, String messageBody, String numberStaff) {
        this.idNotification = idNotification;
        this.recipient = recipient;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
