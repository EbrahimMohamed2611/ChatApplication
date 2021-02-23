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
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    private PieChart genderChart = new PieChart();
    private PieChart statusChart = new PieChart();
    XYChart.Series dataSeries1 = new XYChart.Series();
    ObservableList<PieChart.Data> status;
    ObservableList<PieChart.Data> on_off;
   // XYChart.Series countryChart = new XYChart.Series();
    private BarChart mychart = new BarChart(xAxis, yAxis);


    @FXML
    Label ChartLbl;


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
boolean on;
    @FXML
    public void PowerBtn() {

        if (on == false) {
            try {
                ServicesAssigner.getInstance().startConnection();
                on=true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ServicesAssigner.getInstance().stopServer();
                on=false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

//        }
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
        int count[] = {0, 0};
        int i = 0;
        String gen[] = {null, null};

        try {
            UserDao userDao = UserDaoImpl.getInstance();
            ResultSet rs2 = userDao.getAllByGender();
            while (rs2.next()) {

                count[i] = rs2.getInt("Gender_Count");
                gen[i] = (rs2.getObject("gender")).toString();
                i++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PieChart.Data data0 = new PieChart.Data(gen[0], count[0]);
        status = FXCollections.observableArrayList(data0);

        if (count[0] != 0) {
            PieChart.Data data = new PieChart.Data(gen[1], count[1]);
            status.add(data);

        }
        genderChart.setData(status);

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

        int count[] = {0, 0,0};
        int i = 0;
        String st[] = {null, null,null};

        try {
            UserDao userDao = UserDaoImpl.getInstance();
            ResultSet rs2 = userDao.getAllOnOff();
            while (rs2.next()) {

                count[i] = rs2.getInt("On/offline_Count");
                st[i] = (rs2.getObject("status")).toString();
                i++;

            } }catch (Exception e) {
            e.printStackTrace();
        }
        PieChart.Data data0 = new PieChart.Data(st[0], count[0]);
        status = FXCollections.observableArrayList(data0);

        if (count[1] != 0) {
            PieChart.Data data = new PieChart.Data(st[1], count[1]);
            status.add(data);

        }
        if (count[2] != 0) {
            PieChart.Data data = new PieChart.Data(st[2], count[2]);
            status.add(data);

        }
        statusChart.setData(status);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderS.setVisible(false);
        lineS.setVisible(false);
        countryS.setVisible(false);



        ObservableList<BarChart.Data> gender;


        ServicesAssigner.getInstance().initConnection();

//
//        ServerP.setOnAction(event -> {
//
//
//            if (ServerP.getText() == "Service stopped") {
//
//                ServerP.setText("Service started");
//                try {
//                    ServicesAssigner.getInstance().startConnection();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    ServicesAssigner.getInstance().stopServer();
//                    ServerP.setText("Service stopped");
//
//                    System.out.println("server now is off");
//
//
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//
////        }
//            }
//        });

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


