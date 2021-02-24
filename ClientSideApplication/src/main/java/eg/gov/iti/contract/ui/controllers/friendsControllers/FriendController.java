package eg.gov.iti.contract.ui.controllers.friendsControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class FriendController implements Initializable {
    @FXML
    AnchorPane requestAnchorPane;
    @FXML
    private Circle friendImage;
    @FXML
    private Label friendName;
    @FXML
    private Label phoneNumber;
    @FXML
    private Circle status;

    public AnchorPane getRequestAnchorPane() {
        return requestAnchorPane;
    }

    public Circle getFriendImage() {
        return friendImage;
    }

    public Label getFriendName() {
        return friendName;
    }

    public Label getPhoneNumber() {
        return phoneNumber;
    }

    public Circle getStatus() {
        return status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
