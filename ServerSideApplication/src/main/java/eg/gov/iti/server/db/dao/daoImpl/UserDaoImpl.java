package eg.gov.iti.server.db.dao.daoImpl;

import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.entities.User;

import java.rmi.RemoteException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Boolean save(User person) throws RemoteException {
        return null;
    }

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
