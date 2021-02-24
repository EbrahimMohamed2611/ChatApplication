package eg.gov.iti.contract.clientServerDTO.dto;

import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;

import java.io.Serializable;
import java.sql.Date;

public class UserDto implements Serializable {
    private int userId;
    private String phoneNumber;
    private String fullName;
    private String password;
    private String email;
    private String country;
    private Date dateOfBirth;
    private Gender userGender;
    private String bio;
    private String imageEncoded;
    private Status status;

    public UserDto() {
    }

    public UserDto(int userId, String phoneNumber, String fullName,
                   String password, String email, String country,
                   Date dateOfBirth, Gender userGender, String bio,
                   String imageEncoded, Status status) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.userGender = userGender;
        this.bio = bio;
        this.imageEncoded = imageEncoded;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", userGender=" + userGender +
                ", bio='" + bio + '\'' +
                ", imageEncoded='" + imageEncoded + '\'' +
                ", status=" + status.toString() +
                '}';
    }
}
