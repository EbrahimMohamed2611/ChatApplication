package eg.gov.iti.server.ui.controllers;

import eg.gov.iti.server.db.dao.AdminDao;
import eg.gov.iti.server.db.dao.daoImpl.AdminDaoImp;
import eg.gov.iti.server.db.entities.Admin;
import eg.gov.iti.server.db.helpers.adapters.AdminAdapter;
import eg.gov.iti.server.ui.models.AdminModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable {
    @FXML
    private TableView adminTableView;
    @FXML
    private TableColumn adminIdTextField;
    @FXML
    private TableColumn userNameTextField;
    @FXML
    private TableColumn phoneNumberTextField;
    private AdminDao adminDao;
    public ObservableList<AdminModel> adminObservableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminDao = AdminDaoImp.getInstance();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        adminObservableList = FXCollections.observableArrayList(AdminAdapter.getAdminModelFromEntity(adminDao.getAllAdmins()));

        adminIdTextField.setCellValueFactory(new PropertyValueFactory<>("adminId"));
        userNameTextField.setCellValueFactory(new PropertyValueFactory<>("userName"));
        phoneNumberTextField.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        System.out.println("adminObservableList " + adminObservableList);
        adminTableView.getItems().setAll(adminObservableList);
    }

}