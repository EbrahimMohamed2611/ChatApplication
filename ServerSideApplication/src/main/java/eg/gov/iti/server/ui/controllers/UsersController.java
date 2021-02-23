package eg.gov.iti.server.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
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
    private StageCoordinator coordinator;


    private ObservableList<ObservableList> data;

    public UsersController() throws SQLException {
    }
    boolean edit ;

    @FXML
    JFXDatePicker UserDate;
    @FXML
    private Button DeleteBtn;
//    @FXML
//    private Button UpdateBtn;

    @FXML
    private Button InsertBtn;
    @FXML
    private Button backBtn;

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
    private TableView<User> UsersTable;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coordinator = StageCoordinator.getInstance();

        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        useCol.setCellFactory(TextFieldTableCell.forTableColumn());
        countryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        UsersTable.layout();
        ObservableList<User> usersUpdate = UsersTable.getSelectionModel().getSelectedItems();
        initUsersTableColumns();
        selectUsersTable();
        UsersTable.setEditable(true);
        useCol.setOnEditCommit((TableColumn.CellEditEvent<User, String> event) -> {
            User user = UsersTable.getSelectionModel().getSelectedItem();
            System.out.println(user);
            String name = event.getNewValue();
            user.setUserName(name);
            userDao.update(user);
            System.out.println(user);
            if( name.matches("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$")) {
                user.setUserName(event.getNewValue());
                userDao.update(user);
                System.out.println(user);

            }
            else{
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("User error");
                alertError.setHeaderText("Error Alert ");
                alertError.setContentText("name pattern violated");
                alertError.showAndWait();
            }
        });

        phoneCol.setOnEditCommit((TableColumn.CellEditEvent<User, String> event) -> {
            User user = UsersTable.getSelectionModel().getSelectedItem();
            System.out.println(user);
            String phone = event.getNewValue();
            if( phone.matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$")) {
                user.setPhoneNumber(event.getNewValue());
                userDao.update(user);
                System.out.println(user);

            }
            else{
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("User error");
                alertError.setHeaderText("Error Alert ");
                alertError.setContentText("phone pattern violated");
                alertError.showAndWait();
            }
        });

        emailCol.setOnEditCommit((TableColumn.CellEditEvent<User, String> event) -> {
            User user = UsersTable.getSelectionModel().getSelectedItem();
            System.out.println(user);
            String email = event.getNewValue();
            if( email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                user.setEmail(event.getNewValue());
                userDao.update(user);
                System.out.println(user);

            }
            else{
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("User error");
                alertError.setHeaderText("Error Alert ");
                alertError.setContentText("Email pattern violated");
                alertError.showAndWait();
            }
        });

        passwordCol.setOnEditCommit((TableColumn.CellEditEvent<User, String> event) -> {
            User user = UsersTable.getSelectionModel().getSelectedItem();
            System.out.println(user);
            String passwordd = event.getNewValue();
            if( passwordd.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$")) {
                user.setPassword(event.getNewValue());
                userDao.update(user);
                System.out.println(user);

            }
            else{
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("User error");
                alertError.setHeaderText("Error Alert ");
                alertError.setContentText("password pattern violated");
                alertError.showAndWait();
            }
        });

        countryCol.setOnEditCommit((TableColumn.CellEditEvent<User, String> event) -> {
            User user = UsersTable.getSelectionModel().getSelectedItem();
            System.out.println(user);
                user.setCountry(event.getNewValue());
                userDao.update(user);
                System.out.println(user);
        });

        EmailTxt.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if (EmailTxt.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                    email = true;
                } else {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("Please add a valid email");
                    alertError.showAndWait();
                    email = false;
                }

            }

        });

        PassTxt.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if (PassTxt.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$")) {
                    pass = true;
                } else {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("Please add a strong password");
                    alertError.showAndWait();
                    pass = false;
                }

            }

        });

        PhoneTxt.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) {
                if (PhoneTxt.getText().matches("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$")) {
                    phone = true;
                } else {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("Please add a valid Phone Number");
                    alertError.showAndWait();
                    phone = false;
                }

            }

        });

        NameTxt.focusedProperty().addListener((ov, oldV, newV) -> {
            String txt1 = NameTxt.getText();
            String txt = txt1.replaceAll("\\s+", " ");
            if (!newV) {
                if (txt.isEmpty() || txt == " " || !txt.matches("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$")) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("Please add a valid UserName");
                    alertError.showAndWait();
                    userN = false;
                } else {
                    userN = true;


                }

            }

        });



//                    UsersTable = new TableView();

        InsertBtn.setOnAction((event) -> {
            if (pass == true && email == true && phone == true && userN == true
                    && !CountryTxt.getText().isEmpty() &&
                    UserDate.getValue() != null
            ) {


                User user = new User();


                if (!userDao.isExisted(PhoneTxt.getText())
                &&
                        !userDao.EmailIsExisted(EmailTxt.getText())
                ) {

                    user.setUserName(NameTxt.getText());
                    user.setPhoneNumber(PhoneTxt.getText());
                    user.setEmail(EmailTxt.getText());
                    user.setPassword(PassTxt.getText());
                    user.setCountry(CountryTxt.getText());
//                        user.setDateOfBirth(UserDate.getValue().toString());
                    java.util.Date date = java.util.Date.from(UserDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    user.setDateOfBirth(Date.valueOf(sqlDate.toString()));


                    try {
                        userDao.saveTable(user);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    List<User> listUsers;
                    listUsers = userDao.selectAll();

                    UsersTable.setItems(FXCollections.observableList(listUsers));

                    NameTxt.setText("");
                    PhoneTxt.setText("");
                    EmailTxt.setText("");
                    PassTxt.setText("");
                    CountryTxt.setText("");

                }
                else if(userDao.EmailIsExisted(EmailTxt.getText())){
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("This email is already exists");
                    alertError.showAndWait();
                }

                else {

                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("User error");
                    alertError.setHeaderText("Error Alert ");
                    alertError.setContentText("This phone is already exists");
                    alertError.showAndWait();
                }
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("User error");
                alertError.setHeaderText("Error Alert ");
                alertError.setContentText("Please add all userdata");
                alertError.showAndWait();
            }

        });
        backBtn.setOnAction((event) -> {

coordinator.switchToHomeScene();

        });

        DeleteBtn.setOnAction((event) -> {
            User user = UsersTable.getSelectionModel().getSelectedItem();
            userDao.deleteByPhone(user.getPhoneNumber());
            selectUsersTable();

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



