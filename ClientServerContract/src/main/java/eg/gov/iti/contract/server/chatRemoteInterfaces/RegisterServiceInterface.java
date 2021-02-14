package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterServiceInterface extends Remote {
    boolean addNewUser(UserDto userDto) throws RemoteException;
}
