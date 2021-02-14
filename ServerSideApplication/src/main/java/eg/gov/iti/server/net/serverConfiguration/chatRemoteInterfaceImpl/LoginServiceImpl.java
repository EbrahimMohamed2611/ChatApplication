package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServiceImpl extends UnicastRemoteObject implements LoginServiceInterface {
    UserDao userDao ;
    private static LoginServiceImpl instance;

    protected LoginServiceImpl() throws RemoteException {


    }

    public static LoginServiceImpl getInstance() {
        if (instance == null) {
            try {
                instance = new LoginServiceImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    @Override
    public boolean checkPhoneNumber(UserAuthDto userAuthDto) throws RemoteException {

        try {

            userDao=UserDaoImpl.getInstance();
            return userDao.isExisted(userAuthDto.getPhoneNumber());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkPassword(UserAuthDto userAuthDto) throws RemoteException {
        try {
            userDao=UserDaoImpl.getInstance();
            return userDao.isPasswordValid(userAuthDto.getPhoneNumber(),userAuthDto.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
