package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.MessageDao;
import eg.gov.iti.server.db.entities.MessageEntity;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDaoImpl implements MessageDao {

    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;

    public MessageDaoImpl() throws SQLException {
        connection =dataSource.getConnection();
    }

    @Override
    public Boolean saveMessage(MessageEntity messageEntity) {
        final String SQL_INSERT = "INSERT INTO message (`sender_id`, `receiver_id`, `message_date`, `content`) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, messageEntity.getSender());
            preparedStatement.setString(2, messageEntity.getReceiver());
            preparedStatement.setDate(3, messageEntity.getMessageDate());
            preparedStatement.setString(4, messageEntity.getContent());
            System.out.println(messageEntity);
            int row = preparedStatement.executeUpdate();
            System.out.println("messageEntity Row Effected " +row);
            if(row != 0)
                return true;
        } catch (SQLException sqlException) {
            System.out.println("Message inserted failed");
            sqlException.printStackTrace();
        }

        return false;
    }
}
