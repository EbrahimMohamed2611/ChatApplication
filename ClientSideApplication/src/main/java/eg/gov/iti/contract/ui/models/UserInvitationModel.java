package eg.gov.iti.contract.ui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class UserInvitationModel {
    private final StringProperty senderPhoneNumber = new SimpleStringProperty();
    private final StringProperty receiverPhoneNumber = new SimpleStringProperty();
    private final StringProperty senderName = new SimpleStringProperty();
    private final StringProperty senderImageEncoded = new SimpleStringProperty();
    private Image senderImage;
    private Circle senderPicCircle = new Circle();

    public UserInvitationModel() {
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber.get();
    }

    public StringProperty senderPhoneNumberProperty() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber.set(senderPhoneNumber);
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber.get();
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber.set(receiverPhoneNumber);
    }

    public StringProperty receiverPhoneNumberProperty() {
        return receiverPhoneNumber;
    }

    public String getSenderName() {
        return senderName.get();
    }

    public StringProperty senderNameProperty() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName.set(senderName);
    }

    public String getSenderImageEncoded() {
        return senderImageEncoded.get();
    }

    public StringProperty senderImageEncodedProperty() {
        return senderImageEncoded;
    }

    public void setSenderImageEncoded(String senderImageEncoded) {
        this.senderImageEncoded.set(senderImageEncoded);
    }

    public Image getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(Image senderImage) {
        this.senderImage = senderImage;
        this.senderPicCircle.setFill(new ImagePattern(senderImage));
    }

    public Circle getSenderPicCircle() {
        return senderPicCircle;
    }

    public void setSenderPicCircle(Circle senderPicCircle) {
        this.senderPicCircle = senderPicCircle;

    }

    @Override
    public String toString() {
        return "UserInvitationModel{" +
                "senderPhoneNumber=" + senderPhoneNumber +
                ", receiverPhoneNumber=" + receiverPhoneNumber +
                ", senderName=" + senderName +
                ", senderImageEncoded=" + senderImageEncoded +
                ", senderImage=" + senderImage +
                ", senderPicCircle=" + senderPicCircle +
                '}';
    }
}
