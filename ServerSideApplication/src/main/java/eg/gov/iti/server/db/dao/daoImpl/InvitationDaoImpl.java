package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.InvitationDao;
import eg.gov.iti.server.db.entities.Invitation;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitationDaoImpl implements InvitationDao {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;
    private static InvitationDaoImpl invitationInstance;

    public InvitationDaoImpl() throws SQLException {
        connection = dataSource.getConnection();
    }

    public static InvitationDaoImpl getInstance() {
        if (invitationInstance == null) {
            try {
                invitationInstance = new InvitationDaoImpl();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return invitationInstance;
    }

    @Override
    public boolean saveInvitation(Invitation invitation) {
        final String SQL_INSERT = "INSERT INTO invitation (`sender_phoneNumber`, `receiver_phoneNumber`) VALUES (?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, invitation.getSenderPhoneNumber());
            preparedStatement.setString(2, invitation.getReceiverPhoneNumber());

            System.out.println(invitation);

            int row = preparedStatement.executeUpdate();

            System.out.println(row);

            if (row != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hasInvitation(String receiverPhoneNumber) {
        try {
            final String SQL_SELECT = "SELECT * FROM invitation where receiver_phoneNumber = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, receiverPhoneNumber);
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
    public Boolean isExisted(Invitation invitation) {
        try {
            final String SQL_SELECT = "Select * from invitation where sender_phoneNumber = ? AND receiver_phoneNumber = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,invitation.getSenderPhoneNumber());
            preparedStatement.setString(2, invitation.getReceiverPhoneNumber());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Invitation> retrieveInvitations(String phoneNumber) {
        final String SQL_SELECT = "Select * from invitation where receiver_phoneNumber = ?;";

        List<Invitation> invitations = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, phoneNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invitation invitation = new Invitation();

                invitation.setSenderPhoneNumber(resultSet.getString("sender_phoneNumber"));
                invitation.setReceiverPhoneNumber(resultSet.getString("receiver_phoneNumber"));

                invitations.add(invitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invitations;
    }

    @Override
    public Boolean deleteInvitation(Invitation invitation){
        final  String SQL_DELETE = "DELETE FROM invitation WHERE (`receiver_phoneNumber` = ?) and (`sender_phoneNumber` = ?);";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,invitation.getReceiverPhoneNumber());
            preparedStatement.setString(2,invitation.getSenderPhoneNumber());

            System.out.println("Deleted invitation" + invitation);

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
}
