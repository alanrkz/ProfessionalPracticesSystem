package Logic.DTO;


import java.sql.Date;


public class Course {
    private String nrc;
    private String courseName;
    private String career;
    private Date startDate;
    private Date endDate;
    private String numberStaff;

    
    public Course() {
    }

    public Course(String nrc, String courseName, String career, Date startDate, Date endDate, String numberStaff) {
        this.nrc = nrc;
        this.courseName = courseName;
        this.career = career;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberStaff = numberStaff;
    }

    
    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(String numberStaff) {
        this.numberStaff = numberStaff;
    }
     
}
