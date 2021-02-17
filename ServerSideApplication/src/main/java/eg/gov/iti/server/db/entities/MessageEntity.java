package eg.gov.iti.server.db.entities;

import java.io.Serializable;
import java.sql.Date;

public class MessageEntity implements Serializable {

    private String sender;
    private String receiver;
    private String content;
    private Date messageDate;

    public MessageEntity() {
    }

    public MessageEntity(String sender, String receiver, String content, Date messageDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.messageDate = messageDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
