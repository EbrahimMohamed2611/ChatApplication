package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class LoginServiceImpl extends UnicastRemoteObject implements LoginServiceInterface {
    UserDao userDao ;
    protected LoginServiceImpl() throws RemoteException {


    }

    @Override
    public boolean checkPhoneNumber(UserAuthDto userAuthDto) throws RemoteException {

        try {

            userDao=new UserDaoImpl();

            User user =userDao.selectByPhoneNumber(userAuthDto.getPhoneNumber());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
