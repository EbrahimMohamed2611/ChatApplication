package eg.gov.iti.contract.net;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {

    public ChatClientImpl( )throws RemoteException {

    }

    public void receive(UserMessageDto userMessage)throws RemoteException{
        System.out.println(userMessage);
        Platform.runLater(()->{
          //  chatController.display(userMessage);
        });
    }


    @Override
    public void receiveMessage(UserMessageDto userMessage) throws RemoteException {

    }

    @Override
    public void receiveAnnouncement(String message) throws RemoteException {

    }

    @Override
    public void notify(String message, int type) throws RemoteException {

    }

    @Override
    public void receiveUserDto(UserDto userDto) throws RemoteException {

    }
}
