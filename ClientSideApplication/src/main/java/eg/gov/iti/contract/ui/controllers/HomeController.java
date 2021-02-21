package eg.gov.iti.contract.ui.controllers;


import com.jfoenix.controls.JFXButton;
import eg.gov.iti.contract.ClientSideApplication;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.InvitationServiceInterface;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;

import eg.gov.iti.contract.ui.controllers.friendsControllers.FriendController;
import eg.gov.iti.contract.ui.controllers.friendsControllers.FriendRequestController;
import eg.gov.iti.contract.ui.controllers.messages.ReceiverMessageController;
import eg.gov.iti.contract.ui.controllers.messages.SenderMessageController;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.models.*;

import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.*;

import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LogoutServiceInterface;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private TextField messageContentTextField;

    // for invitation handling
    @FXML
    private TextField searchTextField;

    @FXML
    private VBox chatContentVBox;


    @FXML
    JFXButton SendJFXButton;

    @FXML
    ScrollPane scrollPane;

    @FXML
    private JFXButton editProfileBtn;

    @FXML
    ListView<AnchorPane> listView;
    ListView<AnchorPane> requestsListView;
    ListView<AnchorPane> friendsListView;

    BufferedImage img = null;
    private StageCoordinator coordinator;
    LogoutServiceInterface logoutService;
    //    ChatClient client;
    ChatClientImpl client;
    ChatServerInterface chatService;

    // Fields for invitation handling
    ModelsFactory modelsFactory;
    InvitationServiceInterface invitationService;
    UserInvitationModel invitationModel;
    UserAuthModel userAuthModel;
    CurrentUserModel currentUserModel;
