package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.UpdateProfileServiceInterface;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.helpers.adapters.UserAdapter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class UpdateProfileServiceImpl extends UnicastRemoteObject implements UpdateProfileServiceInterface {
    private UserDao userDao;
    private static UpdateProfileServiceImpl instance;
    protected UpdateProfileServiceImpl() throws RemoteException {
        try {
            userDao= UserDaoImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static UpdateProfileServiceImpl getInstance() throws RemoteException {
        if(instance == null){
            instance=new UpdateProfileServiceImpl();
        }
        return instance;
    }
    @Override
    public UserDto getUser(String phoneNumber) throws RemoteException {
        var entity=userDao.selectByPhoneNumber(phoneNumber);
        return UserAdapter.getUserDtoFromEntityAdapter(entity);
    }

    @Override
    public Boolean updateProfile(UserDto userDto)throws RemoteException {
        return userDao.update(UserAdapter.getUserEntityFromUserDtoAdapter(userDto));

    }
}
