package eg.gov.iti.server.db.dao;

import eg.gov.iti.server.db.entities.Invitation;

import java.rmi.Remote;
import java.util.List;

public interface InvitationDao extends Remote {
    boolean saveInvitation(Invitation invitation);

    boolean hasInvitation(String phoneNumber);

    Boolean isExisted(Invitation invitation);

    List<Invitation> retrieveInvitations(String phoneNumber);

    Boolean deleteInvitation(Invitation invitation);
}
