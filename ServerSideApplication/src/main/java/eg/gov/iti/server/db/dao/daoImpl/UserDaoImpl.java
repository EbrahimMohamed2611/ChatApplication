package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;

    public UserDaoImpl() throws SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public Boolean save(User user) {

        try {
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final String SQL_INSERT = "INSERT INTO user (`phone_number`, `user_name`, `email`, `password`, `gender`, `country`, `date_of_birth`, `bio`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,user.getPhoneNumber());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getUserGender().toString());
            preparedStatement.setString(6,user.getCountry());
            preparedStatement.setDate(7,user.getDateOfBirth());
            preparedStatement.setString(8,user.getBio());
            preparedStatement.setString(9,user.getStatus().toString());

            System.out.println(user);

            int row = preparedStatement.executeUpdate();

            System.out.println(row);

            if(row != 0){
                return true;
            }
        } catch (SQLException e) {
            System.err.format("SQL State: ", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return false;    }

    @Override
    public User update(User person){

        return null;
    }

    @Override
    public Boolean delete(User person){

        return null;
    }


    @Override
    public User selectByPhoneNumber(String personPhoneNumber) {

        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public Boolean isExisted(String phoneNumber) {
        try {

            final String SQL_SELECT = "Select * from user where phone_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,phoneNumber);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
