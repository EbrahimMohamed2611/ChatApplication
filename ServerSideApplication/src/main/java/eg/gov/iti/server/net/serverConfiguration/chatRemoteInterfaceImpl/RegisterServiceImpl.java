package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.RegisterServiceInterface;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.db.helpers.adapters.UserAdapter;
import eg.gov.iti.server.db.helpers.adapters.UserRegAdapter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class RegisterServiceImpl extends UnicastRemoteObject implements RegisterServiceInterface {



    UserDao userDao;
    private static RegisterServiceImpl instance;
    User user;
    protected RegisterServiceImpl() throws RemoteException {

    }


    public static RegisterServiceImpl getInstance() {
        if (instance == null) {
            try {
                instance = new RegisterServiceImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;

    }

    @Override
    public boolean addNewUser(UserRegDto userRegDto) throws RemoteException {
        try {

            userDao = UserDaoImpl.getInstance();

            if(userDao.isExisted(userRegDto.getPhoneNumber()))
            {

                return false;
            }

            else {


                user = UserRegAdapter.getUserEntityFromUserRegDtoAdapter(userRegDto);

                userDao.save(user);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}