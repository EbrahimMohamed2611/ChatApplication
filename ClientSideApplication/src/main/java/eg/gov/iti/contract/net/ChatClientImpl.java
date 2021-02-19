package eg.gov.iti.contract.net;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserInvitationDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.ui.controllers.HomeController;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserInvitationModel;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.ui.controllers.HomeController;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserMessageModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private Notifications notificationBuilder;
    private Node graphic;

    private HomeController homeController;
    private static ChatClientImpl instance;
    private UserAuthModel userAuthModel;
    private UserInvitationModel userInvitationModel;

//    public ChatClientImpl(HomeController homeController) throws RemoteException {
//        this.homeController = homeController;
//        userAuthModel = ModelsFactory.getInstance().getAuthUserModel();
//        System.out.println("ChatClientImpl calling : " );
//    }

    private ChatClientImpl() throws RemoteException {
        userAuthModel = ModelsFactory.getInstance().getAuthUserModel();
        System.out.println("userAuthModel : " + userAuthModel);
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


        @Override
        public void receiveMessage (UserMessageDto userMessage) throws RemoteException {
            UserMessageModel messageModelFromMessageDto = MessageAdapter.getMessageModelFromMessageDto(userMessage);
            System.out.println("messageModelFromMessageDto " + messageModelFromMessageDto);
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

        Platform.runLater(() -> {
                    notificationBuilder.create()
                .title("Announcement")
                .text(message)
                .graphic(graphic)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .darkStyle()
                .showInformation();
        });

    }

    @Override
    public void notify(String message, int type) throws RemoteException {
        System.out.println("Notification");
    }

    @Override
    public void receiveUserDto(UserDto userDto) throws RemoteException {


        }


    @Override
    public String toString() {
        return "ChatClientImpl{" +
                "userAuthModel=" + userAuthModel +
                '}';
    }

    @Override
    public void receiveInvitation(UserInvitationDto userInvitationDto) throws RemoteException {
        userInvitationModel = UserInvitationAdapter.getInvitationModelFromDto(userInvitationDto);
        System.out.println(userInvitationModel.getSenderPhoneNumber() + " invited you!");
    }



    // todo replace user auth model with current user model
    @Override
    public String getPhoneNumber() throws RemoteException {
        return userAuthModel.getPhoneNumber();
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void receiveFile(byte [] fileContent, String fileName) throws RemoteException {
        try {
            homeController.receiveFile(fileContent, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
