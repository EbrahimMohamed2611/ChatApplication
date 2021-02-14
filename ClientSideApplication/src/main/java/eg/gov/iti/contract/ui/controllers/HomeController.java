package eg.gov.iti.contract.ui.controllers;

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
    private StageCoordinator coordinator;
    LogoutServiceInterface logoutService;
    ChatClient client;
    ChatServerInterface chatService;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinator = StageCoordinator.getInstance();
        logoutService = ServicesLocator.getLogoutService();
        client = ChatClientImpl.getInstance();
        chatService = ServicesLocator.getChatServerInterface();
    }
}
