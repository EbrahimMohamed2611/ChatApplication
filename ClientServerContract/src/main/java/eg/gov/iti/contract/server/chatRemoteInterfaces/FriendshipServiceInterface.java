package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.FriendshipDto;

import java.rmi.Remote;
import java.util.List;

public interface FriendshipServiceInterface extends Remote {
    List<FriendshipDto> getFriends(String phoneNumber);
}
