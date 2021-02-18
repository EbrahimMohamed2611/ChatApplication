package eg.gov.iti.contract.net;

import eg.gov.iti.contract.client.ClientInvitationService;
import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.ui.models.UserInvitationModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientInvitationServiceImpl extends UnicastRemoteObject implements ClientInvitationService {
    private static ClientInvitationServiceImpl invitationService;

    protected ClientInvitationServiceImpl() throws RemoteException {
    }

    public static ClientInvitationServiceImpl getInstance() {
        try {
            if (invitationService == null) {
                invitationService = new ClientInvitationServiceImpl();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return invitationService;
    }

    @Override
    public void receiveInvitation(UserInvitationDto userInvitationDto) throws RemoteException {
        UserInvitationModel userInvitationModel = UserInvitationAdapter.getInvitationModelFromDto(userInvitationDto);
        System.out.println(userInvitationModel.getSenderPhoneNumber() + "invited you!");
    }
}
