package eg.gov.iti.server.ui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminModel {
    private  StringProperty adminId = new SimpleStringProperty();
    private  StringProperty userName = new SimpleStringProperty();
    private  StringProperty phoneNumber = new SimpleStringProperty();

    public AdminModel() {
    }

    public String getAdminId() {
        return adminId.get();
    }

    public StringProperty adminIdProperty() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId.set(adminId);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
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

    @Override
    public String toString() {
        return "AdminModel{" +
                "adminId=" + adminId +
                ", userName=" + userName +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
