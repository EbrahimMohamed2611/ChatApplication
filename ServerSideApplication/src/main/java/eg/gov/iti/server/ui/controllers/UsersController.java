package eg.gov.iti.server.ui.controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

import javax.sql.DataSource;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersController implements Initializable {


    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();

    UserDao userDao = UserDaoImpl.getInstance(dataSource.getConnection());
    ObservableList<User> selectedIndexes = FXCollections.observableArrayList();




    private ObservableList<ObservableList> data;

    public UsersController() throws SQLException {
    }

                  @FXML
                JFXDatePicker UserDate;
                @FXML
                private Button DeleteBtn;

                 @FXML
                 private Button InsertBtn;

                @FXML
                private TableColumn<User, String> phoneCol;

                @FXML
                private TableColumn<User, String> useCol;

                @FXML
                private TableColumn<User, String> emailCol;

                @FXML
                private TableColumn<User, String> passwordCol;

                @FXML
                private TableColumn<User, String> countryCol;

                @FXML
                private TableView <User> UsersTable;
                @FXML
                private JFXTextField PhoneTxt;
                @FXML
                 private JFXTextField NameTxt;
                 @FXML
                 private JFXTextField EmailTxt;
                 @FXML
                 private JFXTextField PassTxt;
                @FXML
                 private JFXTextField CountryTxt;

                boolean email;
                boolean pass;
                boolean userN;
                boolean date;
                boolean country;
                boolean phone;


    @Override
                public void initialize (URL url, ResourceBundle resourceBundle) {

        phoneCol.setCellFactory( TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory( TextFieldTableCell.forTableColumn());
        passwordCol.setCellFactory( TextFieldTableCell.forTableColumn());
        useCol.setCellFactory( TextFieldTableCell.forTableColumn());
        countryCol.setCellFactory( TextFieldTableCell.forTableColumn());

        UsersTable.layout();

        UsersTable.setEditable(true);
        ObservableList<User> usersUpdate = UsersTable.getSelectionModel().getSelectedItems();







        PhoneTxt.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if (PhoneTxt.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$")) {
                    phone=true;
                } else {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("Please add a valid Phone Number");
                    alertError.showAndWait();
                    phone= false;
                }

            }

        });


                    initUsersTableColumns();
                    selectUsersTable();
                    UsersTable.setEditable(true);
                    UsersTable = new TableView();



                    DeleteBtn.setOnAction((event) -> {
                    UsersTable.getSelectionModel().getSelectedItems().clear();
//userDao.deleteByPhone(phoneCol.getText());





                    });


                }

                    private void initUsersTableColumns() {
                        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                        useCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
                        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
                        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
                        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

                    }
//select table
    public void selectUsersTable() {
        List<User> allUsers = userDao.selectAll();
        if (allUsers != null) {
            UsersTable.setItems(FXCollections.observableList(allUsers));
        } else {
            System.out.println("No Users");
        }

    }








}



