package eg.gov.iti.contract.ui.controllers;

import com.jfoenix.controls.*;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.CurrentUserModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateProfileController implements Initializable {

    @FXML
    private Circle profilePic;

    @FXML
    private JFXTextField phoneTxtField;

    @FXML
    private JFXTextField nameTxtField;

    @FXML
    private JFXTextField emailTxtField;

    @FXML
    private JFXPasswordField pwdTxtField;

    @FXML
    private JFXPasswordField confirmPwdTxtField;

    @FXML
    private JFXButton choosePhotoBtn;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTextArea bioTxtArea;

    @FXML
    private JFXButton updateUserBtn;

    @FXML
    private JFXButton backCancelBtn;
    @FXML
    private JFXTextField countryTxtField;

    private StageCoordinator coordinator;
    private CurrentUserModel currentUserModel;
    private ModelsFactory modelsFactory;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelsFactory= ModelsFactory.getInstance();
        currentUserModel=modelsFactory.getCurrentUserModel();
        profilePic.setFill(new ImagePattern(new Image("pictures/close-button.png")));
//        FontIcon addIcon=new FontIcon("mdi2p-plus-thick");
//        addIcon.setIconSize(22);
//        addIcon.setIconColor(Color.WHITE);
//        choosePhotoBtn.setGraphic(addIcon);
        currentUserModel.phoneNumberProperty().bindBidirectional(phoneTxtField.textProperty());
        currentUserModel.fullNameProperty().bindBidirectional(nameTxtField.textProperty());
        currentUserModel.emailProperty().bindBidirectional(emailTxtField.textProperty());
        currentUserModel.passwordProperty().bindBidirectional(pwdTxtField.textProperty());
        currentUserModel.passwordProperty().bindBidirectional(confirmPwdTxtField.textProperty());
        currentUserModel.countryProperty().bindBidirectional(countryTxtField.textProperty());
        currentUserModel.bioProperty().bindBidirectional(bioTxtArea.textProperty());
        currentUserModel.dateOfBirthProperty().bindBidirectional(datePicker.valueProperty());
        //todo image loading
//        currentUserModel.imageEncodedProperty().bindBidirectional();
        coordinator=StageCoordinator.getInstance();
    }

    @FXML
    void allowUpdate(ActionEvent event) {
        if(updateUserBtn.getText().equals("Update")){
            enableFields(true);

        }
        else if(updateUserBtn.getText().equals("Apply")){
            enableFields(false);
        }
    }

    private void enableFields(boolean state) {
        nameTxtField.setEditable(state);
        emailTxtField.setEditable(state);
        pwdTxtField.setEditable(state);
        confirmPwdTxtField.setEditable(state);
        choosePhotoBtn.setDisable(!state);
        bioTxtArea.setEditable(state);
        countryTxtField.setEditable(state);
//        nameTxtField.setLabelFloat(state);
//        emailTxtField.setLabelFloat(state);
//        pwdTxtField.setLabelFloat(state);
//        confirmPwdTxtField.setLabelFloat(state);
////        datePicker.setLabelFloat(state);
//        bioTxtArea.setLabelFloat(state);

        if (state) {
            updateUserBtn.setText("Apply");
            backCancelBtn.setText("Cancel");
        }
        else{
            updateUserBtn.setText("Update");
            backCancelBtn.setText("Back");
        }
    }

    @FXML
    void backToHomeScene(ActionEvent event) {
        if(backCancelBtn.getText().equals("Back")){
            enableFields(false);
            coordinator.switchToHomeScene();
        }
        else if(backCancelBtn.getText().equals("Cancel")){
            enableFields(false);
        }
    }
}
