package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.AdminDao;
import eg.gov.iti.server.db.entities.Admin;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImp implements AdminDao {
    private DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    private Connection connection;
    private List<Admin> adminList = new ArrayList<>();
    private static AdminDaoImp instance=null;

    private AdminDaoImp() throws SQLException {
        connection = dataSource.getConnection();
    }
    public static AdminDaoImp getInstance() throws SQLException {
        if(instance == null){
            instance = new AdminDaoImp();
        }
        return instance;
    }

    @Override
    public Boolean isExisted(String phoneNumber) {
        try {
            final String SQL_SELECT = "Select * from admin where phone_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean isPasswordValid(String phoneNumber, String enteredPassword) {
        try {
            final String SQL_SELECT = "Select * from admin where phone_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,phoneNumber);
            ResultSet resultSets = preparedStatement.executeQuery();
            resultSets.next();
            if(resultSets.getString("password").equals(enteredPassword)){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Admin> getAllAdmins() {
        try {
            final String SQL_SELECT = "SELECT id, user_name, phone_number  FROM admin ";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery(SQL_SELECT);

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminId(resultSet.getInt(1));
                admin.setAdminUserName(resultSet.getString(2));
                admin.setAdminPhoneNumber(resultSet.getString(3));
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }


}
