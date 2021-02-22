package eg.gov.iti.contract.server.chatRemoteInterfaces;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    void register(ChatClient chatClient)throws RemoteException;
    void unRegister(ChatClient chatClient)throws RemoteException;
    void changeStatus(String username, String status) throws RemoteException;
    void sendAnnouncementToAllOnlineUsers(String announcementMessage) throws RemoteException;

}
