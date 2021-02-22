package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.InvitationServiceInterface;
import eg.gov.iti.server.db.dao.InvitationDao;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.FriendDaoImpl;
import eg.gov.iti.server.db.dao.daoImpl.InvitationDaoImpl;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.Friendship;
import eg.gov.iti.server.db.entities.Invitation;
import eg.gov.iti.server.db.helpers.adapters.UserInvitationAdapter;
import eg.gov.iti.server.net.callbackConfiguration.OnlineClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class InvitationServiceImpl extends UnicastRemoteObject implements InvitationServiceInterface {
    private static InvitationServiceImpl serviceInstance;
    private final InvitationDao invitationDao;
    private final UserDao userDao;

    public InvitationServiceImpl() throws RemoteException, SQLException {
        invitationDao = InvitationDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();
    }

    public static InvitationServiceImpl getInstance() {
        if (serviceInstance == null) {
            try {
                serviceInstance = new InvitationServiceImpl();
            } catch (RemoteException | SQLException e) {
                e.printStackTrace();
            }
        }
        return serviceInstance;
    }

    @Override
    public boolean sendInvitation(UserInvitationDto invitationDto) {
        Invitation invitation = UserInvitationAdapter.getInvitationFromInvitationDto(invitationDto);
        String sender = invitation.getSenderPhoneNumber();
        String receiver = invitation.getReceiverPhoneNumber();
        // Validate existence of receiver
        if (userDao.isExisted(receiver)) {
            // test if it already exists
            if (invitationDao.isExisted(invitation)) {
                return false;
            }

            List<Invitation> senderInvitations = invitationDao.retrieveInvitations(sender);
            System.out.println(senderInvitations);
            if (senderInvitations != null) {
                // loop on sender invitations if exists
                for (Invitation invitation1 : senderInvitations) {
                    // if sender has any previous invitation from receiver
                    System.out.println(invitation1);
                    if (invitation1.getSenderPhoneNumber().equals(receiver)) {
                        UserInvitationDto userInvitationDto = new UserInvitationDto();
                        userInvitationDto.setSenderPhoneNumber(invitationDto.getReceiverPhoneNumber());
                        userInvitationDto.setReceiverPhoneNumber(invitationDto.getSenderPhoneNumber());
                        if (acceptInvitation(userInvitationDto)) {
                            System.out.println("Invitation exists and accepted");
                            return true;
                        }
                    }
                }
            }

            System.out.println(invitation.getSenderPhoneNumber() + " Invited " + invitation.getReceiverPhoneNumber());

            // test if this client is online to send it
            ChatClient client = OnlineClients.getInstance().getOnlineClients().get(invitation.getReceiverPhoneNumber());
            try {
                if (client != null) {
                    client.receiveInvitation(invitationDto);
                    System.out.println(client + invitationDto.getReceiverPhoneNumber());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            invitationDao.saveInvitation(invitation);
            return true;
        }
        return false;
    }

    @Override
    public boolean acceptInvitation(UserInvitationDto invitationDto) {
        // Create invitation instance
        Invitation invitation = UserInvitationAdapter.getInvitationFromInvitationDto(invitationDto);

        // test if this invitation exists
        if (invitationDao.isExisted(invitation)) {
            // Create new friendship
            Friendship friendship = new Friendship();
            friendship.setUserPhoneNumber(invitation.getSenderPhoneNumber());
            friendship.setFriendPhoneNumber(invitation.getReceiverPhoneNumber());

            // save friendship on database friend table
            if (FriendDaoImpl.getInstance().saveFriendship(friendship)) {
                System.out.println(invitation + " Accepted");

                // notify invitation sender if online
                ChatClient senderClient = OnlineClients.getInstance().getOnlineClients().get(invitation.getSenderPhoneNumber());
                ChatClient receiverClient = OnlineClients.getInstance().getOnlineClients().get(invitation.getReceiverPhoneNumber());
                if (senderClient != null) {
                    UserFriendDto userFriendDto = new UserFriendDto();
                    userFriendDto.setFriendPhoneNumber(invitation.getReceiverPhoneNumber());
                    try {
                        senderClient.addFriend(userFriendDto);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                // notify invitation receiver
                if (receiverClient != null) {
                    UserFriendDto userFriendDto = new UserFriendDto();
                    userFriendDto.setFriendPhoneNumber(invitation.getSenderPhoneNumber());
                    try {
                        receiverClient.addFriend(userFriendDto);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                // delete invitation from database
                return invitationDao.deleteInvitation(invitation);
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean rejectInvitation(UserInvitationDto invitationDto) {
        Invitation invitation = UserInvitationAdapter.getInvitationFromInvitationDto(invitationDto);

        if (invitationDao.isExisted(invitation)) {
            System.out.println(invitation + " rejected");

            return invitationDao.deleteInvitation(invitation);
        }
        return false;
    }
}
