package eg.gov.iti.contract.client;

import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
// todo
public interface ClientMessageService extends Remote {

     void receiveFromMyFriend(UserMessageDto userMessageDto) throws RemoteException;
     void receiveFile(byte[] fileContent, String fileName) throws RemoteException;
}
