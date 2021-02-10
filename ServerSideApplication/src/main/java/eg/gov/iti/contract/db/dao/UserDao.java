package eg.gov.iti.contract.db.dao;

import eg.gov.iti.contract.db.entities.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserDao extends Remote {
    public Boolean save(User person) throws RemoteException;
    public User update(User person) throws  RemoteException;
    public Boolean delete(User person) throws  RemoteException;
    public User selectByPhoneNumber(int personPhoneNumber) throws  RemoteException;
    public List<User> selectAll() throws  RemoteException;
}
