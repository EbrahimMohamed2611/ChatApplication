package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserAuthAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserRegisterModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FirstLoginController implements Initializable {
    private ModelsFactory modelsFactory;
    private UserAuthModel userAuthModel;
    private UserRegisterModel userRegisterModel;

    @FXML
    private JFXTextField userPhoneNumberTxtField;

    private StageCoordinator coordinator;
    private LoginServiceInterface loginService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getAuthUserModel();
        userPhoneNumberTxtField.textProperty().bindBidirectional(userAuthModel.phoneNumberProperty());
        loginService =ServicesLocator.getLoginService();
        userRegisterModel=modelsFactory.getRegisterUserModel();
    }

    public void switchToSecondLoginScene() {
        coordinator.switchToSecondLoginScene();
        try {
            if (loginService.checkPhoneNumber(
                    UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))){
                coordinator.switchToSecondLoginScene();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToRegisterScene() {
        userRegisterModel.clear();
        coordinator.switchToSignupScene();
    }

}
