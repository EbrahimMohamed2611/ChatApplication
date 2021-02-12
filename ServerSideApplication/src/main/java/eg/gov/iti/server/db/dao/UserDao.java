package eg.gov.iti.server.db.dao;

import eg.gov.iti.server.db.entities.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserDao extends Remote {
    Boolean save(User person);
    User update(User person);
    Boolean delete(User person);
    User selectByPhoneNumber(String phoneNumber) ;
    List<User> selectAll() ;

    Boolean isExisted(String phoneNumber);


}
