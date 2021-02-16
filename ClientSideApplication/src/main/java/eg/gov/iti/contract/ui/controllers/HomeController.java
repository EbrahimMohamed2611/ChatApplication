package eg.gov.iti.contract.ui.controllers;


import com.jfoenix.controls.JFXButton;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;

import eg.gov.iti.contract.ui.controllers.messages.ReceiverMessageController;
import eg.gov.iti.contract.ui.controllers.messages.SenderMessageController;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.models.UserMessageModel;

import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private TextField messageContentTextField;

    @FXML
    private VBox chatContentVBox;


    @FXML
    JFXButton SendJFXButton;

    @FXML
    ScrollPane scrollPane;

    BufferedImage img = null;
    private StageCoordinator coordinator;
    LogoutServiceInterface logoutService;
    ChatClient client;
    ChatServerInterface chatService;

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

        try {
        logoutService = ServicesLocator.getLogoutService();
            register();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

//        chatContentVBox.maxWidthProperty().bind(chatSpace.widthProperty());

        scrollPane.vvalueProperty().bind(chatContentVBox.heightProperty());
        chatContentVBox.prefWidthProperty().bind(scrollPane.widthProperty());
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
        System.out.println("====="+userMessageModel);
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
        friendMessageServiceInterface.sendToMyFriend(new ChatClientImpl(this),messageDto);
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
    void logout(ActionEvent event) {
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
    void exit() {
//        Platform.exit();
        System.exit(0);
    }
}