package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServerOnInterface extends Remote {

     Boolean addNewUser(UserRegDto user) throws RemoteException, SQLException;


}
