package eg.gov.iti.server.ui.controllers;

import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    private StageCoordinator stageCoordinator;

    @FXML
    VBox announcementVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageCoordinator = StageCoordinator.getInstance();
    }

    @FXML
    private void enterToAnnouncementPage(){
        System.out.println("Enter To Announcement");
        stageCoordinator.switchToAnnouncement();
    }

    @FXML
    private void enterToAdministrators(){
        System.out.println("Enter  To Administrators");
        stageCoordinator.switchToAdministratorInformation();
    }


}
