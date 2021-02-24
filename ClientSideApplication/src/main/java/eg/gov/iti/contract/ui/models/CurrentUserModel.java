package eg.gov.iti.contract.ui.models;

import eg.gov.iti.contract.clientServerDTO.enums.Status;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
//todo sql to util Date
import java.time.LocalDate;

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
    private Image profileImage;
    private Circle profilePic=new Circle();
    private StringProperty statusProperty = new SimpleStringProperty();
    private Status status;
    private Circle statusCircle = new Circle();
    private ObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<>();
    private ObservableList<FriendModel> friends = FXCollections.observableArrayList();
    private ObservableList<UserInvitationModel> invitations = FXCollections.observableArrayList();

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
        this.profilePic.setFill(new ImagePattern(profileImage));
    }

    public Circle getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Circle profilePic) {
        this.profilePic = profilePic;
    }


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


    public String getStatusProperty() {
        return statusProperty.get();
    }

    public StringProperty statusPropertyProperty() {
        return statusProperty;
    }

    public void setStatusProperty(String statusProperty) {
        this.statusProperty.set(statusProperty);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.statusCircle.setFill(status.getStatus());
    }

    public Circle getStatusCircle() {
        return statusCircle;
    }

    public void setStatusCircle(Circle statusCircle) {
        this.statusCircle = statusCircle;
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
        return "CurrentUserModel{" +
                "userId=" + userId +
                ", phoneNumber=" + phoneNumber +
                ", password=" + password +
                ", fullName=" + fullName +
                ", email=" + email +
                ", country=" + country +
                ", bio=" + bio +
                ", imageEncoded=" + imageEncoded +
                ", status=" + statusProperty +
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
