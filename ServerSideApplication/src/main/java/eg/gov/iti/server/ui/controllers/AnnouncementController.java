package eg.gov.iti.server.ui.controllers;

import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.ChatServerImpl;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AnnouncementController implements Initializable {
    private StageCoordinator stageCoordinator;
    private ChatServerImpl chatServer ;

    @FXML
    TextField announcementTextField;

    @FXML
    Button sendAnnouncementButton;

    @FXML
    Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageCoordinator = StageCoordinator.getInstance();
        try {
            chatServer = new ChatServerImpl();
        } catch (RemoteException e) {
            System.out.println("ChatServerImpl From Announcement Controller");
            e.printStackTrace();
        }
    }


    @FXML
    private void sendAnnouncement(){
        String announcementMessage = announcementTextField.getText();
        System.out.println("Send Announcement " + announcementMessage);
        chatServer.sendAnnouncementToAllOnlineUsers(announcementMessage);
    }

    @FXML
    private void backToServerHome(){
        System.out.println("Send backToServerHome");
        stageCoordinator.switchToHomeScene();
    }


}
