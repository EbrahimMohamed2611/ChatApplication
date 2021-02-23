package eg.gov.iti.contract.ui.controllers;

import com.jfoenix.controls.JFXSpinner;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.helpers.Validator;
import eg.gov.iti.contract.ui.models.ConnectionModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectToServerController implements Initializable {
    private StageCoordinator coordinator;
    private CachedCredentialsData cachedCredentialsData;
    @FXML
    private Label invalidIpLabel;
    @FXML
    private TextField textField;
//    @FXML
//    private JFXSpinner serverSpinner;

    private ConnectionModel connectionModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coordinator = StageCoordinator.getInstance();
        connectionModel = ModelsFactory.getInstance().getConnectionModel();
    }

    @FXML
    private void connectToServer () {

//        serverSpinner.setVisible(true);
        String serverIp = textField.getText();
        if (!serverIp.equals("localhost") && !Validator.checkIP(serverIp)) {
            System.out.println("Ip : " + serverIp);
            invalidIpLabel.setVisible(true);
//            serverSpinner.setVisible(false);
            return;
        }
        ServicesLocator.servicesInit(serverIp);
        cachedCredentialsData = CachedCredentialsData.getInstance();
//        serverSpinner.setVisible(false);
        if (cachedCredentialsData.validateCredentials())
            coordinator.switchToHomeScene();
        else
            coordinator.switchToFirstLoginScene();

    }





}
