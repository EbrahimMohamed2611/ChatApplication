package eg.gov.iti.contract.server.messageServices;



import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageServiceInterface extends Remote {

     void sendToMyFriend(UserMessageDto userMessageDto) throws RemoteException;
     void sendFile(byte[] fileContent, String fileName, String receiver) throws RemoteException;

}

