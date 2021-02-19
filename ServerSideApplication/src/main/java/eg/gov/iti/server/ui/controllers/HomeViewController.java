package eg.gov.iti.server.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.net.serverConfiguration.ServicesAssigner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    private PieChart genderChart = new PieChart();
    private PieChart statusChart = new PieChart();
    XYChart.Series dataSeries1 = new XYChart.Series();
    ObservableList<PieChart.Data> gender;
    ObservableList<PieChart.Data> on_off;
   // XYChart.Series countryChart = new XYChart.Series();
    private BarChart mychart = new BarChart(xAxis, yAxis);



    @FXML
    JFXButton ServerP;

//    private Registry registry;

    public HomeViewController() throws RemoteException {
    }

    @FXML
    public void Statistics() {
        if (!genderS.isVisible() && !lineS.isVisible() && !countryS.isVisible()) {

            genderS.setVisible(true);
            lineS.setVisible(true);
            countryS.setVisible(true);
        } else {
            genderS.setVisible(false);
            lineS.setVisible(false);
            countryS.setVisible(false);
        }
    }

    @FXML
    JFXRadioButton genderS;
    @FXML
    JFXRadioButton lineS;
    @FXML
    JFXRadioButton countryS;
    @FXML
    private AnchorPane View;

    private void getGenderData() {
        int countmale = 0;
        int countfemale = 0;
        try {
            UserDao userDao = UserDaoImpl.getInstance();
            ResultSet rs2 = userDao.getAllByGender();
            rs2.next();
            countfemale = rs2.getInt("Gender_Count");
            rs2.next();
            countmale = rs2.getInt("Gender_Count");

        } catch (Exception e) {
            e.printStackTrace();
        }
        gender = FXCollections.observableArrayList(

                new PieChart.Data("male", countmale),
                new PieChart.Data("Female", countfemale));

        genderChart.setData(gender);

    }

    private void getCountryData() {

        try {
            UserDao userDao = UserDaoImpl.getInstance();
            ResultSet rs1 = userDao.getAllByCountry();
            dataSeries1.getData().clear();
            mychart.getData().clear();

            while (rs1.next()) {
                dataSeries1.getData().add(new XYChart.Data<>(rs1.getString("country"), rs1.getInt("Country_Count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mychart.getData().add(dataSeries1);
        }
        catch (Exception e ){
            e.printStackTrace();
        }

    }
    private void getStatusData() {

        int countAvailable = 0;
        int countBusy = 0;
        int countAway = 0;
        try {
            UserDao userDao = UserDaoImpl.getInstance();
            ResultSet rs1 = userDao.getAllOnOff();
            rs1.next();
            countAvailable = rs1.getInt("On/offline_Count");
            rs1.next();
            countBusy = rs1.getInt("On/offline_Count");
            rs1.next();
            countAway = rs1.getInt("On/offline_Count");

        } catch (Exception e) {
            e.printStackTrace();
        }
        on_off = FXCollections.observableArrayList(

                new PieChart.Data("Available", countAvailable),
                new PieChart.Data("Busy", countBusy),
                new PieChart.Data("Away", countAway));



        statusChart.setData(on_off);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderS.setVisible(false);
        lineS.setVisible(false);
        countryS.setVisible(false);



        ObservableList<BarChart.Data> gender;


        ServicesAssigner.getInstance().initConnection();


        ServerP.setOnAction(event -> {


            if (ServerP.getText() == "Service stopped") {

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

        genderS.setOnAction(event -> {

            try {
                View.getChildren().clear();
            } catch (Exception e) {
            }

            getGenderData();
            View.getChildren().add(genderChart);

        });

        countryS.setOnAction(event -> {

            try {
                View.getChildren().clear();
            } catch (Exception e) {
            }

            getCountryData();
            View.getChildren().add(mychart);

        });

        lineS.setOnAction(event -> {

            try {
                View.getChildren().clear();
            } catch (Exception e) {
            }

            getStatusData();
            View.getChildren().addAll(statusChart);

        });


    }
}


