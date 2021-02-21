package eg.gov.iti.contract.clientServerDTO.dto;

import java.io.Serializable;

public class FriendshipDto implements Serializable {
    private String userPhoneNumber;
    private String friendPhoneNumber;

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }

    @Override
    public String toString() {
        return "FriendshipDto{" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userFriendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}
