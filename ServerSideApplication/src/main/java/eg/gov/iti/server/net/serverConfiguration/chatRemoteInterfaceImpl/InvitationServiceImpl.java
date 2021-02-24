package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.InvitationServiceInterface;
import eg.gov.iti.server.db.dao.InvitationDao;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.InvitationDaoImpl;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.Invitation;
import eg.gov.iti.server.db.helpers.adapters.UserInvitationAdapter;
import eg.gov.iti.server.net.callbackConfiguration.OnlineClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

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
        // Validate existence of receiver
        if (userDao.isExisted(invitation.getReceiverPhoneNumber())) {
            // test if it already exists
            if (invitationDao.isExisted(invitation)) {
                return false;
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
            invitationDao.save(invitation);
            return true;
        }
        return false;
    }
}
