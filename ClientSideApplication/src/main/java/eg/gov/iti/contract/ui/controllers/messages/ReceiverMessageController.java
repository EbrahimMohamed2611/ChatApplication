package eg.gov.iti.contract.ui.controllers.messages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceiverMessageController implements Initializable {

    @FXML
    private Label receiverNameLabel;
    @FXML
    private Label receiverMessageBodyLabel;
    @FXML
    private Label receiverTimeStampLabel;
    @FXML
    private ImageView receiverImgView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getReceiverNameLabel() {
        return receiverNameLabel;
    }

    public void setReceiverNameLabel(Label receiverNameLabel) {
        this.receiverNameLabel = receiverNameLabel;
    }

    public Label getReceiverMessageBodyLabel() {
        return receiverMessageBodyLabel;
    }

    public void setReceiverMessageBodyLabel(Label receiverMessageBodyLabel) {
        this.receiverMessageBodyLabel = receiverMessageBodyLabel;
    }

    public Label getReceiverTimeStampLabel() {
        return receiverTimeStampLabel;
    }

    public void setReceiverTimeStampLabel(Label receiverTimeStampLabel) {
        this.receiverTimeStampLabel = receiverTimeStampLabel;
    }

    public ImageView getReceiverImgView() {
        return receiverImgView;
    }

    public void setReceiverImgView(ImageView receiverImgView) {
        this.receiverImgView = receiverImgView;
    }


}
