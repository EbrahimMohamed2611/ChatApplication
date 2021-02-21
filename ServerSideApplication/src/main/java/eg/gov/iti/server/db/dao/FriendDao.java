package eg.gov.iti.server.db.dao;

import eg.gov.iti.server.db.entities.Friendship;
import eg.gov.iti.server.db.entities.Invitation;

import java.util.List;

public interface FriendDao {
    boolean saveFriendship(Friendship friendShip);
    List<Friendship> retrieveFriendsOf(String phoneNumber);
    boolean hasFriendship(String receiverPhoneNumber);
    boolean isExisted(Friendship friendship);
}
