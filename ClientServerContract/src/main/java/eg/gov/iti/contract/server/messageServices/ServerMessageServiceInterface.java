package eg.gov.iti.contract.server.messageServices;


import eg.gov.iti.contract.client.ChatClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageServiceInterface extends Remote {

     void sendToMyFriend(String userMessageDto) throws RemoteException;
     void register(ChatClient clientRef)throws RemoteException;

}

