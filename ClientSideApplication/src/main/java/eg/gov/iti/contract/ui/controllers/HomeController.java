package eg.gov.iti.contract.ui.controllers;


import com.jfoenix.controls.JFXButton;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LogoutServiceInterface;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
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


    private StageCoordinator coordinator;
    LogoutServiceInterface logoutService;
    ChatClient client;
    ChatServerInterface chatService;
      ServerMessageServiceInterface friendMessageServiceInterface = ServicesLocator.getFriendMessageServiceInterface();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinator = StageCoordinator.getInstance();

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
        String messageText = messageContentTextField.getText();
        this.chatContentVBox.getChildren().add(new Label(messageText));
        sendMessageToMyFriend(messageText);
        System.out.println("Message Content is " + messageText);
        messageContentTextField.setText("");

    }

    private void sendMessageToMyFriend(String message) throws IOException {
        System.out.println(message);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ReceiverMessageView.fxml"));
        Parent messageSender = fxmlLoader.load();

        this.chatContentVBox.getChildren().add(messageSender);
        friendMessageServiceInterface.sendToMyFriend(message);
    }

    public void displayFriendMessage(String friendMessage) throws IOException {
        System.out.println(friendMessage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/SenderMessageView.fxml"));
        Parent messageSender = fxmlLoader.load();

        this.chatContentVBox.getChildren().add(messageSender);


    }

    private void register() throws RemoteException {
       friendMessageServiceInterface.register(new ChatClientImpl(this));

    }

    @FXML
    void logout(ActionEvent event) {
        try {
            if (logoutService.logout()) {
                chatService.unRegister(client);
                coordinator.switchToSecondLoginScene();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        coordinator.switchToSecondLoginScene();
    }
}