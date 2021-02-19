package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;
import eg.gov.iti.server.db.dao.MessageDao;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.MessageDaoImpl;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.server.db.entities.MessageEntity;
import eg.gov.iti.server.db.helpers.adapters.MessageAdapter;
import eg.gov.iti.server.net.callbackConfiguration.OnlineClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageServiceImpl extends UnicastRemoteObject implements ServerMessageServiceInterface {


    private final MessageDao messageDao = new MessageDaoImpl();
    private MessageAdapter messageAdapter;
    private static MessageServiceImpl instance;

    protected MessageServiceImpl() throws RemoteException, SQLException {
    }

    public static MessageServiceImpl getInstance() {
        if (instance == null) {
            try {
                instance = new MessageServiceImpl();
            } catch (RemoteException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    @Override
    public void sendToMyFriend(  UserMessageDto userMessageDto) throws RemoteException {

        System.out.println("Sender phone number : " +  userMessageDto.getSenderPHoneNumber());
        System.out.println("Receiver phone number : " +  userMessageDto.getReceiverPhoneNumber());

        try {
            MessageEntity messageEntityFromMessageDto = MessageAdapter.getMessageEntityFromMessageDto(userMessageDto);
            UserDao userDao = UserDaoImpl.getInstance();
            System.out.println("Message : "  + messageEntityFromMessageDto);
//            messageDao.saveMessage(messageEntityFromMessageDto);

            OnlineClients onlineClients = OnlineClients.getInstance();
            Map<String, ChatClient> onlineClients1 = onlineClients.getOnlineClients();
            ChatClient chatClient = onlineClients1.get(messageEntityFromMessageDto.getReceiver());

            if(chatClient != null)
                chatClient.receiveMessage(userMessageDto);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }



}
