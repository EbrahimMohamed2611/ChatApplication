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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl extends UnicastRemoteObject implements ServerMessageServiceInterface {

    private List<ChatClient> clientsVector=new ArrayList<>();

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
    public void register(ChatClient clientRef)throws RemoteException
    {
        clientsVector.add(clientRef);
        System.out.println("Client added");
    }


    @Override
    public void sendToMyFriend(ChatClient senderClient, UserMessageDto userMessageDto) throws RemoteException {
        String sender = userMessageDto.getSenderPHoneNumber();
        System.out.println("Sender : " + sender);
        System.out.println("senderClient " + senderClient.getPhoneNumber());
        try {
            MessageEntity messageEntityFromMessageDto = MessageAdapter.getMessageEntityFromMessageDto(userMessageDto);
            UserDao userDao = UserDaoImpl.getInstance();
            System.out.println("Message : "  + messageEntityFromMessageDto);
            messageDao.saveMessage(messageEntityFromMessageDto);

            for(ChatClient clientRef: clientsVector)
            {
                if(clientRef.getPhoneNumber().equals(messageEntityFromMessageDto.getReceiver()))
                clientRef.receiveMessage(userMessageDto);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }



}
