package eg.gov.iti.contract.server.messageServices;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageServiceInterface extends Remote {

     void sendToMyFriend(ChatClient senderClient, UserMessageDto userMessageDto) throws RemoteException;
     void register(ChatClient clientRef)throws RemoteException;

}

