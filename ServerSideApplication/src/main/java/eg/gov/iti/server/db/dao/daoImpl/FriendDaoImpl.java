package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.FriendDao;
import eg.gov.iti.server.db.entities.Friendship;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDaoImpl implements FriendDao {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;
    private static FriendDaoImpl friendDaoInstance;

    public FriendDaoImpl() throws SQLException {
        connection = dataSource.getConnection();
    }

    public static FriendDaoImpl getInstance() {
        if (friendDaoInstance == null) {
            try {
                friendDaoInstance = new FriendDaoImpl();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return friendDaoInstance;
    }

    @Override
    public boolean saveFriendship(Friendship friendship) {
        final String SQL_INSERT = "INSERT INTO friend (`id`, `friend_id`) VALUES (?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, friendship.getUserPhoneNumber());
            preparedStatement.setString(2, friendship.getFriendPhoneNumber());
            int row = preparedStatement.executeUpdate();


            if (row != 0) {
                preparedStatement.setString(1, friendship.getFriendPhoneNumber());
                preparedStatement.setString(2, friendship.getUserPhoneNumber());
                row = preparedStatement.executeUpdate();
                if (row != 0) {
                    System.out.println(row);
//                    System.out.println(friendship + "Added Successfully");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Friendship> retrieveFriendsOf(String phoneNumber) {
        final String SQL_SELECT = "Select * from friend where id = ?;";

        List<Friendship> friendships = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, phoneNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Friendship friendship = new Friendship();

                friendship.setUserPhoneNumber(resultSet.getString("id"));
                friendship.setFriendPhoneNumber(resultSet.getString("friend_id"));

                friendships.add(friendship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public boolean hasFriendship(String phoneNumber) {
        try {
            final String SQL_SELECT = "SELECT * FROM friend where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, phoneNumber);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExisted(Friendship friendship) {
        try {
            final String SQL_SELECT = "Select * from friend where id = ? AND friend_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, friendship.getUserPhoneNumber());
            preparedStatement.setString(2, friendship.getFriendPhoneNumber());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
