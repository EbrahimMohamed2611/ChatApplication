package eg.gov.iti.contract.ui.controllers.friendsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class FriendController {
    @FXML
    AnchorPane requestAnchorPane;
    @FXML
    private Circle friendImage;
    @FXML
    private Label friendName;

    public AnchorPane getRequestAnchorPane() {
        return requestAnchorPane;
    }

    public Circle getFriendImage() {
        return friendImage;
    }

    public Label getFriendName() {
        return friendName;
    }
}
