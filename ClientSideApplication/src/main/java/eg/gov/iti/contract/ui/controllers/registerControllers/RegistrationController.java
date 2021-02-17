package eg.gov.iti.contract.ui.controllers.registerControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserRegAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.RegisterServiceInterface;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserRegisterModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;


public class RegistrationController implements Initializable {

    private ModelsFactory modelsFactory;
    private StageCoordinator coordinator;
    private UserRegisterModel userRegisterModel;
    private UserAuthModel userAuthModel;

    @FXML
    private JFXTextField  nameText;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXDatePicker birthText ;
    @FXML
    private JFXTextField  emailText;
    @FXML
    private JFXButton registerButton;
    @FXML
    private Label wrongPhone;
    @FXML
    private Label wrongUserName;
    @FXML
    private Label wrongEmail;
    @FXML
    private Label wrongPassword;
    @FXML
    private Label wrongConfirm;
    @FXML
    private  Label DateCheck;
    @FXML
    private  Label GenderCheck;
    @FXML
    private JFXPasswordField passwordText;
    @FXML
    private JFXPasswordField confirmText;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;


    private boolean checkConfirmPass=false;
    UserRegisterModel newUser;
    RegisterServiceInterface registerService;


    public String genderDetermination(){
        if(male.isSelected()){return "MALE";}
        else
        {return "FEMALE";}

    }

    @FXML
    public boolean phoneValidation() {
        if (phoneText.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$")) {

            wrongPhone.setText("");
            phoneText.setUnFocusColor(Color.rgb(218, 228, 238));

        } else {

            wrongPhone.setText("Please add right phone number");
            phoneText.setUnFocusColor(Color.RED);

        }
        return phoneText.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$");
    }



    @FXML
    public boolean userNameValidation() {

        String txt1 = nameText.getText();

        String txt =txt1.replaceAll("\\s+", " ");

        if ((txt.isEmpty() ||txt != " ") && txt.matches("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$")) {
            wrongUserName.setText("");
            nameText.setUnFocusColor(Color.rgb(218, 228, 238));
        } else {
            wrongUserName.setText("Please add a valid user name");
            nameText.setUnFocusColor(Color.RED);
        }

        return ((txt.isEmpty() ||txt != " ") && txt.matches("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$"));

    }

    @FXML
    public boolean validateEmail() {
        if (!emailText.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            wrongEmail.setText("E-mail Is Not Valid");
            emailText.setUnFocusColor(Color.RED);


        } else {
            wrongEmail.setText("");
            emailText.setUnFocusColor(Color.rgb(218, 228, 238));

        }
        return emailText.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }


    @FXML
    public boolean strongPassword() {
        if (!passwordText.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$")) {
            wrongPassword.setText("Please add a strong password");
            passwordText.setUnFocusColor(Color.RED);
        } else {
            wrongPassword.setText("");
            passwordText.setUnFocusColor(Color.rgb(218, 228, 238));

        }
        return passwordText.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$");
    }


    public boolean validatePasswordMatch() {

        if(strongPassword()){

            if (confirmText.getText().equals(passwordText.getText())) {
                checkConfirmPass = true;
                confirmText.setUnFocusColor(Color.rgb(218, 228, 238));
                wrongConfirm.setText("");
            } else {
                wrongConfirm.setText("Please add right confirmation password");
                checkConfirmPass = false;
                confirmText.setUnFocusColor(Color.RED);
            }

        }return checkConfirmPass;}

    public boolean genderSelected() {
        if (male.isSelected() || female.isSelected()) {
            GenderCheck.setText("");
            return true;
        } else {
            GenderCheck.setText("Please select your gender");
            return false;
        }
    }

    public boolean dateSelected() {
        if (birthText.getValue() != null) {
            DateCheck.setText("");
            return true;
        } else {
            DateCheck.setText("Please add your birth date");
            return false;
        }
    }


    public boolean userDataValid() {


        return phoneValidation() && userNameValidation() && validateEmail() && validateEmail() && validatePasswordMatch() && strongPassword() && genderSelected() && dateSelected();

    }
    public void register()  {

        if (userDataValid()) {
            System.out.println("success");
        }}


    public void register(UserRegisterModel newUser) {

        if (userDataValid()) {
            newUser.setFullName(nameText.getText());
            newUser.setEmail(emailText.getText());
            newUser.setPassword(passwordText.getText());
            newUser.setPhoneNumber(phoneText.getText());
            newUser.setUserGender(genderDetermination());
            //todo date toString()
            newUser.setDateOfBirth(birthText.getValue().toString());
            System.out.println(newUser);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        userRegisterModel = modelsFactory.getRegisterUserModel();
        userAuthModel = modelsFactory.getAuthUserModel();
        phoneText.textProperty().bindBidirectional(userRegisterModel.phoneNumberProperty());
        emailText.textProperty().bindBidirectional(userRegisterModel.emailProperty());
        passwordText.textProperty().bindBidirectional(userRegisterModel.passwordProperty());
        nameText.textProperty().bindBidirectional(userRegisterModel.fullNameProperty());

//        nameText.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
//            if(!t1){
//                nameText.validate();
//            }
//        });


        registerService = ServicesLocator.getRegisterService();


        registerButton.setOnAction((event) -> {


            try {

                register();
                newUser = new UserRegisterModel();
                register(newUser);

                if(male.isSelected()){
                    userRegisterModel.setUserGender("MALE");
                }else {

                    userRegisterModel.setUserGender("FEMALE");
                }
                //todo refactor
                java.util.Date date = java.util.Date.from(birthText.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                userRegisterModel.setDateOfBirth(sqlDate.toString());


                userRegisterModel.setStatus("AVAILABLE");


                System.out.println(userRegisterModel.getStatus());
                if (registerService.addNewUser(
                        UserRegAdapter.getUserDtoFromModelAdapter(userRegisterModel))) {
                    userAuthModel.setPhoneNumber(userRegisterModel.getPhoneNumber());
                    userAuthModel.setPassword("");
                    coordinator.switchToSecondLoginScene();
                    wrongPhone.setText("");

                }
                else {
                    wrongPhone.setText("This phone is existed");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }



        });
//        meth();
    }
//    void meth(){
//        RegexValidator regexValidator =new RegexValidator();
//        regexValidator.setRegexPattern("");
//        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
//        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
//        requiredFieldValidator.setMessage("fuck jets");
//        nameText.getValidators().addAll(requiredFieldValidator,regexValidator);
//
//    }

}
