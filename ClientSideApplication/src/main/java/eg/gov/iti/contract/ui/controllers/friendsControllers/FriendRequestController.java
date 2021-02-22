package eg.gov.iti.contract.ui.controllers.friendsControllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class FriendRequestController implements Initializable {
    @FXML
    AnchorPane requestAnchorPane;
    @FXML
    private Circle friendImage;
    @FXML
    private Label friendName;

    @FXML
    private JFXButton acceptButton;
    @FXML
    private JFXButton declineButton;

    public AnchorPane getRequestAnchorPane() {
        return requestAnchorPane;
    }

    public Circle getFriendImage() {
        return friendImage;
    }

    public Label getFriendName() {
        return friendName;
    }

    public JFXButton getAcceptButton() {
        return acceptButton;
    }

    public JFXButton getDeclineButton() {
        return declineButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
