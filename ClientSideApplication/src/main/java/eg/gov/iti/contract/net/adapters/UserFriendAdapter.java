package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.ui.models.FriendModel;
import eg.gov.iti.contract.ui.models.UserInvitationModel;

public class UserFriendAdapter {
    public static UserFriendDto getFriendDtoFromModel(FriendModel friendModel) {
        UserFriendDto userFriendDto = new UserFriendDto();
        userFriendDto.setFriendPhoneNumber(friendModel.getPhoneNumber());

        return userFriendDto;
    }

    public static FriendModel getFriendModelFromDto(UserFriendDto userFriendDto) {
        FriendModel friendModel = new FriendModel();
        friendModel.setPhoneNumber(userFriendDto.getFriendPhoneNumber());

        return friendModel;
    }
}
