package eg.gov.iti.contract.clientServerDTO.dto;

import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;

import java.io.Serializable;
import java.sql.Date;

public class UserRegDto implements Serializable {

    private String phoneNumber;
    private String fullName;
    private String password;
    private String email;
    private Date dateOfBirth;
    private Gender userGender;
    private Status status;

    public UserRegDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getUserGender() {
        return userGender;
    }

    public void setUserGender(Gender userGender) {
        this.userGender = userGender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
