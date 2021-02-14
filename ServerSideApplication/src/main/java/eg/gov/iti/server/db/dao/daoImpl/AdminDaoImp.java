package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.AdminDao;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImp implements AdminDao {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;
    private static AdminDaoImp instance=null;

    private AdminDaoImp() throws SQLException {
        connection = dataSource.getConnection();
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

    public static AdminDaoImp getInstance() throws SQLException {
        if(instance == null){
            instance = new AdminDaoImp();
        }
        return instance;
    }
}
