package eg.gov.iti.contract.net;

import eg.gov.iti.contract.client.ClientMessageService;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;
import eg.gov.iti.contract.ui.controllers.HomeController;
import eg.gov.iti.contract.ui.models.UserMessageModel;
import javafx.application.Platform;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientMessageServiceImpl extends UnicastRemoteObject implements ClientMessageService {

    HomeController homeController ;
    public ClientMessageServiceImpl(HomeController homeController)throws RemoteException {
        this.homeController = homeController;
    }

    @Override
    public void receiveFromMyFriend(UserMessageDto userMessageDto) throws RemoteException {
        UserMessageModel messageModel= MessageAdapter.getMessageModelFromMessageDto(userMessageDto);
        System.out.println(userMessageDto);
        Platform.runLater(()->{
            try {
                homeController.displayFriendMessage(messageModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
