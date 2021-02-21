package eg.gov.iti.contract.ui.models;


import javafx.scene.image.Image;

public class FriendModel {
    private String name;
    private String phoneNumber;
    private Image image;
    private String imageEncoded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
    }

    @Override
    public String toString() {
        return "FriendModel{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", image=" + image +
                ", imageEncoded='" + imageEncoded + '\'' +
                '}';
    }
}
