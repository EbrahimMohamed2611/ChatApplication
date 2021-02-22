package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateProfileServiceInterface extends Remote {
    UserDto getUser(String phoneNumber)throws RemoteException;
    Boolean updateProfile(UserDto userDto)throws RemoteException;
}
