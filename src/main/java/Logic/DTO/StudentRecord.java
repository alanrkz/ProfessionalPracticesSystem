package Logic.DTO;


public class StudentRecord {
    private int idStudentRecord;
    private String internSchedule;
    private String workPlan;
    private String assigmentLetter;
    private String enrollment;

    
    public StudentRecord() {
    }

    public StudentRecord(int idStudentRecord, String internSchedule, String workPlan, String assigmentLetter, String enrollment) {
        this.idStudentRecord = idStudentRecord;
        this.internSchedule = internSchedule;
        this.workPlan = workPlan;
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

    public String getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
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
