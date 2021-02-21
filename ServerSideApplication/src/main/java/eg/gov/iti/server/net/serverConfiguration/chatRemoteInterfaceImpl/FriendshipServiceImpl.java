package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.FriendshipDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.FriendshipServiceInterface;
import eg.gov.iti.server.db.dao.daoImpl.FriendDaoImpl;
import eg.gov.iti.server.db.entities.Friendship;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FriendshipServiceImpl extends UnicastRemoteObject implements FriendshipServiceInterface {
    private static FriendshipServiceImpl friendshipServiceInstance;
    FriendDaoImpl friendDao;

    protected FriendshipServiceImpl() throws RemoteException {
        friendDao = FriendDaoImpl.getInstance();
    }

    public static FriendshipServiceImpl getInstance() {
        if (friendshipServiceInstance == null) {
            try {
                friendshipServiceInstance = new FriendshipServiceImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return friendshipServiceInstance;
    }

    @Override
    public List<FriendshipDto> getFriends(String phoneNumber) {
        List<FriendshipDto> friends = new ArrayList<>();

        List<Friendship> friendships = friendDao.retrieveFriendsOf(phoneNumber);

        for (Friendship friendship : friendships) {
            FriendshipDto friendshipDto = new FriendshipDto();
            friendshipDto.setUserPhoneNumber(friendship.getUserPhoneNumber());
            friendshipDto.setFriendPhoneNumber(friendship.getFriendPhoneNumber());
            friends.add(friendshipDto);
        }
        return friends;
    }
}
