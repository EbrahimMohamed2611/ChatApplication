package eg.gov.iti.contract.ui.controllers.messages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SenderMessageController implements Initializable {

    @FXML
    private Label senderNameLabel;
    @FXML
    private Label senderMessageBodyLabel;
    @FXML
    private Label senderTimeStampLabel;
    @FXML
    private  ImageView senderImgView ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getSenderNameLabel() {
        return senderNameLabel;
    }

    public void setSenderNameLabel(Label senderNameLabel) {
        this.senderNameLabel = senderNameLabel;
    }

    public Label getSenderMessageBodyLabel() {
        return senderMessageBodyLabel;
    }

    public void setSenderMessageBodyLabel(Label senderMessageBodyLabel) {
        this.senderMessageBodyLabel = senderMessageBodyLabel;
    }

    public Label getSenderTimeStampLabel() {
        return senderTimeStampLabel;
    }

    public void setSenderTimeStampLabel(Label senderTimeStampLabel) {
        this.senderTimeStampLabel = senderTimeStampLabel;
    }

    public ImageView getSenderImgView() {
        return senderImgView;
    }

    public void setSenderImgView(ImageView senderImgView) {
        this.senderImgView = senderImgView;
    }
}
