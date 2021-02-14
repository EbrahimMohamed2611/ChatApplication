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


      ServerMessageServiceInterface friendMessageServiceInterface = ServicesLocator.getFriendMessageServiceInterface();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
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
}
