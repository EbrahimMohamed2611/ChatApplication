package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.server.db.entities.Invitation;
import eg.gov.iti.server.db.entities.User;

public class UserInvitationAdapter {
    private static Invitation invitation;
    private static UserInvitationDto invitationDto;

    public static Invitation getInvitationFromInvitationDto(UserInvitationDto userInvitationDto) {
        invitation = new Invitation();
        invitation.setSenderPhoneNumber(userInvitationDto.getSenderPhoneNumber());
        invitation.setReceiverPhoneNumber(userInvitationDto.getReceiverPhoneNumber());

        return invitation;
    }

    public static UserInvitationDto getInvitationDtoFromInvitation(Invitation invitation, User sender) {
        invitationDto = new UserInvitationDto();
        invitationDto.setSenderPhoneNumber(invitation.getSenderPhoneNumber());
        invitationDto.setReceiverPhoneNumber(invitation.getReceiverPhoneNumber());
        invitationDto.setSenderName(sender.getUserName());
        invitationDto.setSenderImageEncoded(sender.getImageEncoded());

        return invitationDto;
    }
}
