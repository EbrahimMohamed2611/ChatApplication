package eg.gov.iti.contract.clientServerDTO.dto;

import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Date;

public class UserMessageDto implements Serializable {

    private String name;
    transient private  ImageView userImage;
    private String messageBody;
    private  String imageEncoded;
    private Date messageDate;
    private String senderPHoneNumber;
    private String receiverPhoneNumber;

    public UserMessageDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getUserImage() {
        return userImage;
    }

    public void setUserImage(ImageView userImage) {
        this.userImage = userImage;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
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

    @Override
    public String toString() {
        return "UserMessageDto{" +
                "name='" + name + '\'' +
                ", userImage=" + userImage +
                ", messageBody='" + messageBody + '\'' +
                ", imageEncoded='" + imageEncoded + '\'' +
                ", messageDate=" + messageDate +
                ", senderPHoneNumber='" + senderPHoneNumber + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
