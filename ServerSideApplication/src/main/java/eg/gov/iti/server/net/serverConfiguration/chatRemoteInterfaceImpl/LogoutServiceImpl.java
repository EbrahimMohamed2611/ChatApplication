package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.server.chatRemoteInterfaces.LogoutServiceInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogoutServiceImpl extends UnicastRemoteObject implements LogoutServiceInterface {
    private static LogoutServiceImpl instance;

    protected LogoutServiceImpl() throws RemoteException {
    }

    @Override
    public boolean logout() throws RemoteException {
        return true;
    }

    public static LogoutServiceImpl getInstance() {
        if (instance == null) {
            try {
                instance = new LogoutServiceImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
