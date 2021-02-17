package eg.gov.iti.contract.client;

import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInvitationService extends Remote {
    void receiveInvitation(UserInvitationDto userInvitationDto) throws RemoteException;
}
