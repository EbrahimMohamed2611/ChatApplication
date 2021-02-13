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

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FirstLoginController implements Initializable {
    ModelsFactory modelsFactory;
    UserAuthModel userAuthModel;

    @FXML
    private JFXTextField userPhoneNumberTxtField;

    StageCoordinator coordinator;
    LoginServiceInterface loginService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getCurrentUserModel();
        userPhoneNumberTxtField.textProperty().bindBidirectional(userAuthModel.phoneNumberProperty());
        loginService =ServicesLocator.getLoginService();
    }


    public void switchToSecondLoginScene() {

//        coordinator.switchToSecondLoginScene();

        try {
            if (loginService.checkPhoneNumber(
                    UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))){
                coordinator.switchToSecondLoginScene();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //(Boolean)serverLoginService.checkPhoneNumber(userAuthDto);
        //thread
    }


}
