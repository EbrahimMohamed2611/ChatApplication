package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXPasswordField;
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

public class SecondLoginController implements Initializable {
    ModelsFactory modelsFactory;
    UserAuthModel userAuthModel;
    StageCoordinator coordinator;
    LoginServiceInterface loginService;
    @FXML
    private JFXPasswordField passwordTxtField;
    @FXML
    private Label wrongPassword;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getAuthUserModel();
        passwordTxtField.textProperty().bindBidirectional(userAuthModel.passwordProperty());
        loginService = ServicesLocator.getLoginService();
    }

    @FXML
    void logIn(ActionEvent event) {
        try {
            if(loginService.checkPassword(UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))){
                wrongPassword.setText("");
                passwordTxtField.setUnFocusColor(Color.rgb(218, 228, 238));
                coordinator.switchToHomeScene();
            }
            else if(passwordTxtField.getText()==null || passwordTxtField.getText()=="" || passwordTxtField.getText().isEmpty()){
                wrongPassword.setText("Please add your password");
                passwordTxtField.setUnFocusColor(Color.RED);
            }

            else {
                wrongPassword.setText("Your password is wrong");
                passwordTxtField.setUnFocusColor(Color.RED);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void returnBack(ActionEvent event) {
        wrongPassword.setText("");
        passwordTxtField.setUnFocusColor(Color.rgb(218, 228, 238));
        passwordTxtField.setText("");
        coordinator.switchToFirstLoginScene();
    }

}
