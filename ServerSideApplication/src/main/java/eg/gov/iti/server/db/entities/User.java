package eg.gov.iti.server.db.entities;

import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int userId;
    private String phoneNumber;
    private String userName;
    private String password;
    private String encryptionPassword;
    private String email;
    private String country;
    private Date dateOfBirth;
    private Gender userGender;
    private String bio;
    private transient ImageView imageView;
    private String imageEncoded;
    private Status status;

    public User() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptionPassword() {
        return encryptionPassword;
    }

    public void setEncryptionPassword(String encryptionPassword) {
        this.encryptionPassword = encryptionPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    public User(int userId, String phoneNumber, String userName, String password, String encryptionPassword, String email, String country, Date dateOfBirth, Gender userGender, String bio, ImageView imageView, String imageEncoded, Status status) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.encryptionPassword = encryptionPassword;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.userGender = userGender;
        this.bio = bio;
        this.imageView = imageView;
        this.imageEncoded = imageEncoded;
        this.status = status;
    }

    public User(String phoneNumber, String userName, String password, String email, String country, Date dateOfBirth, Gender userGender, String bio, Status status) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.userGender = userGender;
        this.bio = bio;
        this.status = status;
    }

    public User(String phoneNumber, String userName, String password, String email, String country) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.country = country;

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", encryptionPassword='" + encryptionPassword + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", userGender=" + userGender +
                ", bio='" + bio + '\'' +
                ", imageView=" + imageView +
                ", imageEncoded='" + imageEncoded + '\'' +
                ", status=" + status +
                '}';
    }
}
