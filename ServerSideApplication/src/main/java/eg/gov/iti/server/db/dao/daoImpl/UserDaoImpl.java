package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.MyDataSourceFactory;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoImpl extends UnicastRemoteObject implements UserDao {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;

    public UserDaoImpl() throws RemoteException, SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public Boolean save(User user) throws RemoteException {

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
        } catch (SQLException throwables) {
            System.err.format("SQL State: ", throwables.getSQLState(), throwables.getMessage());
            throwables.printStackTrace();
        }

        return false;    }

    @Override
    public User update(User person) throws RemoteException {
        return null;
    }

    @Override
    public Boolean delete(User person) throws RemoteException {
        return null;
    }

    @Override
    public User selectByPhoneNumber(int personPhoneNumber) throws RemoteException {
        return null;
    }

    @Override
    public List<User> selectAll() throws RemoteException {
        return null;
    }
}
