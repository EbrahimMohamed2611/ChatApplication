package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.models.FriendModel;

public class UserFriendAdapter {
    public static UserFriendDto getFriendDtoFromModel(FriendModel friendModel) {
        UserFriendDto userFriendDto = new UserFriendDto();
        userFriendDto.setFriendPhoneNumber(friendModel.getPhoneNumber());

        return userFriendDto;
    }

    public static FriendModel getFriendModelFromDto(UserFriendDto userFriendDto) {
        FriendModel friendModel = new FriendModel();
        friendModel.setPhoneNumber(userFriendDto.getFriendPhoneNumber());
        friendModel.setName((userFriendDto.getName()));
        friendModel.setImageEncoded(userFriendDto.getImageEncoded());
        if (userFriendDto.getImageEncoded() != null)
            friendModel.setImage(ImageConverter.getDecodedImage(userFriendDto.getImageEncoded()));

        return friendModel;
    }
}
