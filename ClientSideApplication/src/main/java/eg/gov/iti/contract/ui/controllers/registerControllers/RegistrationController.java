package eg.gov.iti.contract.ui.controllers.registerControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserRegAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.RegisterServiceInterface;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.helpers.Validator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserRegisterModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class RegistrationController implements Initializable {

    private ModelsFactory modelsFactory;
    private StageCoordinator coordinator;
    private UserRegisterModel userRegisterModel;
    private UserAuthModel userAuthModel;

    @FXML
    private JFXTextField nameText;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXDatePicker birthText;
    @FXML
    private JFXTextField emailText;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXPasswordField passwordText;
    @FXML
    private JFXPasswordField confirmText;
    @FXML
    private RadioButton male;


    private boolean checkConfirmPass = false;
    private RegisterServiceInterface registerService;
    private Validator validator;
    boolean isValidUserName = false;
    boolean isValidPhoneNumber = false;
    boolean isValidEmail = false;
    boolean isValidPassword = false;
    boolean isValidConfirmPassword = false;

    public String getGender() {
        if (male.isSelected()) {
            return "MALE";
        } else {
            return "FEMALE";
        }

    }

    private boolean isDataValid() {
        return (isValidUserName && isValidEmail && isValidPhoneNumber && isValidPassword && isValidConfirmPassword);
    }

    @FXML
    private void backToFirstSign() {
        userRegisterModel.clear();
        confirmText.clear();
        coordinator.switchToFirstLoginScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userRegisterModel = modelsFactory.getRegisterUserModel();
        userAuthModel = modelsFactory.getAuthUserModel();
        validator = new Validator();
        phoneText.textProperty().bindBidirectional(userRegisterModel.phoneNumberProperty());
        emailText.textProperty().bindBidirectional(userRegisterModel.emailProperty());
        passwordText.textProperty().bindBidirectional(userRegisterModel.passwordProperty());
        nameText.textProperty().bindBidirectional(userRegisterModel.fullNameProperty());
        validator.nameValidator(nameText);
        validator.phoneValidator(phoneText);
        validator.emailValidator(emailText);
        validator.passwordValidator(passwordText);
        validator.confirmPasswordValidator(confirmText);
        phoneText.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                try {
                    if (registerService.checkPhoneNumber(userRegisterModel.getPhoneNumber())) {
                        var regexValidator = phoneText.getValidators().get(2);
                        if (regexValidator instanceof RegexValidator) {
                            ((RegexValidator) regexValidator).setRegexPattern("!" + userRegisterModel.getPhoneNumber());
                        }
                    } else {
                        var regexValidator = phoneText.getValidators().get(2);
                        if (regexValidator instanceof RegexValidator) {
                            ((RegexValidator) regexValidator).setRegexPattern(".*");
                        }
                    }
                    isValidPhoneNumber = phoneText.validate();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }


            }
        });
        nameText.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                isValidUserName = nameText.validate();

            }
        });
        emailText.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                isValidEmail = emailText.validate();

            }
        });
        passwordText.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                isValidPassword = passwordText.validate();

            }
        });
        confirmText.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                var regexValidator = confirmText.getValidators().get(1);
                if (regexValidator instanceof RegexValidator) {
                    ((RegexValidator) regexValidator).setRegexPattern(passwordText.getText());
                }
                isValidConfirmPassword = confirmText.validate();

            }
        });
        male.setSelected(true);
        registerService = ServicesLocator.getRegisterService();
        birthText.setValue(LocalDate.of(1997, 3, 13));

        registerButton.setOnAction((event) -> {

            if (isDataValid()) {
                userRegisterModel.setDateOfBirth(birthText.getValue().toString());
                userRegisterModel.setUserGender(getGender());
                userRegisterModel.setStatus("AWAY");
                try {
                    if (registerService.addNewUser(UserRegAdapter.getUserDtoFromModelAdapter(userRegisterModel))) {
                        userAuthModel.setPhoneNumber(userRegisterModel.getPhoneNumber());
                        userAuthModel.setPassword("");
                        userRegisterModel.clear();
                        coordinator.switchToSecondLoginScene();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
