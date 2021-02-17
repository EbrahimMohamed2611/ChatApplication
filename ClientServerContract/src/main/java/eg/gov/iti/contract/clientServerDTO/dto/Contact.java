package eg.gov.iti.contract.clientServerDTO.dto;

import java.io.Serializable;

public class Contact implements Serializable {

    String userPhoneNumber;
    String contactPhoneNumber;
    String name;
    String bio;
    String email;


    public Contact (String userPhoneNumber, String contactPhoneNumber, String name, String bio, String email) {
        this.userPhoneNumber = userPhoneNumber;
        this.contactPhoneNumber = contactPhoneNumber;
        this.name = name;
        this.bio = bio;
        this.email = email;

    }

    public Contact(String userPhoneNumber,String contactPhoneNumber){
        this.contactPhoneNumber = contactPhoneNumber;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
