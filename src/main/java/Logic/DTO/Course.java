package Logic.DTO;


import java.sql.Date;


public class Course {
    private String nrc;
    private String courseName;
    private String career;
    private String numberStaff;

    
    public Course() {
    }

    public Course(String nrc, String courseName, String career, String numberStaff) {
        this.nrc = nrc;
        this.courseName = courseName;
        this.career = career;
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

    public String getNumberStaff() {
        return numberStaff;
    }

    public void setNumberStaff(String numberStaff) {
        this.numberStaff = numberStaff;
    }
     
}
