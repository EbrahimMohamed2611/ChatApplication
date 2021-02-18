package eg.gov.iti.contract.client;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
  //  void receiveMessage (UserMessageDto userMessage) throws RemoteException;
    void receiveMessage (UserMessageDto userMessage) throws RemoteException;
    void receiveAnnouncement(String message)throws RemoteException;
    void notify(String message , int type)  throws RemoteException;
    void receiveUserDto(UserDto userDto)throws RemoteException;

    String getPhoneNumber() throws RemoteException;
}
