package eg.gov.iti.contract.net;

import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServicesLocator {
    private static Registry registry;
    private static ServicesLocator instance;

    private static ChatServerInterface chatServerInterface;
    private static LoginServiceInterface loginService;
    private static boolean connectionEstablished;
    private ServicesLocator(){

    }

    public static boolean isConnectionEstablished() {
        return connectionEstablished;
    }
    public static ServicesLocator getInstance() {
        if(instance==null)
            instance = new ServicesLocator();
        return instance;
    }

    public static ChatServerInterface getChatServerInterface() {
        return chatServerInterface;
    }

    public static LoginServiceInterface getLoginService() {
        return loginService;
    }

    public static boolean servicesInit(){
        if(!connectionEstablished){
            try {
                registry = LocateRegistry.getRegistry("127.0.0.1");
                //services lookup
                chatServerInterface = (ChatServerInterface) registry.lookup("chatApplication");
                loginService = (LoginServiceInterface) registry.lookup("loginService");


                connectionEstablished = true;
                return true;
            } catch (Exception e) {
                connectionEstablished = false;
                System.out.println(">> RMI-Registry Couldn't Establish a Connection...");
                return false;
            }
        }else {
            connectionEstablished = false;
            System.out.println(">> RMI-Registry Connection Already Established...");
            return true;
        }
    }

}
