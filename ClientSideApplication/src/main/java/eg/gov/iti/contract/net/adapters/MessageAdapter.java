package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.models.UserMessageModel;

public class MessageAdapter {

    private static final UserMessageDto userMessageDto = new UserMessageDto();

    private static final UserMessageModel userMessageModel = new UserMessageModel();

    public static UserMessageDto getMessageDtoFromMessageModel(UserMessageModel userMessageModel) {

        userMessageDto.setName(userMessageModel.getSenderName());
        userMessageDto.setMessageBody(userMessageModel.getMessageBody());
        userMessageDto.setImageEncoded(userMessageModel.getImageEncoded());
        userMessageDto.setMessageDate(userMessageModel.getMessageDate());
        userMessageDto.setReceiverPhoneNumber(userMessageModel.getReceiverPhoneNumber());
        userMessageDto.setSenderPHoneNumber(userMessageModel.getSenderPHoneNumber());

        return userMessageDto;
    }

    public static UserMessageModel getMessageModelFromMessageDto(UserMessageDto userMessageDto) {
        userMessageModel.setSenderName(userMessageDto.getName());
        userMessageModel.setMessageBody(userMessageDto.getMessageBody());
//        userMessageModel.setImage(ImageConverter.getDecodedImage(userMessageDto.getImageEncoded()));
        userMessageModel.setImageEncoded(userMessageDto.getImageEncoded());
        userMessageModel.setMessageDate(userMessageDto.getMessageDate());
        userMessageModel.setSenderPHoneNumber(userMessageDto.getSenderPHoneNumber());
        userMessageModel.setReceiverPhoneNumber(userMessageDto.getReceiverPhoneNumber());

        return userMessageModel;
    }


}
