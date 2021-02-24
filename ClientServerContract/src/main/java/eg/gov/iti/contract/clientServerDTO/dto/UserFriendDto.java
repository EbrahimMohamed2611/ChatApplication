package eg.gov.iti.contract.clientServerDTO.dto;

import eg.gov.iti.contract.clientServerDTO.enums.Status;

import java.io.Serializable;

public class UserFriendDto implements Serializable {
    private String friendPhoneNumber;
    private String name;
    private String imageEncoded;
    private Status friendStatus;

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
    }

    public Status getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(Status friendStatus) {
        this.friendStatus = friendStatus;
    }

    @Override
    public String toString() {
        return "UserFriendDto{" +
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", friendStatus=" + friendStatus.toString() +
                '}';
    }
}
