package eg.gov.iti.server.db.dao;

import eg.gov.iti.server.db.entities.MessageEntity;

public interface MessageDao {

    Boolean saveMessage(MessageEntity messageEntity);
}
