package eg.gov.iti.server.ui.controllers;

import com.jfoenix.controls.JFXButton;
import eg.gov.iti.server.net.serverConfiguration.ServicesAssigner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    @FXML
    JFXButton ServerP;

//    private Registry registry;

    public HomeViewController() throws RemoteException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            ServicesAssigner.getInstance().initConnection();


                ServerP.setOnAction(event -> {

                    if (ServerP.getText()=="Service stopped") {

                        ServerP.setText("Service started");
                        try {
                            ServicesAssigner.getInstance().startConnection();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            ServicesAssigner.getInstance().stopServer();
                            ServerP.setText("Service stopped");

                            System.out.println("server now is off");


                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

//        }
                    }
                });



    }
}


