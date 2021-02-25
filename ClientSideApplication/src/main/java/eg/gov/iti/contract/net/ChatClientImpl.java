package eg.gov.iti.contract.net;


import eg.gov.iti.contract.ClientSideApplication;
import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.*;
import eg.gov.iti.contract.net.adapters.UserFriendAdapter;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.ui.controllers.HomeController;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.*;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {

    private Notifications notificationBuilder;
    private Node graphic;

    private String clientPhoneNumber = new UserAuthModel().getPhoneNumber();

    private HomeController homeController;
    private static ChatClientImpl instance;
    private UserAuthModel userAuthModel;
    private UserInvitationModel userInvitationModel;
    private ModelsFactory modelsFactory;
    private CurrentUserModel currentUser;

    private ChatClientImpl() throws RemoteException {
        userAuthModel = ModelsFactory.getInstance().getAuthUserModel();
        modelsFactory = ModelsFactory.getInstance();
        currentUser = modelsFactory.getCurrentUserModel();

//        System.out.println("userAuthModel : " + userAuthModel);

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
    public void receiveMessage(UserMessageDto userMessage) throws RemoteException {
        UserMessageModel messageModelFromMessageDto = MessageAdapter.getMessageModelFromMessageDto(userMessage);
        Platform.runLater(() -> {
            try {
                homeController.displayFriendMessage(messageModelFromMessageDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // todo create stage
    @Override
    public void receiveAnnouncement(String message) throws RemoteException {
        Platform.runLater(() -> {
//            Stage owner = new Stage(StageStyle.TRANSPARENT);
//            StackPane root = new StackPane();
//            root.setStyle("-fx-background-color: TRANSPARENT");
//            Scene scene = new Scene(root, 1, 1);
//            scene.setFill(Color.TRANSPARENT);
//            owner.setScene(scene);
//            owner.setWidth(1);
//            owner.setHeight(1);
//            owner.toBack();
//            owner.show();
            notificationBuilder.create()
                    .title("Announcement")
                    .text(message)
                    .graphic(graphic)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .owner(ClientSideApplication.getStage())
                    .showInformation();
        });
    }


    @Override
    public String toString() {
        return "ChatClientImpl{" +
                "userAuthModel=" + userAuthModel +
                '}';
    }

    @Override
    public void receiveInvitation(UserInvitationDto userInvitationDto) throws RemoteException {
        Platform.runLater(() -> {
            userInvitationModel = UserInvitationAdapter.getInvitationModelFromDto(userInvitationDto);
            System.out.println(userInvitationModel.getSenderName() + " invited you!");
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

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void receiveFile(byte[] fileContent, String fileName) throws RemoteException {
        try {
            homeController.receiveFile(fileContent, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyFriendUpdate(UserFriendDto userFriendDto) throws RemoteException {
//        currentUser.getFriends()
//                .stream()
//                .filter(friendModel -> friendModel.getPhoneNumber().equals(userFriendDto.getFriendPhoneNumber()))
//                .map(friendModel -> {
//                    friendModel.setName(userFriendDto.getName());
//                    friendModel.setImage(ImageConverter.getDecodedImage(userFriendDto.getImageEncoded()));
//                    friendModel.setImageEncoded(userFriendDto.getImageEncoded());
//                    friendModel.setStatusProperty(userFriendDto.getFriendStatus().getStatus());
//                    return friendModel;
//                }).findFirst().get();

        for (FriendModel friendModel : currentUser.getFriends()) {
            if (friendModel.getPhoneNumber().equals(userFriendDto.getFriendPhoneNumber())) {
                friendModel.setName(userFriendDto.getName());
                friendModel.setImage(ImageConverter.getDecodedImage(userFriendDto.getImageEncoded()));
                friendModel.setImageEncoded(userFriendDto.getImageEncoded());
                friendModel.setStatus(userFriendDto.getFriendStatus());
            }
        }
    }

    public void receiveAnnouncementFromServer(String announcementMessage) throws RemoteException {

        Platform.runLater(() -> {
//            Stage owner = new Stage(StageStyle.TRANSPARENT);
//            StackPane root = new StackPane();
//            root.setStyle("-fx-background-color: TRANSPARENT");
//            Scene scene = new Scene(root, 1, 1);
//            scene.setFill(Color.TRANSPARENT);
//            owner.setScene(scene);
//            owner.setWidth(1);
//            owner.setHeight(1);
//            owner.toBack();
//            owner.show();
            notificationBuilder.create()
                    .title("Announcement From Server")
                    .text(announcementMessage)
                    .graphic(graphic)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .owner(ClientSideApplication.getStage())
                    .showInformation();
        });
    }
}
