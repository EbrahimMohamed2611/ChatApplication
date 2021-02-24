package eg.gov.iti.contract.ui.controllers;


import com.jfoenix.controls.JFXButton;
import eg.gov.iti.contract.ClientSideApplication;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.CurrentUserAdapter;
import eg.gov.iti.contract.net.adapters.UserInvitationAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.*;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;

import eg.gov.iti.contract.ui.controllers.friendsControllers.FriendController;
import eg.gov.iti.contract.ui.controllers.friendsControllers.FriendRequestController;
import eg.gov.iti.contract.ui.controllers.messages.ReceiverMessageController;
import eg.gov.iti.contract.ui.controllers.messages.SenderMessageController;
import eg.gov.iti.contract.ui.helpers.*;
import eg.gov.iti.contract.ui.models.*;
import eg.gov.iti.contract.ui.models.CurrentUserModel;
import eg.gov.iti.contract.ui.models.UserMessageModel;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TextField messageContentTextField;
    @FXML
    private TextField searchTextField;
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
    private Label errorsLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private ComboBox<Status> statusComboBox;
    @FXML
    private ListView<HBox> chatListView;
    @FXML
    private ListView<AnchorPane> listView;
    @FXML
    private Circle status;

    ListView<AnchorPane> requestsListView;
    ListView<AnchorPane> friendsListView;

    // RMI Services
    private ChatServerInterface chatService;
    private LogoutServiceInterface logoutService;
    private ServerMessageServiceInterface friendMessageServiceInterface;
    private UpdateProfileServiceInterface updateProfileService;
    private InvitationServiceInterface invitationService;
    private StatusServiceInterface statusService;
    private ChatClientImpl client;

    BufferedImage img = null;
    private StageCoordinator coordinator;

    private ModelsFactory modelsFactory;
    private FriendModel currentFriend;
    private UserInvitationModel invitationModel;
    private UserAuthModel userAuthModel;
    private CurrentUserModel currentUserModel;

    private CachedCredentialsData credentialsData;
    private Image defaultUserImage;
    private Image userImage;

    private List<UserMessageModel> messageSessions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        friendMessageServiceInterface = ServicesLocator.getFriendMessageServiceInterface();
        updateProfileService = ServicesLocator.getUpdateProfileService();
        statusService = ServicesLocator.getStatusService();

        defaultUserImage = new Image("/pictures/avatar.png");
        client = ChatClientImpl.getInstance();
        client.setHomeController(this);
        try {
            img = ImageIO.read(new File("/pictures/avatar.png"));
//            System.out.println(img.toString());
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
//        System.out.println(userAuthModel);
//        System.out.println("DB user After" + user);
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

        currentUserModel.setStatus(Status.AVAILABLE);
        try {
            statusService.updateStatus(client, user.getStatus());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        userNameLabel.textProperty().bind(currentUserModel.fullNameProperty());
        userPhoneNumberLabel.textProperty().bind(userAuthModel.phoneNumberProperty());
        profilePic.fillProperty().bind(currentUserModel.getProfilePic().fillProperty());


        statusComboBox.getItems().add(Status.AVAILABLE);
        statusComboBox.getItems().add(Status.BUSY);
        statusComboBox.getItems().add(Status.AWAY);
        statusComboBox.getSelectionModel().selectFirst();

        statusComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            currentUserModel.setStatus(newValue);
            status.setFill(newValue.getStatus());
            try {
                statusService.updateStatus(client, newValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

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
        messageSessions = new ArrayList<>();
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
        System.out.println("Message Content is " + messageContentTextField.getText());
        sendMessageToMyFriend(userMessageModel);
        messageContentTextField.setText("");
    }

    private void sendMessageToMyFriend(UserMessageModel message) {
        new Thread(() -> {
            Platform.runLater(() -> {
                try {
                    UserMessageDto messageDto;
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/messages/SenderMessageView.fxml"));
                    Parent messageSender = null;
                    messageSender = fxmlLoader.load();
                    SenderMessageController senderMessageController = fxmlLoader.getController();
                    senderMessageController.getSenderMessageBodyLabel().setText(message.getMessageBody());
                    senderMessageController.getSenderNameLabel().setText(message.getName());
                    Image image = ImageConverter.getDecodedImage(message.getImageEncoded());
                    senderMessageController.getSenderImgView().setImage(image);
                    senderMessageController.getSenderTimeStampLabel().setText(String.valueOf(new Date()));
                    currentFriend.getMessages().add((HBox) messageSender);
                    messageDto = MessageAdapter.getMessageDtoFromMessageModel(message);
                    friendMessageServiceInterface.sendToMyFriend(messageDto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }).start();
    }

    public void displayFriendMessage(UserMessageModel friendMessage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/messages/ReceiverMessageView.fxml"));
        Parent messageReceiver = fxmlLoader.load();
        ReceiverMessageController receiverMessageController = fxmlLoader.getController();

        receiverMessageController.getReceiverNameLabel().setText(friendMessage.getName());
        Image image = ImageConverter.getDecodedImage(friendMessage.getImageEncoded());

        receiverMessageController.getReceiverImgView().setImage(image);
        receiverMessageController.getReceiverMessageBodyLabel().setText(friendMessage.getMessageBody());
        receiverMessageController.getReceiverTimeStampLabel().setText(String.valueOf(friendMessage.getMessageDate()));

        currentUserModel.getFriends().stream()
                .filter(friendModel -> friendModel.getPhoneNumber().equals(friendMessage.getSenderPHoneNumber()))
                .map(friendModel -> {
                    friendModel.getMessages().add((HBox) messageReceiver);
                    return friendModel;
                }).count();

//        currentFriend.getMessages().add((HBox) messageReceiver);

    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            if (logoutService.logout()) {
                chatService.unRegister(client);
                credentialsData.clearCredentials();
                currentUserModel.getFriends().clear();
                currentUserModel.getInvitations().clear();
                coordinator.switchToSecondLoginScene();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        coordinator.switchToSecondLoginScene();
    }

    @FXML
    private void exit() {
        try {
            chatService.unRegister(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        currentUserModel.getFriends().clear();
        currentUserModel.getInvitations().clear();
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
        new Thread(() -> {
            try {
                if (searchTextField.getText().equals(userAuthModel.getPhoneNumber())) {
                    Platform.runLater(() -> showError("you cannot add yourself"));
                    return;
                }

                for (FriendModel friendModel : currentUserModel.getFriends()) {
                    if (searchTextField.getText().equals(friendModel.getPhoneNumber())) {
                        Platform.runLater(() -> showError("This is your friend"));
                        return;
                    }
                }

                invitationModel.setReceiverPhoneNumber(searchTextField.getText());

                if (invitationService.sendInvitation(UserInvitationAdapter.getInvitationDtoFromModel(invitationModel))) {
                    Platform.runLater(() -> showMessage("Invitation sent."));
                } else {
                    Platform.runLater(() -> showError("Not valid invitation!!"));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }).start();
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
                friendRequest.getFriendName().textProperty().bind(invitationModel.senderNameProperty());
                friendRequest.getFriendImage().fillProperty().bind(invitationModel.getSenderPicCircle().fillProperty());
                Parent finalRequestInstance = requstInstance;

                friendRequest.getAcceptButton().setOnAction(e -> {
                    FriendModel friendModel = new FriendModel();
                    friendModel.setName("");
                    friendModel.setPhoneNumber(invitationModel.getSenderPhoneNumber());
                    try {
                        invitationService.acceptInvitation(UserInvitationAdapter.getInvitationDtoFromModel(invitationModel));
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    requestsListView.getItems().remove(finalRequestInstance);
                });

                friendRequest.getDeclineButton().setOnAction(e -> {
                    try {
                        invitationService.rejectInvitation(UserInvitationAdapter.getInvitationDtoFromModel(invitationModel));
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    System.out.println("List size before " + requestsListView.getItems().size());
                    requestsListView.getItems().remove(finalRequestInstance);
                    System.out.println("List size after " + requestsListView.getItems().size());

                    System.out.println("Invitations before " + currentUserModel.getInvitations().size());
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
                friendController.getStatus().fillProperty().bind(friendModel.getStatusCircle().fillProperty());

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

    @FXML
    private void saveSessionButton() {
        Thread saveSessionThread = new Thread(() -> {
            ObservableList<HBox> items = chatListView.getItems();
            ArrayList<Object> messages = items.stream().map(hb -> {
                UserMessageModel messages1 = getMessages(hb);
                messageSessions.add(messages1);
                return messageSessions;
            }).collect(Collectors.toCollection(ArrayList::new));
            SaveSession.saveSession(messageSessions);
        });
        saveSessionThread.start();
    }

    private UserMessageModel getMessages(HBox hBox) {
        UserMessageModel message = new UserMessageModel();
        if (hBox.getChildren().get(0) instanceof VBox) {
            VBox outerVbox = (VBox) hBox.getChildren().get(0);
            Label messageContent = (Label) outerVbox.getChildren().get(1);
            HBox hbox = (HBox) outerVbox.getChildren().get(2);
            Label time = (Label) hbox.getChildren().get(1);
            System.out.println(messageContent.getText() + time.getText());
            message.setMessageBody(messageContent.getText());
            message.setMessageDate(new Date());
        } else {
            VBox outerVbox = (VBox) hBox.getChildren().get(1);
            Label messageContent = (Label) outerVbox.getChildren().get(1);
            HBox hbox = (HBox) outerVbox.getChildren().get(2);
            Label time = (Label) hbox.getChildren().get(1);
            System.out.println(messageContent.getText() + time.getText());
            System.out.println(messageContent.getText() + time.getText());
            message.setMessageBody(messageContent.getText());
            message.setMessageDate(new Date());
        }
        return message;
    }

    public void showError(String errorMessage) {
        errorsLabel.setText(errorMessage);
        new Thread(() -> {
            errorsLabel.setVisible(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            errorsLabel.setVisible(false);
        }).start();
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
        new Thread(() -> {
            messageLabel.setVisible(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            messageLabel.setVisible(false);
        }).start();
    }
}