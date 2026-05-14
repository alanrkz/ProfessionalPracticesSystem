package Logic.DTO;


public class StudentFiles {
    private int idDocument;
    private String documentName;
    private String documentURL;
    private String documentType;
    private String enrollment;

    
    public StudentFiles() {
    }

    public StudentFiles(int idDocument, String documentName, String documentURL, String documentType, String enrollment) {
        this.idDocument = idDocument;
        this.documentName = documentName;
        this.documentURL = documentURL;
        this.documentType = documentType;
        this.enrollment = enrollment;
    }
    
    
    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentURL() {
        return documentURL;
    }

    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
    
}
