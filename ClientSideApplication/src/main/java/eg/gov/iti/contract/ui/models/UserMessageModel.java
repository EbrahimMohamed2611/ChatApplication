package eg.gov.iti.contract.ui.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

import java.util.Date;

public class UserMessageModel {

    private StringProperty name;
    private StringProperty messageBody;
    private StringProperty imageEncoded;
    private ObjectProperty<Date> messageDate;


    public UserMessageModel() {
    }

    public UserMessageModel(StringProperty name, StringProperty messageBody, StringProperty imageEncoded, ObjectProperty<Date> messageDate) {
        this.name = name;
        this.messageBody = messageBody;
        this.imageEncoded = imageEncoded;
        this.messageDate = messageDate;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMessageBody() {
        return messageBody.get();
    }

    public StringProperty messageBodyProperty() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody.set(messageBody);
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

    public Date getMessageDate() {
        return messageDate.get();
    }

    public ObjectProperty<Date> messageDateProperty() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate.set(messageDate);
    }

    @Override
    public String toString() {
        return "UserMessageModel{" +
                "name=" + name +
                ", messageBody=" + messageBody +
                ", imageEncoded=" + imageEncoded +
                ", messageDate=" + messageDate +
                '}';
    }
}
