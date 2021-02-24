package eg.gov.iti.contract.ui.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserInvitationModel {
    private final StringProperty senderPhoneNumber = new SimpleStringProperty();
    private final StringProperty receiverPhoneNumber = new SimpleStringProperty();

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

    @Override
    public String toString() {
        return "UserInvitationModel{" +
                "senderPhoneNumber=" + senderPhoneNumber +
                ", receiverPhoneNumber=" + receiverPhoneNumber +
                '}';
    }
}
