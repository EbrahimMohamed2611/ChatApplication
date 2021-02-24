package eg.gov.iti.contract.client;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receiveAnnouncementFromServer(String announcementMessage) throws RemoteException;
    void receiveMessage(UserMessageDto userMessage) throws RemoteException;
    void receiveAnnouncement(String message) throws RemoteException;
    void addFriend(UserFriendDto userFriendShipDto) throws RemoteException;
    String getPhoneNumber() throws RemoteException;
    void receiveInvitation(UserInvitationDto userInvitationDto) throws RemoteException;
    void receiveFile(byte[] fileContent, String fileName) throws RemoteException;

    void notifyFriendUpdate(UserFriendDto userFriendDto) throws RemoteException;
}
