package eg.gov.iti.contract.ui.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactModel {
    //StringProperty userPhoneNumber = new SimpleStringProperty();
    StringProperty contactPhoneNumber = new SimpleStringProperty();
    StringProperty name = new SimpleStringProperty();
    StringProperty bio = new SimpleStringProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty image = new SimpleStringProperty();


    public ContactModel(String contactPhoneNumber,String name,String bio,String email){
        this.contactPhoneNumber.setValue(contactPhoneNumber);
        this.name.setValue(name);
        this.bio.setValue(bio);
        this.email.setValue(email);

    }

    public ContactModel() {
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber.get();
    }

    public StringProperty contactPhoneNumberProperty() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber.set(contactPhoneNumber);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }


}