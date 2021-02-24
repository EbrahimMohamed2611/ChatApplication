package eg.gov.iti.contract.ui.models;
import javafx.scene.image.ImageView;
import java.util.Date;

public class UserMessageModel {

    private String name;
    private String messageBody;
    private String imageEncoded;
    private Date messageDate;
    private ImageView imageView;
    private String senderPHoneNumber;
    private String receiverPhoneNumber;

    public UserMessageModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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
        return "UserMessageModel{" +
                "name='" + name + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", imageEncoded='" + imageEncoded + '\'' +
                ", messageDate=" + messageDate +
                ", imageView=" + imageView +
                ", senderPHoneNumber='" + senderPHoneNumber + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
