package eg.gov.iti.contract.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstLoginController implements Initializable {
    ModelsFactory modelsFactory;
    UserAuthModel userAuthModel;

    @FXML
    private JFXTextField userPhoneNumberTxtField;

    StageCoordinator coordinator;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator =StageCoordinator.getInstance();
        modelsFactory =ModelsFactory.getInstance();
        userAuthModel= modelsFactory.getCurrentUserModel();
        userPhoneNumberTxtField.textProperty().bindBidirectional(userAuthModel.phoneNumberProperty());

    }


    public void switchToSecondLoginScene() {
        coordinator.switchToSecondLoginScene();

        //(Boolean)serverLoginService.checkPhoneNumber(userAuthDto);
        //thread
    }


}
