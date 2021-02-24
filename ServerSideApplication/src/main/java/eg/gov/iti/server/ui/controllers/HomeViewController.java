package eg.gov.iti.server.ui.controllers;

import com.jfoenix.controls.JFXRadioButton;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.net.serverConfiguration.ServicesAssigner;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {

    private Notifications notificationBuilder;
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    XYChart.Series dataSeries1 = new XYChart.Series();
    ObservableList<PieChart.Data> status;
    ObservableList<PieChart.Data> Gender;
    private Node graphic;


    private StageCoordinator stageCoordinator;
    @FXML
    Label ChartLbl;
    @FXML
    JFXRadioButton genderS;
    @FXML
    JFXRadioButton lineS;
    @FXML
    JFXRadioButton countryS;
    @FXML
    private AnchorPane View;


    private void getCountryData() {
        BarChart countryChart = new BarChart(xAxis, yAxis);

        try {
            UserDao userDao = UserDaoImpl.getInstance();
            ResultSet rs1 = userDao.getAllByCountry();
            dataSeries1.getData().clear();
            countryChart.getData().clear();

            while (rs1.next()) {
                dataSeries1.getData().add(new XYChart.Data<>(rs1.getString("country"), rs1.getInt("Country_Count")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            countryChart.getData().add(dataSeries1);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        View.getChildren().add(countryChart);
        ChartLbl.setText("");

    }

    private void getStatusData() {
        PieChart statusChart = new PieChart();
        View.getChildren().clear();
        int count[] = {0, 0,0};
        int i = 0;
        String st[] = {null, null,null};

        try {
            statusChart.getData().clear();
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
        statusChart.setLabelLineLength(20);
        View.getChildren().addAll(statusChart);
        ChartLbl.setText("No of Busy : "+count[0]+"\n"+
                "No of Available : "+count[1]+"\n"+
                "No of Away : "+count[2]);

        ChartLbl.setWrapText(true);

    }

    private void getGenderData() {
         PieChart genderChart = new PieChart();

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
        Gender = FXCollections.observableArrayList(data0);

        if (count[0] != 0) {
            PieChart.Data data = new PieChart.Data(gen[1], count[1]);
            Gender.add(data);
        }
        genderChart.setData(Gender);
        genderChart.setLabelLineLength(20);
        View.getChildren().add(genderChart);
        ChartLbl.setText("No of Males : "+count[0]+"\n"+
                "No of Females : "+count[1]);
        ChartLbl.setWrapText(true);

    }

    @FXML
    public void Statistics() {
        ChartLbl.setText("");
        View.getChildren().clear();
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
                Platform.runLater(() -> {
                    notificationBuilder.create()
                            .title("Announcement")
                            .text("The server is on")
                            .graphic(graphic)
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.TOP_CENTER)
                            .darkStyle()
                            .showInformation();});
                ServicesAssigner.getInstance().startConnection();
                on = true;
                System.out.println("serveron");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ServicesAssigner.getInstance().stopServer();
                on = false;
                System.out.println("off");

                    Platform.runLater(() -> {
        notificationBuilder.create()
                .title("Announcement")
                .text("The server is off")
                .graphic(graphic)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_CENTER)
                .darkStyle()
                .showInformation();});

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @FXML
    VBox announcementVBox;
    @FXML
    private void enterToAnnouncementPage () {
        System.out.println("Enter To Announcement");
        stageCoordinator.switchToAnnouncement();
    }

    @FXML
    private void enterToAdministrators () {
        System.out.println("Enter  To Administrators");
        stageCoordinator.switchToAdministratorInformation();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageCoordinator = StageCoordinator.getInstance();

        genderS.setVisible(false);
        lineS.setVisible(false);
        countryS.setVisible(false);

        genderS.setOnAction(event -> {

            try {
                View.getChildren().clear();
            } catch (Exception e) {
            }
            getGenderData();

        });

        countryS.setOnAction(event -> {

            try {
                View.getChildren().clear();
            } catch (Exception e) {
            }

            getCountryData();

        });

        lineS.setOnAction(event -> {

            try {
                View.getChildren().clear();
            } catch (Exception e) {
            }

            getStatusData();

        });



    }
}
