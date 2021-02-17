package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.AuthService;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.helpers.adapters.UserAdapter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


public class AuthServiceImpl extends UnicastRemoteObject implements AuthService {

    UserDao userDao;
    public AuthServiceImpl() throws RemoteException, SQLException {
        userDao = UserDaoImpl.getInstance();
    }


    @Override
    public UserDto login(String phone, String Password) throws RemoteException {

        UserDto user = UserAdapter.getUserDtoFromEntityAdapter(userDao.selectByPhoneNumber(phone));
        if(user!=null){

            //add to page

        }

        return user;
    }

    @Override
    public int signUp(UserDto user) throws RemoteException {
        return 0;
    }
}