//    ObservableList<UserInvitationModel> invitations;
    //

    ServerMessageServiceInterface friendMessageServiceInterface = ServicesLocator.getFriendMessageServiceInterface();

    CachedCredentialsData credentialsData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            img = ImageIO.read(new File("/pictures/avatar.png"));
            System.out.println(img.toString());
        } catch (IOException e) {
        }

        coordinator = StageCoordinator.getInstance();

        credentialsData = CachedCredentialsData.getInstance();

        client = ChatClientImpl.getInstance();
        chatService = ServicesLocator.getChatServerInterface();
        FontIcon editIcon = new FontIcon("mdi2a-account-edit");
        editIcon.setIconSize(22);
        editIcon.setIconColor(Color.WHITE);
        editProfileBtn.setGraphic(editIcon);
        try {
            logoutService = ServicesLocator.getLogoutService();
            register();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        invitationService = ServicesLocator.getInvitationService();
        modelsFactory = ModelsFactory.getInstance();
        invitationModel = modelsFactory.getUserInvitationModel();
        userAuthModel = modelsFactory.getAuthUserModel();
        currentUserModel = modelsFactory.getCurrentUserModel();
        invitationModel.senderPhoneNumberProperty().bindBidirectional(userAuthModel.phoneNumberProperty());

//        chatContentVBox.maxWidthProperty().bind(chatSpace.widthProperty());

        scrollPane.vvalueProperty().bind(chatContentVBox.heightProperty());
        chatContentVBox.prefWidthProperty().bind(scrollPane.widthProperty());

        requestsListView = new ListView<>();
        friendsListView = new ListView<>();

        currentUserModel.getInvitations().addListener((ListChangeListener<UserInvitationModel>) c -> {
            while (c.next()) {
                    requestsListView.getItems().clear();
                    requestsListView.setItems(getRequestsListView().getItems());
            }
        });

        currentUserModel.getFriends().addListener((ListChangeListener<FriendModel>) c -> {
            while (c.next()) {
                showFriends();
            }
        });
        showFriends();
    }

    public HomeController() throws RemoteException {


    }

    @FXML
    private void sendMessage(ActionEvent actionEvent) throws IOException {
//        String messageText = messageContentTextField.getText();
        UserMessageModel userMessageModel = new UserMessageModel();
        userMessageModel.setMessageBody(messageContentTextField.getText());
        userMessageModel.setMessageDate(new Date());

        //  ImageView sourceimage = new ImageView(new Image("D:\\ITI\\Java Project Specification\\ChatApplication\\ClientSideApplication\\src\\main\\resources\\pictures\\avatar.png"));
        //  Image imageView = new Image(String.valueOf(new File("D:\\ITI\\Java Project Specification\\ChatApplication\\ClientSideApplication\\src\\main\\resources\\pictures\\avatar.png")));
        //ImageConverter.convertFromImageToString(imageView);
        //  userMessageModel.setImageView(sourceimage);

        userMessageModel.setImageEncoded(ImageConverter.getEncodedImage(new File("D:\\ITI\\Java Project Specification\\ChatApplication\\ClientSideApplication\\src\\main\\resources\\pictures\\avatar.png")));
//        System.out.println(ImageConverter.convertToFxImage(img));
        userMessageModel.setName("Ali");
        // this.chatContentVBox.getChildren().add(new Label(messageContentTextField.getText()));
        System.out.println("=====" + userMessageModel);
        sendMessageToMyFriend(userMessageModel);

        System.out.println("Message Content is " + messageContentTextField.getText());
        messageContentTextField.setText("");

    }

    private void sendMessageToMyFriend(UserMessageModel message) throws IOException {

        System.out.println(message);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/messages/SenderMessageView.fxml"));
        Parent messageSender = fxmlLoader.load();
        SenderMessageController senderMessageController = fxmlLoader.getController();

        senderMessageController.getSenderMessageBodyLabel().setText(message.getMessageBody());
        senderMessageController.getSenderNameLabel().setText(message.getName());
        Image image = ImageConverter.getDecodedImage(message.getImageEncoded());

        senderMessageController.getSenderImgView().setImage(image);
        senderMessageController.getSenderTimeStampLabel().setText(String.valueOf(new Date()));

        this.chatContentVBox.getChildren().add(messageSender);
        UserMessageDto messageDto = MessageAdapter.getMessageDtoFromMessageModel(message);
        System.out.println("Message dto" + messageDto);
        friendMessageServiceInterface.sendToMyFriend(new ChatClientImpl(this), messageDto);
    }

    public void displayFriendMessage(UserMessageModel friendMessage) throws IOException {
        System.out.println(friendMessage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/messages/ReceiverMessageView.fxml"));
        Parent messageReceiver = fxmlLoader.load();
        ReceiverMessageController receiverMessageController = fxmlLoader.getController();

        receiverMessageController.getReceiverNameLabel().setText(friendMessage.getName());
        Image image = ImageConverter.getDecodedImage(friendMessage.getImageEncoded());

        receiverMessageController.getReceiverImgView().setImage(image);
        receiverMessageController.getReceiverMessageBodyLabel().setText(friendMessage.getMessageBody());
        receiverMessageController.getReceiverTimeStampLabel().setText(String.valueOf(friendMessage.getMessageDate()));
        this.chatContentVBox.getChildren().add(messageReceiver);


    }

    private void register() throws RemoteException {
        friendMessageServiceInterface.register(new ChatClientImpl(this));

    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            if (logoutService.logout()) {
                chatService.unRegister(client);

                credentialsData.clearCredentials();

                coordinator.switchToSecondLoginScene();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        coordinator.switchToSecondLoginScene();
    }

    // todo complete exit method implementation
    @FXML
    private void exit() {
//        Platform.exit();
        System.exit(0);
        try {
            chatService.unRegister(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void inviteFriend() {
        try {
            if (searchTextField.getText().equals(userAuthModel.getPhoneNumber())){
                System.out.println("you cannot add yourself");
                return;
            }

            for (FriendModel friendModel : currentUserModel.getFriends()) {
                if (searchTextField.getText().equals(friendModel.getPhoneNumber())) {
                    System.out.println("This is your friend");
                    return;
                }
            }

            invitationModel.setReceiverPhoneNumber(searchTextField.getText());

            if (invitationService.sendInvitation(UserInvitationAdapter.getInvitationDtoFromModel(invitationModel))) {
                System.out.println(invitationModel + " sent.");
            } else {
                System.out.println("Not valid invitation!!");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void editProfile(ActionEvent event) {
        coordinator.switchToUpdateProfileScene();
    }

    @FXML
    void showFriends() {
        listView.getItems().clear();
        listView.setItems(getFriendsListView().getItems());
        listView.getSelectionModel().select(0);
        listView.getFocusModel().focus(0);
        System.out.println("Marhabaaa");
    }

    @FXML
    void showRequests() {
        listView.getItems().clear();
        listView.setItems(getRequestsListView().getItems());
    }

    private ListView<AnchorPane> getRequestsListView() {
        requestsListView.getItems().clear();

        if (!currentUserModel.getInvitations().isEmpty()) {
            for (UserInvitationModel invitationModel : currentUserModel.getInvitations()) {
                Parent requstInstance = null;
                FriendRequestController friendRequest;

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(ClientSideApplication.class.getResource("/views/Friends/FriendRequest.fxml"));
                try {
                    requstInstance = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                friendRequest = loader.getController();
                friendRequest.getFriendName().setText(invitationModel.getSenderPhoneNumber());
                Parent finalRequstInstance = requstInstance;

                friendRequest.getAcceptButton().setOnAction(e -> {
                    FriendModel friendModel = new FriendModel();
                    friendModel.setName("");
                    friendModel.setPhoneNumber(invitationModel.getSenderPhoneNumber());
                    try {
                        invitationService.acceptInvitation(UserInvitationAdapter.getInvitationDtoFromModel(invitationModel));
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    System.out.println(friendModel + "Added");
                    requestsListView.getItems().remove(finalRequstInstance);
                });

                friendRequest.getDeclineButton().setOnAction(e -> {
                    try {
                        invitationService.rejectInvitation(UserInvitationAdapter.getInvitationDtoFromModel(invitationModel));
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    System.out.println(invitationModel.getSenderPhoneNumber() + "rejected");
                    requestsListView.getItems().remove(finalRequstInstance);
                });

                requestsListView.getItems().add((AnchorPane) requstInstance);
            }
        }
        return requestsListView;
    }

    private ListView<AnchorPane> getFriendsListView() {
        // clear list
        friendsListView.getItems().clear();
        // append friends to list if exists
        if (!currentUserModel.getFriends().isEmpty()) {
            for (FriendModel friendModel : currentUserModel.getFriends()) {
                Parent friendInstance = null;
                FriendController friendController;

                FXMLLoader loader = new FXMLLoader();

                loader.setLocation(ClientSideApplication.class.getResource("/views/Friends/Friend.fxml"));
                try {
                    friendInstance = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                friendController = loader.getController();
                friendController.getFriendName().setText(friendModel.getPhoneNumber());

                friendsListView.getItems().add((AnchorPane) friendInstance);
            }
        }
        return friendsListView;
    }
}