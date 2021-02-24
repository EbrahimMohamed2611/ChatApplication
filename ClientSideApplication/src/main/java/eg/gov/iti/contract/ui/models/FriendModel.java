package eg.gov.iti.contract.ui.models;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FriendModel {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty phoneNumber = new SimpleStringProperty();
    private Image image;
    private StringProperty imageEncoded = new SimpleStringProperty();
    private Circle friendImage = new Circle();

    private ObservableList<HBox> messages = FXCollections.observableArrayList();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.friendImage.setFill(new ImagePattern(image));
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

    public Circle getFriendImage() {
        return friendImage;
    }

    public void setFriendImage(Circle friendImage) {
        this.friendImage = friendImage;
    }

    public void setMessages(ObservableList<HBox> messages) {
        this.messages = messages;
    }

    public ObservableList<HBox> getMessages() {
        return messages;
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
