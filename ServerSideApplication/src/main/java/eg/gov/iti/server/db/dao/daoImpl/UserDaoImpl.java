package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;
    private static UserDaoImpl instance=null;

    private UserDaoImpl() throws SQLException {
        connection = dataSource.getConnection();
        System.out.println("Databases is running  ....");
    }

    public static UserDaoImpl getInstance() throws SQLException {
        if(instance == null){

                instance=new UserDaoImpl();


        }
        return instance;
    }

    @Override
    public Boolean save(User user) {
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

        return false;
    }

    @Override
    public boolean update(User user){
        final String SQL_UPDATE = "UPDATE `chatproject`.`user` SET `user_name` = ?, `email` = ?, `picture` = ?, `password` = ?, " +
                "`gender` = ?, `country` = ?, `date_of_birth` = ?, `bio` = ?, `status` = ?, WHERE (`phone_number` = ?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getUserGender().toString());
            preparedStatement.setString(5,user.getCountry());
            preparedStatement.setDate(6,user.getDateOfBirth());
            preparedStatement.setString(7,user.getBio());
            preparedStatement.setString(8,user.getStatus().toString());
            preparedStatement.setString(9,user.getPhoneNumber());

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
        return false;
    }

    @Override
    public Boolean delete(User user){
        final String SQL_DELETE = "DELETE FROM `chatproject`.`user` WHERE (`phone_number` = ?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,user.getPhoneNumber());

            System.out.println("Deleted user");
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
        return false;
    }

    @Override
    public User selectByPhoneNumber(String phoneNumber) {
        final String SQL_SELECT = "Select * from user where phone_number = ?;";

        User user = new User();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,phoneNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
//                user.setImageEncoded(resultSet.getBlob("picture").toString());
                user.setPassword(resultSet.getString("password"));

                if (resultSet.getString("gender").equals("FEMALE"))
                    user.setUserGender(Gender.FEMALE);
                else if (resultSet.getString("gender").equals("MALE"))
                    user.setUserGender(Gender.MALE);

                user.setCountry(resultSet.getString("country"));
                user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                user.setBio(resultSet.getString("bio"));

                switch (resultSet.getString("status")) {
                    case "AVAILABLE":
                        user.setStatus(Status.AVAILABLE);
                        break;
                    case "AWAY":
                        user.setStatus(Status.AWAY);
                        break;
                    case "BUSY":
                        user.setStatus(Status.BUSY);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectUsersByPhoneNumbers(List<String> phoneNumbers) {
        List<User> users = new ArrayList<>();
        for (String phoneNumber : phoneNumbers) {
            users.add(selectByPhoneNumber(phoneNumber));
        }
        return users;
    }

    @Override
    public List<User> selectAll() {
        final String SQL_SELECT = "Select * from user";

        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User(resultSet.getString("phone_number"),
                        resultSet.getString("user_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("country"),
                        resultSet.getDate("date_of_birth"),
                        null, resultSet.getString("bio"), null);

                if (resultSet.getString("gender").equals("FEMALE"))
                    user.setUserGender(Gender.FEMALE);
                else if (resultSet.getString("gender").equals("MALE"))
                    user.setUserGender(Gender.MALE);

                switch (resultSet.getString("status")) {
                    case "AVAILABLE":
                        user.setStatus(Status.AVAILABLE);
                        break;
                    case "AWAY":
                        user.setStatus(Status.AWAY);
                        break;
                    case "BUSY":
                        user.setStatus(Status.BUSY);
                        break;
                }

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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

    @Override
    public Boolean isPasswordValid(String phoneNumber,String enteredPassword) {
        try {
            final String SQL_SELECT = "Select * from user where phone_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,phoneNumber);
            ResultSet rs=preparedStatement.executeQuery();
            rs.next();
            if(rs.getString("password").equals(enteredPassword)){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ResultSet getAllByCountry() {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("SELECT country, COUNT(*) " +
                    "AS Country_Count FROM user GROUP BY country");
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getAllByGender(){
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("SELECT gender, COUNT(*) AS " +
                    "'Gender_Count' FROM user GROUP BY gender");
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getAllOnOff(){
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("SELECT status, COUNT(*) AS " +
                    "'On/offline_Count' FROM user GROUP BY status");
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}