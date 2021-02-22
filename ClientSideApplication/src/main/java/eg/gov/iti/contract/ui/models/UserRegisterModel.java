package eg.gov.iti.contract.ui.models;

import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class UserRegisterModel {

    private final IntegerProperty userId = new SimpleIntegerProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty fullName = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final StringProperty bio = new SimpleStringProperty();
    private final StringProperty imageEncoded = new SimpleStringProperty();
    private StringProperty userGender;
    private StringProperty status;
    private StringProperty dateOfBirth = new SimpleStringProperty();

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUserGender() {
        return userGender.get();
    }

    public StringProperty userGenderProperty() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender.set(userGender);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    @Override
    public String toString() {
        return "UserRegisterModel{" +
                "userId=" + userId +
                ", phoneNumber=" + phoneNumber +
                ", password=" + password +
                ", fullName=" + fullName +
                ", email=" + email +
                ", country=" + country +
                ", bio=" + bio +
                ", imageEncoded=" + imageEncoded +
                ", userGender=" + userGender +
                ", status=" + status +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public void clear() {
        phoneNumber.set("");
        password.set("");
        fullName.set("");
        email.set("");
        dateOfBirth.set("");


    }
}