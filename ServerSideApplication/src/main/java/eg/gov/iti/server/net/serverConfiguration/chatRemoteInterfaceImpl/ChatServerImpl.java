package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface {
    List<ChatClient> clientsVector=new ArrayList<>();

    public ChatServerImpl()throws RemoteException {}


    @Override
    public void register(ChatClient clientRef)throws RemoteException
    {
        clientsVector.add(clientRef);
        System.out.println("Client added");
    }

    public void unRegister(ChatClient clientRef)throws RemoteException
    {
        clientsVector.remove(clientRef);
        System.out.println("Client removed");
    }

    @Override
    public void changeStatus(String username, String status) throws RemoteException {

    }

//    public void tellOthers(ChatClient chatClient ,UserMessageDto userMessageDto)throws RemoteException
//    {
//        System.out.println("Message received: "+userMessageDto);
//        for(ChatClient clientRef: clientsVector)
//        {
////            clientRef.receive(userMessageDto);
//
//        }
//    }
    public void tellOthers(String message)throws RemoteException
    {
        System.out.println("Message received: "+message);
        for(ChatClient clientRef: clientsVector)
        {
            clientRef.receiveMessage(message);

        }
    }

}