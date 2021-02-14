package eg.gov.iti.server.db.entities;

import javafx.scene.image.Image;

public class Admin {
    private int AdminId;
    private String adminUserName;
    private String adminPhoneNumber;
    private String adminPassword;
    private Image adminImage;

    public Admin() {
    }

    public Admin(String adminUserName, String adminPhoneNumber, String adminPassword) {
        this.adminUserName = adminUserName;
        this.adminPhoneNumber = adminPhoneNumber;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Image getAdminImage() {
        return adminImage;
    }

    public void setAdminImage(Image adminImage) {
        this.adminImage = adminImage;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "AdminId=" + AdminId +
                ", adminUserName='" + adminUserName + '\'' +
                ", adminPhoneNumber='" + adminPhoneNumber + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminImage=" + adminImage +
                '}';
    }
}
