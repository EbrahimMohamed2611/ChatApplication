package eg.gov.iti.contract.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientMessageService extends Remote {

     void receiveFromMyFriend(String userMessageDto) throws RemoteException;
}
