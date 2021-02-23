package eg.gov.iti.contract.clientServerDTO.dto;

import java.io.Serializable;

public class UserFriendDto implements Serializable {
    private String friendPhoneNumber;
    private String name;
    private String imageEncoded;

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

    @Override
    public String toString() {
        return "UserFriendDto{" +
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}
