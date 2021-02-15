package eg.gov.iti.contract.ui.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Cached-data")
public class CachedData {
    private String phoneNumber;
    private String password;

    public CachedData() {
    }

    public CachedData(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
