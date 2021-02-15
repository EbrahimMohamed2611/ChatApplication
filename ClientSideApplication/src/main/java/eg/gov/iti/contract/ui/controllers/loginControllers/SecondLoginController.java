package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXPasswordField;
import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.net.ChatClientImpl;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserAuthAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class SecondLoginController implements Initializable {
    ModelsFactory modelsFactory;
    UserAuthModel userAuthModel;
    StageCoordinator coordinator;
    LoginServiceInterface loginService;
    ChatServerInterface chatService;
    ChatClient client;
    CachedCredentialsData credentialsData;
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

        passwordTxtField.clear();

        client = ChatClientImpl.getInstance();
    }
    @FXML
    void logIn() {
        try {
            if(loginService.checkPassword(UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))){
                chatService.register(client);

                credentialsData = CachedCredentialsData.getInstance();
                credentialsData.saveCredentials(userAuthModel);

                coordinator.switchToHomeScene();
                passwordTxtField.clear();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void returnBack() {
        coordinator.switchToFirstLoginScene();
    }

}
