package Logic.DTO;


public class StudentFiles {
    private int idStudentRecord;
    private String internSchedule;
    private String activitiesPlan;
    private String assigmentLetter;
    private String enrollment;

    
    public StudentFiles() {
    }

    public StudentFiles(int idStudentRecord, String internSchedule, String activitiesPlan, String assigmentLetter, String enrollment) {
        this.idStudentRecord = idStudentRecord;
        this.internSchedule = internSchedule;
        this.activitiesPlan = activitiesPlan;
        this.assigmentLetter = assigmentLetter;
        this.enrollment = enrollment;
    }

    
    public int getIdStudentRecord() {
        return idStudentRecord;
    }

    public void setIdStudentRecord(int idStudentRecord) {
        this.idStudentRecord = idStudentRecord;
    }

    public String getInternSchedule() {
        return internSchedule;
    }

    public void setInternSchedule(String internSchedule) {
        this.internSchedule = internSchedule;
    }

    public String getActivitiesPlan() {
        return activitiesPlan;
    }

    public void setActivitiesPlan(String activitiesPlan) {
        this.activitiesPlan = activitiesPlan;
    }

    public String getAssigmentLetter() {
        return assigmentLetter;
    }

    public void setAssigmentLetter(String assigmentLetter) {
        this.assigmentLetter = assigmentLetter;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
    
}
