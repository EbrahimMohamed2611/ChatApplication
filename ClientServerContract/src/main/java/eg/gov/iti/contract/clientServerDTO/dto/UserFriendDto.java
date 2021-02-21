package eg.gov.iti.contract.clientServerDTO.dto;

import java.io.Serializable;

public class UserFriendDto implements Serializable {
    private String friendPhoneNumber;

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }

    @Override
    public String toString() {
        return "UserFriendDto{" +
                "friendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}
