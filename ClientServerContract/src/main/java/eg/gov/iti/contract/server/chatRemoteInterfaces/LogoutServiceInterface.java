package eg.gov.iti.contract.server.chatRemoteInterfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LogoutServiceInterface extends Remote {
    boolean logout() throws RemoteException;
}
