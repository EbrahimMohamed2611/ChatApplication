package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthService extends Remote {

    UserDto login(String phone , String Password) throws RemoteException;
    int signUp(UserDto user) throws RemoteException;

}
