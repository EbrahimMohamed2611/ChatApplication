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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServerLoginController implements Initializable {
    StageCoordinator coordinator;
    AdminDao adminDao;
    AdminAuthentication adminAuthModel;
    ModelsFactory modelsFactory;
    @FXML
    private JFXTextField adminPhoneNumberTextField;
    @FXML
    private JFXPasswordField adminPasswordTextField;

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
                if (adminDao.isPasswordValid(adminAuthModel.getPhoneNumber(), adminAuthModel.getPassword())) {
                    coordinator.switchToHomeScene();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
