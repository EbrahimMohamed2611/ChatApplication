package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.server.db.entities.MessageEntity;

import java.sql.Date;

public class MessageAdapter {

    public static MessageEntity getMessageEntityFromMessageDto(UserMessageDto userMessageDto){

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSender(userMessageDto.getSenderPHoneNumber());
        messageEntity.setReceiver(userMessageDto.getReceiverPhoneNumber());
        messageEntity.setContent(userMessageDto.getMessageBody());
        messageEntity.setMessageDate(new Date(userMessageDto.getMessageDate().getTime()));
        return messageEntity;
    }

    public static UserMessageDto getMessageDtoFromMessageEntity(MessageEntity  messageEntity){

        UserMessageDto userMessageDto = new UserMessageDto();
        userMessageDto.setSenderPHoneNumber(messageEntity.getSender());
        userMessageDto.setReceiverPhoneNumber(messageEntity.getReceiver());
        userMessageDto.setMessageBody(messageEntity.getContent());
        userMessageDto.setMessageDate(messageEntity.getMessageDate());
        return userMessageDto;
    }
}
