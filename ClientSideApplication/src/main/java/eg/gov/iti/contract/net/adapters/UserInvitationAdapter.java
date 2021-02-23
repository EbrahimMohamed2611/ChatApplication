package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.models.UserInvitationModel;

public class UserInvitationAdapter {

    public static UserInvitationDto getInvitationDtoFromModel(UserInvitationModel userInvitationModel) {
        UserInvitationDto userInvitationDto = new UserInvitationDto();
        userInvitationDto.setSenderPhoneNumber(userInvitationModel.getSenderPhoneNumber());
        userInvitationDto.setReceiverPhoneNumber(userInvitationModel.getReceiverPhoneNumber());
//        userInvitationDto.setSenderName(userInvitationModel.getSenderName());
//        userInvitationDto.setSenderImageEncoded(userInvitationModel.getSenderImageEncoded());

        return userInvitationDto;
    }

    public static UserInvitationModel getInvitationModelFromDto(UserInvitationDto userInvitationDto) {
        UserInvitationModel userInvitationModel = new UserInvitationModel();
        userInvitationModel.setSenderPhoneNumber(userInvitationDto.getSenderPhoneNumber());
        userInvitationModel.setReceiverPhoneNumber(userInvitationDto.getReceiverPhoneNumber());
        userInvitationModel.setSenderName(userInvitationDto.getSenderName());
        userInvitationModel.setSenderImageEncoded(userInvitationDto.getSenderImageEncoded());
        if (userInvitationDto.getSenderImageEncoded() != null)
            userInvitationModel.setSenderImage(ImageConverter.getDecodedImage(userInvitationDto.getSenderImageEncoded()));

        return userInvitationModel;
    }
}
