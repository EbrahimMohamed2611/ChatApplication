package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserAuthAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.helpers.Validator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserRegisterModel;
import javafx.application.Platform;
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
    private Validator validator;
    private boolean isValidPhoneNumber = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getAuthUserModel();
        userPhoneNumberTxtField.textProperty().bindBidirectional(userAuthModel.phoneNumberProperty());
        loginService = ServicesLocator.getLoginService();
        userRegisterModel = modelsFactory.getRegisterUserModel();
        validator = new Validator();
        validator.phoneValidator(userPhoneNumberTxtField);
        var regexValidator = userPhoneNumberTxtField.getValidators().get(2);
        if (regexValidator instanceof RegexValidator) {
            ((RegexValidator) regexValidator).setRegexPattern(".*");
            regexValidator.setMessage("Phone Number doesn't exist.");
        }

        userPhoneNumberTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                try {
                    if (loginService.checkPhoneNumber(
                            UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))) {
                        if (regexValidator instanceof RegexValidator) {
                            ((RegexValidator) regexValidator).setRegexPattern(".*");
                        }
                    } else {
                        if (regexValidator instanceof RegexValidator) {
                            ((RegexValidator) regexValidator).setRegexPattern("");
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                isValidPhoneNumber = userPhoneNumberTxtField.validate();
            }
        });
    }
    public void switchToSecondLoginScene() {
        if (isValidPhoneNumber) {
            coordinator.switchToSecondLoginScene();
        }
    }

    @FXML
    private void switchToRegisterScene() {
        userRegisterModel.clear();
        coordinator.switchToSignupScene();
    }

}
