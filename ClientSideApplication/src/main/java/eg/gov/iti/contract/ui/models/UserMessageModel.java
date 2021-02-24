package eg.gov.iti.contract.ui.models;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Date;

public class UserMessageModel {
    private StringProperty senderName = new SimpleStringProperty();
    private String messageBody;
    private StringProperty imageEncoded = new SimpleStringProperty();
    private Image image;
    private Circle senderImage = new Circle();
    private Date messageDate;
    private String senderPHoneNumber;
    private String receiverPhoneNumber;

    public UserMessageModel() {
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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getSenderPHoneNumber() {
        return senderPHoneNumber;
    }

    public void setSenderPHoneNumber(String senderPHoneNumber) {
        this.senderPHoneNumber = senderPHoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.senderImage.setFill(new ImagePattern(image));
    }

    public Circle getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(Circle senderImage) {
        this.senderImage = senderImage;
    }

    @Override
    public String toString() {
        return "UserMessageModel{" +
                "senderName=" + senderName +
                ", messageBody='" + messageBody + '\'' +
                ", messageDate=" + messageDate +
                ", senderPHoneNumber='" + senderPHoneNumber + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
