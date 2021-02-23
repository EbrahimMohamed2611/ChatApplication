package eg.gov.iti.server.db.dao;

import eg.gov.iti.server.db.entities.User;

import java.rmi.Remote;
import java.util.List;

public interface UserDao extends Remote {
    Boolean save(User person);
    Boolean saveTable(User person);

    boolean update(User person);
    Boolean delete(User person);
    User selectByPhoneNumber(String phoneNumber) ;
    List<User> selectUsersByPhoneNumbers(List<String> phoneNumbers);
    List<User> selectAll() ;
    Boolean isExisted(String phoneNumber);
    Boolean isPasswordValid(String phoneNumber,String password);
    public Boolean deleteByPhone(String phone);
//    boolean deleteUSerTable(User user);
}
