package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserAuthAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FirstLoginController implements Initializable {
    ModelsFactory modelsFactory;
    UserAuthModel userAuthModel;

    @FXML
    private Label wrongPhone;
    @FXML
    private JFXTextField userPhoneNumberTxtField;

    StageCoordinator coordinator;
    LoginServiceInterface loginService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getAuthUserModel();
        userPhoneNumberTxtField.textProperty().bindBidirectional(userAuthModel.phoneNumberProperty());
        loginService =ServicesLocator.getLoginService();
    }

    @FXML
    void createNewAcc(ActionEvent event) {
        coordinator.switchToSignupScene();
    }


    public void switchToSecondLoginScene() {
      //  coordinator.switchToSecondLoginScene();

//        coordinator.switchToSecondLoginScene();

        try {
            if(phoneValidation()){
                if (loginService.checkPhoneNumber(
                        UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))){
                    wrongPhone.setText("");
                    userPhoneNumberTxtField.setUnFocusColor(Color.rgb(218, 228, 238));
                    coordinator.switchToSecondLoginScene();
                }
                else {
                    wrongPhone.setText("Wrong Phone Number");
                    userPhoneNumberTxtField.setUnFocusColor(Color.RED);
                }}
            else {
                wrongPhone.setText("please add a valid phone number");
                userPhoneNumberTxtField.setUnFocusColor(Color.RED);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //(Boolean)serverLoginService.checkPhoneNumber(userAuthDto);
        //thread
    }


    public boolean phoneValidation() {
        return userPhoneNumberTxtField.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$");
    }



}
