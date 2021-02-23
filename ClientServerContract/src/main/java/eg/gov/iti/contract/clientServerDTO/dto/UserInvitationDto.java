package eg.gov.iti.contract.clientServerDTO.dto;

import java.io.Serializable;

public class UserInvitationDto implements Serializable {
    private String senderPhoneNumber;
    private String senderName;
    private String senderImageEncoded;
    private String receiverPhoneNumber;

    public UserInvitationDto() {
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderImageEncoded() {
        return senderImageEncoded;
    }

    public void setSenderImageEncoded(String senderImageEncoded) {
        this.senderImageEncoded = senderImageEncoded;
    }

    @Override
    public String toString() {
        return "UserInvitationDto{" +
                "senderPhoneNumber='" + senderPhoneNumber + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
