package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterServiceInterface extends Remote {
    boolean addNewUser(UserRegDto userRegDto) throws RemoteException;
    boolean  checkPhoneNumber(String userPhoneNumber) throws RemoteException;
}