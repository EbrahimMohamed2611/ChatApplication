package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.contract.clientServerDTO.dto.Contact;
import eg.gov.iti.server.db.dao.ContactDao;
import eg.gov.iti.server.db.helpers.dbFactory.MyDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    private static ContactDaoImpl instance=null;

    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;

    private ContactDaoImpl() throws SQLException {
        connection = dataSource.getConnection();
        System.out.println("Databases is running  ....");
    }

    public static ContactDaoImpl getInstance() throws SQLException {
        if(instance == null){

            instance=new ContactDaoImpl();


        }
        return instance;
    }
    @Override
    public int insertContact(Contact contact) {
        PreparedStatement preparedStatement = null;
        try {
            final String insertStatement = "insert into contact (userPhone,contactPhone) values (?,?)";
            preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setString(1,contact.getContactPhoneNumber());
            preparedStatement.setString(2,contact.getUserPhoneNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Contact getContact(String userPhone, String contactPhone) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from contact where contactPhone = ? and userPhone = ?");
            preparedStatement.setString(1,contactPhone);
            preparedStatement.setString(2,userPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String contactPhoneNumber = resultSet.getString(1);
                String userPhoneNumber = resultSet.getString(2);
                preparedStatement.close();
                return new Contact(contactPhoneNumber,userPhoneNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Contact> getContacts(String userPhone){
        List<Contact> contacts = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        System.out.println(userPhone);
        try {
            final String SQL_SELECT = "select phone_number,user_name,email,bio from user,contact " +
                    "where phoneNumber = contactPhone and contact.userPhone = ?";

            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1,userPhone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Contact contact = new Contact(userPhone,resultSet.getString(1),resultSet.getString("user_name")
                        ,resultSet.getString("bio"),resultSet.getString("email")
                       );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(contacts.size());
        return contacts;
    }
}