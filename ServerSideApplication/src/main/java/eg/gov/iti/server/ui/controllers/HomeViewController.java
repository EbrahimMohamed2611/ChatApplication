package eg.gov.iti.server.ui.controllers;

import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    private StageCoordinator coordinator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();

    }
    @FXML
    public void userGo (){
        coordinator.switchToUsersView();
    }
}
