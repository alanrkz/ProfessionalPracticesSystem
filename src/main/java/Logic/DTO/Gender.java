/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.DTO;

/**
 *
 * @author ELLIN JV
 */
public class Gender {
    private String genderId;
    private String genderName;

    public Gender() {
    }

    public Gender(String genderId, String genderName) {
        this.genderId = genderId;
        this.genderName = genderName;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    @Override
    public String toString() {
        return genderName;
    }

    
}
