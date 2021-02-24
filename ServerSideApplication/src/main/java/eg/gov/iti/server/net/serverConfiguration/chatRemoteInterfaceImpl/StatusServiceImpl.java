package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserFriendDto;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import eg.gov.iti.contract.server.chatRemoteInterfaces.StatusServiceInterface;
import eg.gov.iti.server.db.dao.daoImpl.FriendDaoImpl;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.Friendship;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.net.callbackConfiguration.OnlineClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class StatusServiceImpl extends UnicastRemoteObject implements StatusServiceInterface {
    private static StatusServiceImpl statusServiceInstance;
    private UserDaoImpl userDao;
    private FriendDaoImpl friendDao;

    protected StatusServiceImpl() throws RemoteException {
        try {
            userDao = UserDaoImpl.getInstance();
            friendDao = FriendDaoImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StatusServiceImpl getInstance() {
        if (statusServiceInstance == null) {
            try {
                statusServiceInstance = new StatusServiceImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return statusServiceInstance;
    }

    @Override
    public void updateStatus(ChatClient client, Status status) {
        try {
            User user = userDao.selectByPhoneNumber(client.getPhoneNumber());
            user.setStatus(status);
            userDao.update(user);

            List<Friendship> friendships = friendDao.retrieveFriendsOf(client.getPhoneNumber());

            List<String> phoneNumbers = friendships.stream()
                    .map(friendship -> friendship.getFriendPhoneNumber())
                    .collect(Collectors.toList());

            long count = OnlineClients.getInstance().getOnlineClients()
                    .values()
                    .stream()
                    .filter(client1 -> {
                        try {
                            return phoneNumbers.contains(client1.getPhoneNumber());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .map(client1 -> {
                        UserFriendDto userFriendDto = new UserFriendDto();
                        userFriendDto.setFriendPhoneNumber(user.getPhoneNumber());
                        userFriendDto.setName(user.getUserName());
                        userFriendDto.setFriendStatus(status);
                        userFriendDto.setImageEncoded(user.getImageEncoded());

                        try {
                            client1.notifyFriendUpdate(userFriendDto);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        return userFriendDto;
                    })
                    .count();
            System.out.println(count + " friends updated");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
