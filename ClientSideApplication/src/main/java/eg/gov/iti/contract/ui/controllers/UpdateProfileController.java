package eg.gov.iti.contract.ui.controllers;

import com.jfoenix.controls.*;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.CurrentUserAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.UpdateProfileServiceInterface;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
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
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
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
    private UpdateProfileServiceInterface updateProfileService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelsFactory= ModelsFactory.getInstance();
        currentUserModel=modelsFactory.getCurrentUserModel();

        updateProfileService= ServicesLocator.getUpdateProfileService();
        profilePic.fillProperty().bind(currentUserModel.getProfilePic().fillProperty());
        phoneTxtField.setText(currentUserModel.getPhoneNumber());
        pwdTxtField.setText(currentUserModel.getPassword());
        nameTxtField.setText(currentUserModel.getFullName());
        confirmPwdTxtField.setText(currentUserModel.getPassword());
        countryTxtField.setText(currentUserModel.getCountry());
        emailTxtField.setText(currentUserModel.getEmail());
        //todo image loading
        coordinator=StageCoordinator.getInstance();
    }
    @FXML
    private void selectPic()  {
        FileChooser fileChooser = new FileChooser();
        File file;
        file = fileChooser.showOpenDialog(null);
        try {
            currentUserModel.setImageEncoded(ImageConverter.getEncodedImage(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentUserModel.setProfileImage(new Image(file.toURI().toString()));
    }


    @FXML
    private void allowUpdate(ActionEvent event) {
        if(updateUserBtn.getText().equals("Update")){
            enableFields(true);

        }
        else if(updateUserBtn.getText().equals("Apply")){
            enableFields(false);
            try {
                currentUserModel.setPassword(pwdTxtField.getText());
                currentUserModel.setCountry(countryTxtField.getText());
                currentUserModel.setDateOfBirth(datePicker.getValue());
                currentUserModel.setFullName(nameTxtField.getText());
                currentUserModel.setEmail(emailTxtField.getText());
                currentUserModel.setBio(bioTxtArea.getText());
                System.out.println("in Apply");
                if(updateProfileService.updateProfile(CurrentUserAdapter.getUserDtoFromModelAdapter(currentUserModel))){
                    System.out.println("update ran successfully");
                }else {
                    System.out.println("update failed");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

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
    private void backToHomeScene(ActionEvent event) {
        if(backCancelBtn.getText().equals("Back")){
            enableFields(false);
            coordinator.switchToHomeScene();
        }
        else if(backCancelBtn.getText().equals("Cancel")){
            enableFields(false);
        }
    }
}
