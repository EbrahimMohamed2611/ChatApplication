package eg.gov.iti.server.ui.controllers.loginControllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.server.db.dao.AdminDao;
import eg.gov.iti.server.db.dao.daoImpl.AdminDaoImp;
import eg.gov.iti.server.ui.helpers.ModelsFactory;
import eg.gov.iti.server.ui.models.AdminAuthentication;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServerLoginController implements Initializable {
    private StageCoordinator coordinator;
    private AdminDao adminDao;
    private AdminAuthentication adminAuthModel;
    private ModelsFactory modelsFactory;
    @FXML
    private JFXTextField adminPhoneNumberTextField;

    @FXML
    private Label phoneNumberValidationLabel;

    @FXML
    private JFXPasswordField adminPasswordTextField;

    @FXML
    private Label passwordValidationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinator = StageCoordinator.getInstance();
        modelsFactory = ModelsFactory.getInstance();
        adminAuthModel = modelsFactory.getAuthAdminModel();
        adminPhoneNumberTextField.textProperty().bindBidirectional(adminAuthModel.phoneNumberProperty());
        adminPasswordTextField.textProperty().bindBidirectional(adminAuthModel.passwordProperty());
    }

    @FXML
    void login() {
        try {
            adminDao = AdminDaoImp.getInstance();
            if (adminDao.isExisted(adminAuthModel.getPhoneNumber())) {
                phoneNumberValidationLabel.setVisible(false);
                if (adminDao.isPasswordValid(adminAuthModel.getPhoneNumber(), adminAuthModel.getPassword())) {
                    passwordValidationLabel.setVisible(false);
                    coordinator.switchToHomeScene();
                }else {
                    passwordValidationLabel.setVisible(true);
                }
            }else {
                phoneNumberValidationLabel.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
