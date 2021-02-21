package eg.gov.iti.contract.ui.models;

import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//todo sql to util Date
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrentUserModel {
    private static CurrentUserModel instance;
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
    private ObjectProperty<LocalDate> dateOfBirth;

    ObservableList<FriendModel> friends = FXCollections.observableArrayList();
    ObservableList<UserInvitationModel> invitations = FXCollections.observableArrayList();

    private CurrentUserModel() {
    }

    public static CurrentUserModel getInstance() {
        if (instance == null) {
            instance = new CurrentUserModel();
        }
        return instance;
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getBio() {
        return bio.get();
    }

    public StringProperty bioProperty() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio.set(bio);
    }

    public String getImageEncoded() {
        return imageEncoded.get();
    }

    public StringProperty imageEncodedProperty() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded.set(imageEncoded);
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

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
        country.set("");
//        dateOfBirth.before(null);


    }

    public ObservableList<FriendModel> getFriends() {
        return friends;
    }

    public ObservableList<UserInvitationModel> getInvitations() {
        return invitations;
    }
}
