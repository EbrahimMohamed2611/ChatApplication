package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.FriendshipDto;
import eg.gov.iti.server.db.entities.Friendship;

public class UserFriendAdapter {
    private static FriendshipDto friendshipDto;
    private static Friendship friendship;

    public static Friendship getFriendshipFromFriendDto(FriendshipDto friendshipDto) {
        friendship = new Friendship();
        friendship.setUserPhoneNumber(friendshipDto.getUserPhoneNumber());
        friendship.setFriendPhoneNumber(friendshipDto.getFriendPhoneNumber());

        return friendship;
    }

    public static FriendshipDto getFriendDtoFromFriendshipModel(Friendship friendship) {
        friendshipDto = new FriendshipDto();
        friendshipDto.setUserPhoneNumber(friendship.getUserPhoneNumber());
        friendshipDto.setFriendPhoneNumber(friendship.getFriendPhoneNumber());

        return friendshipDto;
    }
}
