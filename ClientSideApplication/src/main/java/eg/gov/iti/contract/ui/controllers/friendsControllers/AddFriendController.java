package eg.gov.iti.contract.ui.controllers.friendsControllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class AddFriendController {

    @FXML
    private AnchorPane requestAnchorPane;

    @FXML
    private Circle friendImage;

    @FXML
    private Label friendName;

    @FXML
    private JFXButton addButton;

    public AnchorPane getRequestAnchorPane() {
        return requestAnchorPane;
    }

    public Circle getFriendImage() {
        return friendImage;
    }

    public Label getFriendName() {
        return friendName;
    }

    public JFXButton getAddButton() {
        return addButton;
    }
}
