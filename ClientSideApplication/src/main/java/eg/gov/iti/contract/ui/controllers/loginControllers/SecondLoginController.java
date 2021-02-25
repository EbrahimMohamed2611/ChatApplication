package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.RegexValidator;
import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserAuthAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.helpers.Validator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SecondLoginController implements Initializable {
    private ModelsFactory modelsFactory;
    private UserAuthModel userAuthModel;
    private StageCoordinator coordinator;
    private LoginServiceInterface loginService;
    private ChatServerInterface chatService;
    private ChatClient client;
    private CachedCredentialsData credentialsData;
    private Validator validator;
    @FXML
    private JFXPasswordField passwordTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getAuthUserModel();
        passwordTxtField.textProperty().bindBidirectional(userAuthModel.passwordProperty());
        loginService = ServicesLocator.getLoginService();
        chatService = ServicesLocator.getChatServerInterface();
        client = ChatClientImpl.getInstance();
        credentialsData = CachedCredentialsData.getInstance();
        validator = new Validator();
        passwordTxtField.clear();
        validator.loginPasswordValidator(passwordTxtField);
        var regexValidator = passwordTxtField.getValidators().get(1);
        if (regexValidator instanceof RegexValidator) {
            ((RegexValidator) regexValidator).setRegexPattern(".*");
            regexValidator.setMessage("Incorrect Password");
        }
    }

    @FXML
    void logIn() {
        try {
            var regexValidator = passwordTxtField.getValidators().get(1);
            if (loginService.checkPassword(UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))) {
                ((RegexValidator) regexValidator).setRegexPattern(".*");
                chatService.register(client);
                credentialsData.saveCredentials(userAuthModel);
                coordinator.switchToHomeScene();
                passwordTxtField.clear();
            } else {
                ((RegexValidator) regexValidator).setRegexPattern("");
            }
            passwordTxtField.validate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void returnBack() {
        coordinator.switchToFirstLoginScene();
    }
}
