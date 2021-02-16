package eg.gov.iti.contract.net;


import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import eg.gov.iti.contract.net.adapters.MessageAdapter;
import eg.gov.iti.contract.ui.controllers.HomeController;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserMessageModel;
import javafx.application.Platform;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {

    private  String clientPhoneNumber = new UserAuthModel().getPhoneNumber();
    HomeController homeController;
    private static ChatClientImpl instance;

    public ChatClientImpl(HomeController homeController) throws RemoteException {
        this.homeController = homeController;

    }

    private ChatClientImpl() throws RemoteException {

    }

    public static ChatClientImpl getInstance() {
        if (instance == null) {
            try {
                instance = new ChatClientImpl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }



//    public void receiveMessage(String userMessage)throws RemoteException{
//        System.out.println(userMessage);
//        Platform.runLater(()->{
//            homeController.displayFriendMessage(userMessage);
//        });
//    }


//    @Override
//    public void receiveMessage(UserMessageDto userMessage) throws RemoteException {
//
//    }
        @Override
        public void receiveMessage (UserMessageDto userMessage) throws RemoteException {
            UserMessageModel messageModelFromMessageDto = MessageAdapter.getMessageModelFromMessageDto(userMessage);
            System.out.println(userMessage);
            Platform.runLater(() -> {
                try {
                    homeController.displayFriendMessage(messageModelFromMessageDto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void receiveAnnouncement (String message) throws RemoteException {

        }

        @Override
        public void notify (String message,int type) throws RemoteException {

        }

        @Override
        public void receiveUserDto (UserDto userDto) throws RemoteException {

        }
    }
