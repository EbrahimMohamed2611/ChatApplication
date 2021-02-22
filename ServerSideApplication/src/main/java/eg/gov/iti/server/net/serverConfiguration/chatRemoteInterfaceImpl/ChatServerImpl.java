package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.server.db.dao.FriendDao;
import eg.gov.iti.server.db.dao.InvitationDao;
import eg.gov.iti.server.db.dao.daoImpl.FriendDaoImpl;
import eg.gov.iti.server.db.dao.daoImpl.InvitationDaoImpl;
import eg.gov.iti.server.db.entities.Friendship;
import eg.gov.iti.server.db.entities.Invitation;
import eg.gov.iti.server.db.helpers.adapters.UserFriendAdapter;
import eg.gov.iti.server.db.helpers.adapters.UserInvitationAdapter;
import eg.gov.iti.server.net.callbackConfiguration.OnlineClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface {
    private InvitationDao invitationDao;
    private FriendDao friendDao;
    private OnlineClients onlineClients = OnlineClients.getInstance();

    public ChatServerImpl() throws RemoteException {

    }


    @Override
    public void register(ChatClient clientRef) throws RemoteException {
        var test = onlineClients.getOnlineClients();
               test.put(clientRef.getPhoneNumber(), clientRef);
        System.out.println("Client " + clientRef.getPhoneNumber() + " added");

        invitationDao = InvitationDaoImpl.getInstance();
        if (invitationDao.hasInvitation(clientRef.getPhoneNumber())) {
            List<Invitation> invitations = invitationDao.retrieveInvitations(clientRef.getPhoneNumber());
            for (Invitation invitation : invitations) {
                clientRef.receiveInvitation(UserInvitationAdapter.getInvitationDtoFromInvitation(invitation));
            }
        }

        friendDao = FriendDaoImpl.getInstance();
        if(friendDao.hasFriendship(clientRef.getPhoneNumber())) {
            List<Friendship> friendships = friendDao.retrieveFriendsOf(clientRef.getPhoneNumber());
            for (Friendship friendship : friendships) {
                UserFriendDto userFriendDto = new UserFriendDto();
                userFriendDto.setFriendPhoneNumber(friendship.getFriendPhoneNumber());
                clientRef.addFriend(userFriendDto);
            }
        }
        // Receive Announcement To Client
        clientRef.receiveAnnouncement("Welcome You are Online");
    }

    @Override
    public void unRegister(ChatClient clientRef) throws RemoteException {
        onlineClients.getOnlineClients().remove(clientRef.getPhoneNumber());
        System.out.println("Client removed");
        clientRef.receiveAnnouncement("You are Offline");
    }

    @Override
    public void changeStatus(String username, String status) throws RemoteException {

    }

    //    public void tellOthers(ChatClient chatClient ,UserMessageDto userMessageDto)throws RemoteException
//    {
//        System.out.println("Message received: "+userMessageDto);
//        for(ChatClient clientRef: clientsVector)
//        {
////            clientRef.receive(userMessageDto);
//
//        }
//    }
//    public void tellOthers(String message) throws RemoteException {
//        System.out.println("Message received: " + message);
//        for (ChatClient clientRef : onlineClients.getOnlineClients().values()) {
//            clientRef.receiveMessage(message);
//        }
//    }

}