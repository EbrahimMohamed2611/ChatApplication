package eg.gov.iti.server.db.entities;


public class Invitation {
    private String senderPhoneNumber;
    private String receiverPhoneNumber;


    public Invitation() {
    }

    public Invitation(String senderPhoneNumber, String receiverPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
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

    @Override
    public String toString() {
        return "Invitation{" +
                "senderPhoneNumber='" + senderPhoneNumber + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
