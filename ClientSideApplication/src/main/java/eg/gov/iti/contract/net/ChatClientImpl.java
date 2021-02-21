package eg.gov.iti.contract.net;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.*;
import eg.gov.iti.contract.net.adapters.UserFriendAdapter;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.ui.controllers.HomeController;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.models.*;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import javafx.application.Platform;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {

    private String clientPhoneNumber = new UserAuthModel().getPhoneNumber();
    HomeController homeController;
    private static ChatClientImpl instance;
    private UserAuthModel userAuthModel;
    private UserInvitationModel userInvitationModel;
    ModelsFactory modelsFactory;
    CurrentUserModel currentUser;

    public ChatClientImpl(HomeController homeController) throws RemoteException {
        this.homeController = homeController;
    }

    private ChatClientImpl() throws RemoteException {
        userAuthModel = ModelsFactory.getInstance().getAuthUserModel();
        modelsFactory = ModelsFactory.getInstance();
        currentUser = modelsFactory.getCurrentUserModel();
    }

    public static ChatClientImpl getInstance() {
        if (instance == null) {
            try {
                instance = new ChatClientImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


//    public void receiveMessage(String userMessage)throws RemoteException{
//        System.out.println(userMessage);
//        Platform.runLater(()->{
//            homeController.displayFriendMessage(userMessage);
//        });
//    }


    //    @Override
//    public void receiveMessage(UserMessageDto userMessage) throws RemoteException {
//
//    }

    @Override
    public void receiveMessage(UserMessageDto userMessage) throws RemoteException {
        UserMessageModel messageModelFromMessageDto = MessageAdapter.getMessageModelFromMessageDto(userMessage);
        System.out.println(userMessage);
        Platform.runLater(() -> {
            try {
                homeController.displayFriendMessage(messageModelFromMessageDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void receiveAnnouncement(String message) throws RemoteException {

    }

    @Override
    public void notify(String message, int type) throws RemoteException {

    }

    @Override
    public void receiveUserDto(UserDto userDto) throws RemoteException {

    }

    @Override
    public void receiveInvitation(UserInvitationDto userInvitationDto) throws RemoteException {
        Platform.runLater(() -> {
            userInvitationModel = UserInvitationAdapter.getInvitationModelFromDto(userInvitationDto);
            System.out.println(userInvitationModel.getSenderPhoneNumber() + " invited you!");
            currentUser.getInvitations().add(userInvitationModel);
        });
    }

    @Override
    public void addFriend(UserFriendDto userFriendShipDto) throws RemoteException {
        Platform.runLater(() -> {
            FriendModel friendModel = UserFriendAdapter.getFriendModelFromDto(userFriendShipDto);
            modelsFactory.getCurrentUserModel().getFriends().add(friendModel);

            for (UserInvitationModel invitationModel : currentUser.getInvitations()) {
                if (invitationModel.getSenderPhoneNumber().equals(userFriendShipDto.getFriendPhoneNumber())) {
                    currentUser.getInvitations().remove(invitationModel);
                    break;
                }
            }
        });
    }

    // todo replace user auth model with current user model
    @Override
    public String getPhoneNumber() throws RemoteException {
        return userAuthModel.getPhoneNumber();
    }
}
