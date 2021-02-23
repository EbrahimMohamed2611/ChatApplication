package eg.gov.iti.contract.ui.controllers;


import com.jfoenix.controls.JFXButton;
import eg.gov.iti.contract.ClientSideApplication;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.CurrentUserAdapter;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.InvitationServiceInterface;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.UpdateProfileServiceInterface;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;

import eg.gov.iti.contract.ui.controllers.friendsControllers.FriendController;
import eg.gov.iti.contract.ui.controllers.friendsControllers.FriendRequestController;
import eg.gov.iti.contract.ui.controllers.messages.ReceiverMessageController;
import eg.gov.iti.contract.ui.controllers.messages.SenderMessageController;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.models.*;
import eg.gov.iti.contract.ui.models.CurrentUserModel;
import eg.gov.iti.contract.ui.models.UserMessageModel;

import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.*;

import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LogoutServiceInterface;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private TextField messageContentTextField;

    // for invitation handling
    @FXML
    private TextField searchTextField;

//    @FXML
//    private VBox chatContentVBox;

    @FXML
    private Circle profilePic;
    @FXML
    ScrollPane scrollPane;

    @FXML
    private JFXButton editProfileBtn;
    @FXML
    private Label userNameLabel;

    @FXML
    private Label userPhoneNumberLabel;

    @FXML
    ListView<HBox> chatListView;

    @FXML
    ListView<AnchorPane> listView;
    ListView<AnchorPane> requestsListView;
    ListView<AnchorPane> friendsListView;

    BufferedImage img = null;
    private StageCoordinator coordinator;
    private LogoutServiceInterface logoutService;
    private ChatClientImpl client;
    private ChatServerInterface chatService;

    // Fields for invitation handling
    private ModelsFactory modelsFactory;
    private InvitationServiceInterface invitationService;
    private UserInvitationModel invitationModel;
    private UserAuthModel userAuthModel;
    private CurrentUserModel currentUserModel;
    //

    private ServerMessageServiceInterface friendMessageServiceInterface;
    private UpdateProfileServiceInterface updateProfileService;
    private CachedCredentialsData credentialsData;
    private Image defaultUserImage;
    private Image userImage;

    //    private String receiverPhoneNumber;
    private FriendModel currentFriend;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        friendMessageServiceInterface = ServicesLocator.getFriendMessageServiceInterface();
        updateProfileService = ServicesLocator.getUpdateProfileService();
        defaultUserImage = new Image("/pictures/avatar.png");
        if (updateProfileService == null)
            System.out.println("fuck");
        client = ChatClientImpl.getInstance();
        client.setHomeController(this);
        try {
            img = ImageIO.read(new File("/pictures/avatar.png"));
            System.out.println(img.toString());
        } catch (IOException e) {
        }

        coordinator = StageCoordinator.getInstance();

        credentialsData = CachedCredentialsData.getInstance();
        logoutService = ServicesLocator.getLogoutService();

        chatService = ServicesLocator.getChatServerInterface();
        FontIcon editIcon = new FontIcon("mdi2a-account-edit");
        editIcon.setIconSize(22);
        editIcon.setIconColor(Color.WHITE);
        editProfileBtn.setGraphic(editIcon);
        invitationService = ServicesLocator.getInvitationService();
        modelsFactory = ModelsFactory.getInstance();
        invitationModel = modelsFactory.getUserInvitationModel();
        userAuthModel = modelsFactory.getAuthUserModel();
        currentUserModel = modelsFactory.getCurrentUserModel();
        invitationModel.senderPhoneNumberProperty().bindBidirectional(userAuthModel.phoneNumberProperty());
        System.out.println(userAuthModel.getPhoneNumber());
        UserDto user = null;
        try {
            user = updateProfileService.getUser(userAuthModel.getPhoneNumber());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(userAuthModel);
        System.out.println("DB user After" + user);
        var tempModel = CurrentUserAdapter.getUserModelFromUserDtoAdapter(user);
        currentUserModel.setFullName(tempModel.getFullName());
        currentUserModel.setEmail(tempModel.getEmail());
        currentUserModel.setCountry(tempModel.getCountry());
        currentUserModel.setBio(tempModel.getBio());
        currentUserModel.setDateOfBirth(tempModel.getDateOfBirth());
        currentUserModel.setImageEncoded(tempModel.getImageEncoded());
        if (currentUserModel.getImageEncoded() == null) {
            currentUserModel.setProfileImage(defaultUserImage);

        } else {
            userImage = ImageConverter.getDecodedImage(currentUserModel.getImageEncoded());
            currentUserModel.setProfileImage(userImage);
        }
        currentUserModel.setPassword(tempModel.getPassword());
        currentUserModel.phoneNumberProperty().bind(userAuthModel.phoneNumberProperty());
        userNameLabel.textProperty().bind(currentUserModel.fullNameProperty());
        userPhoneNumberLabel.textProperty().bind(userAuthModel.phoneNumberProperty());
        profilePic.fillProperty().bind(currentUserModel.getProfilePic().fillProperty());
//        chatContentVBox.maxWidthProperty().bind(chatSpace.widthProperty());

//        scrollPane.vvalueProperty().bind(chatContentVBox.heightProperty());
//        chatContentVBox.prefWidthProperty().bind(scrollPane.widthProperty());

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

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Your action here
            if (newValue != null) {
                VBox outerVbox = (VBox) newValue.getChildren().get(0);
                HBox outerHBox = (HBox) outerVbox.getChildren().get(0);
                VBox innerVbox = (VBox) outerHBox.getChildren().get(1);
                Label friendPhoneNumber = (Label) innerVbox.getChildren().get(1);

                if (!currentUserModel.getFriends().isEmpty()) {
                    currentFriend = currentUserModel.getFriends()
                            .stream()
                            .filter(f -> f.getPhoneNumber().equals(friendPhoneNumber.getText()))
                            .findFirst().get();

                    chatListView.setItems(currentFriend.getMessages());
                }
            }
        });
    }

    public HomeController() throws RemoteException {
    }


    @FXML
    private void sendMessage(ActionEvent actionEvent) throws IOException {

        UserMessageModel userMessageModel = new UserMessageModel();
        userMessageModel.setMessageBody(messageContentTextField.getText());
        userMessageModel.setMessageDate(new Date());
        userMessageModel.setImageEncoded(ImageConverter.getEncodedImage(new File("src/main/resources/pictures/avatar.png")));
        userMessageModel.setReceiverPhoneNumber(currentFriend.getPhoneNumber());

        userMessageModel.setSenderPHoneNumber(userAuthModel.getPhoneNumber());
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

        currentFriend.getMessages().add((HBox) messageSender);
        UserMessageDto messageDto = MessageAdapter.getMessageDtoFromMessageModel(message);
        System.out.println("Message dto" + messageDto);
        friendMessageServiceInterface.sendToMyFriend(messageDto);
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
        currentFriend.getMessages().add((HBox) messageReceiver);


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
            if (searchTextField.getText().equals(userAuthModel.getPhoneNumber())) {
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
                if (invitationModel.getSenderImageEncoded() != null)
                    friendRequest.getFriendImage().setFill(new ImagePattern(ImageConverter.getDecodedImage(invitationModel.getSenderImageEncoded())));
                else {
                    friendRequest.getFriendImage().setFill(new ImagePattern(defaultUserImage));
                }
                System.out.println(invitationModel.senderNameProperty().get());
                friendRequest.getFriendName().textProperty().bind(invitationModel.senderNameProperty());
                friendRequest.getFriendImage().fillProperty().bind(invitationModel.getSenderPicCircle().fillProperty());
                System.out.println(friendRequest.getFriendName().getText() + " Added");
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
                    System.out.println("List size before " + requestsListView.getItems().size());
                    requestsListView.getItems().remove(finalRequstInstance);
                    System.out.println("List size after " + requestsListView.getItems().size());

                    System.out.println("Invitations befor " + currentUserModel.getInvitations().size());
                    currentUserModel.getInvitations().remove(invitationModel);
                    System.out.println("Invitations after " + currentUserModel.getInvitations().size());
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
                if (friendModel.getImageEncoded() != null)
                    friendController.getFriendImage().setFill(new ImagePattern(ImageConverter.getDecodedImage(friendModel.getImageEncoded())));
                else {
                    friendController.getFriendImage().setFill(new ImagePattern(defaultUserImage));
                }
                friendController.getFriendImage().fillProperty().bind(friendModel.getFriendImage().fillProperty());
                friendController.getFriendName().textProperty().bind(friendModel.nameProperty());
                friendController.getPhoneNumber().textProperty().bind(friendModel.phoneNumberProperty());

                friendsListView.getItems().add((AnchorPane) friendInstance);
            }
        }
        return friendsListView;
    }


    @FXML
    private void sendAttachments() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file;
        StringBuilder fileContent = new StringBuilder();
        file = fileChooser.showOpenDialog(null);
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try {    //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        friendMessageServiceInterface.sendFile(bFile, file.getName(), "01024261188");


    }


    public void receiveFile(byte[] fileContent, String fileName) throws IOException {
        System.out.println("file name receiver" + fileName);

        Platform.runLater(() -> {
            FileChooser fileChooser = new FileChooser();
            File file;
            fileChooser.setInitialFileName(fileName);
            file = fileChooser.showSaveDialog(ClientSideApplication.getStage());
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte b[] = fileContent;//converting string into byte array
            try {
                fout.write(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }


}
