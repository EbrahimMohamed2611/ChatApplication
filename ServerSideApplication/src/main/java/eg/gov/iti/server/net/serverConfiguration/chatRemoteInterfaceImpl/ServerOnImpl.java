package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;
import eg.gov.iti.contract.server.chatRemoteInterfaces.RegisterServiceInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ServerOnInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ServerOnImpl extends UnicastRemoteObject implements ServerOnInterface {

    protected ServerOnImpl() throws RemoteException {

    }
    private static ServerOnImpl instance;



    @Override
    public Boolean addNewUser(UserRegDto user) throws RemoteException, SQLException {
        return null;
    }

    public static ServerOnImpl getInstance() {
        if (instance == null) {
            try {
                instance = new ServerOnImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;

    }
}
