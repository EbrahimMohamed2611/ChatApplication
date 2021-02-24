package eg.gov.iti.contract.ui.models;


import eg.gov.iti.contract.clientServerDTO.enums.Status;
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
    private StringProperty statusProperty = new SimpleStringProperty();
    private Status status;
    private Circle statusCircle = new Circle();

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

    public ObservableList<HBox> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "FriendModel{" +
                "name=" + name +
                ", phoneNumber=" + phoneNumber +
                ", statusProperty=" + statusProperty +
                ", messages=" + messages +
                '}';
    }
}
