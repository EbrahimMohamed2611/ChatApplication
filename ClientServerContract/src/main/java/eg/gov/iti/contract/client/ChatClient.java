package eg.gov.iti.contract.client;

import eg.gov.iti.contract.clientServerDTO.dto.UserMessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive (UserMessageDto userMessage) throws RemoteException;
}
