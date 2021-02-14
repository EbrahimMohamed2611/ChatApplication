package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.server.messageServices.ServerMessageServiceInterface;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageServiceImpl extends UnicastRemoteObject implements ServerMessageServiceInterface {
    List<ChatClient> clientsVector=new ArrayList<>();
    UserDao userDao ;

    private static MessageServiceImpl instance;

    protected MessageServiceImpl() throws RemoteException {


    }

    public static MessageServiceImpl getInstance() {
        if (instance == null) {
            try {
                instance = new MessageServiceImpl();
            } catch (RemoteException e) {
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
    public void sendToMyFriend(String userMessageDto) throws RemoteException {

        try {
            userDao= UserDaoImpl.getInstance();
            System.out.println("Message received: "+userMessageDto);
            for(ChatClient clientRef: clientsVector)
            {
                clientRef.receiveMessage(userMessageDto);

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }



}
