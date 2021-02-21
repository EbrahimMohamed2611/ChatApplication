package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InvitationServiceInterface extends Remote {
    boolean sendInvitation(UserInvitationDto invitationDto) throws RemoteException;

    boolean acceptInvitation(UserInvitationDto invitationDto) throws RemoteException;

    boolean rejectInvitation(UserInvitationDto invitationDto) throws RemoteException;
}
