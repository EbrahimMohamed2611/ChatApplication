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

import eg.gov.iti.contract.ui.controllers.messages.ReceiverMessageController;
import eg.gov.iti.contract.ui.controllers.messages.SenderMessageController;
import eg.gov.iti.contract.ui.helpers.*;
import eg.gov.iti.contract.ui.models.CurrentUserModel;
import eg.gov.iti.contract.ui.models.UserMessageModel;

import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserInvitationModel;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.*;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LogoutServiceInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;
import org.w3c.dom.events.MouseEvent;

import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.*;

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
    JFXButton SaveChat;


    @FXML
    private JFXButton editProfileBtn;


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

    private CachedCredentialsData credentialsData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        friendMessageServiceInterface = ServicesLocator.getFriendMessageServiceInterface();
        client = ChatClientImpl.getInstance();
        client.setHomeController(this);

        try {
            img = ImageIO.read(new File("/pictures/avatar.png"));
            System.out.println(img.toString());
        } catch (IOException e) {
        }

        coordinator = StageCoordinator.getInstance();

        credentialsData = CachedCredentialsData.getInstance();

        chatService = ServicesLocator.getChatServerInterface();
        FontIcon editIcon = new FontIcon("mdi2a-account-edit");
        editIcon.setIconSize(22);
        editIcon.setIconColor(Color.WHITE);
        editProfileBtn.setGraphic(editIcon);
//        try {
//        logoutService = ServicesLocator.getLogoutService();
//            register();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

        invitationService = ServicesLocator.getInvitationService();
        modelsFactory = ModelsFactory.getInstance();
        invitationModel = modelsFactory.getUserInvitationModel();
        userAuthModel = modelsFactory.getAuthUserModel();
        invitationModel.senderPhoneNumberProperty().bindBidirectional(userAuthModel.phoneNumberProperty());

//        chatContentVBox.maxWidthProperty().bind(chatSpace.widthProperty());

        scrollPane.vvalueProperty().bind(chatContentVBox.heightProperty());
        chatContentVBox.prefWidthProperty().bind(scrollPane.widthProperty());
    }

    public HomeController() throws RemoteException {
    }

    UserMessageModel userMessageModel = new UserMessageModel();

    @FXML
    private void sendMessage(ActionEvent actionEvent) throws IOException {

        userMessageModel.setMessageBody(messageContentTextField.getText());
        userMessageModel.setMessageDate(new Date());
        userMessageModel.setImageEncoded(ImageConverter.getEncodedImage(new File("E:\\saveChatAmira\\ChatApplication\\ClientSideApplication\\src\\main\\resources\\pictures\\avatar.png")));
        userMessageModel.setReceiverPhoneNumber("01024261188");
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

        this.chatContentVBox.getChildren().add(messageSender);
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
        this.chatContentVBox.getChildren().add(messageReceiver);


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
    private void sendAttachments() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file;
        StringBuilder fileContent = new StringBuilder();
       file = fileChooser.showOpenDialog(null);
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try
        {    //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            friendMessageServiceInterface.sendFile( bFile, file.getName(), "01024261188");


    }





}
