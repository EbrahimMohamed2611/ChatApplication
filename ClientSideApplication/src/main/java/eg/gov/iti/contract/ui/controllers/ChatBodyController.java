package eg.gov.iti.contract.ui.controllers;

import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LogoutServiceInterface;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ChatBodyController implements Initializable {
    private StageCoordinator coordinator;
    LogoutServiceInterface logoutService;

    @FXML
    void logout(ActionEvent event) {
        try {
            // todo send chatClient
            if (logoutService.clearCachedData()) {
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
    }
}
