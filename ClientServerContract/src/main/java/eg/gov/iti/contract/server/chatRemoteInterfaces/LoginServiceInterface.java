package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface LoginServiceInterface extends Remote {

    boolean  checkPhoneNumber(UserAuthDto userAuthDto) throws RemoteException;
    boolean  checkPassword(UserAuthDto userAuthDto) throws RemoteException;


}
