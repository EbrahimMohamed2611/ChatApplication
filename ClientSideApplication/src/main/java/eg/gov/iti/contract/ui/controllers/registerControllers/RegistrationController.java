package eg.gov.iti.contract.ui.controllers.registerControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
//    private UserDao userDao;
    @FXML
    private JFXTextField phoneNumberTextField;

    @FXML
    private JFXTextField userNameTextField;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXTextField passwordTextField;

    @FXML
    private JFXTextField confirmPasswordTextField;

    @FXML
    private VBox genderRadioButton;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXDatePicker dateOfBirthDatePicker;

    @FXML
    private JFXButton nextButton;

    @FXML
    void signUp(ActionEvent event) {
//        try {
//            User user = new User("01005425354", "ArabieIbrahim", "1234", "email@dfd.com", "Egypt", Date.valueOf("1997-02-25"), Gender.MALE, "", Status.AVAILABLE);
//            userDao.save(user);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        userDao = new UserDaoImpl();
    }
}
