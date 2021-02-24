package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.clientServerDTO.enums.Status;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StatusServiceInterface extends Remote {
    void updateStatus(ChatClient client, Status status) throws RemoteException;
}
