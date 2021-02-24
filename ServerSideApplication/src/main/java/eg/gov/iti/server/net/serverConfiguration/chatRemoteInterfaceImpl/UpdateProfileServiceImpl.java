package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.UpdateProfileServiceInterface;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.helpers.adapters.UserAdapter;
import eg.gov.iti.server.net.callbackConfiguration.OnlineClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class UpdateProfileServiceImpl extends UnicastRemoteObject implements UpdateProfileServiceInterface {
    private UserDao userDao;
    private static UpdateProfileServiceImpl instance;
    private OnlineClients onlineClients;

    protected UpdateProfileServiceImpl() throws RemoteException {
        try {
            userDao= UserDaoImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onlineClients = OnlineClients.getInstance();
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
        boolean status =  userDao.update(UserAdapter.getUserEntityFromUserDtoAdapter(userDto));

        for (ChatClient client : onlineClients.getOnlineClients().values()) {
            UserFriendDto userFriendDto = new UserFriendDto();
            userFriendDto.setName(userDto.getFullName());
            userFriendDto.setFriendPhoneNumber(userDto.getPhoneNumber());
            userFriendDto.setImageEncoded(userDto.getImageEncoded());
            userFriendDto.setFriendStatus(userDto.getStatus());
            client.notifyFriendUpdate(userFriendDto);
        }

        return status;
    }
}
