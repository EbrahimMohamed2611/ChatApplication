package eg.gov.iti.server.net.serverConfiguration;

import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.ChatServerImpl;
import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.LoginServiceImpl;
import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.RegisterServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServicesAssigner {

    private static ServicesAssigner instance;
    private Registry registry;
    private ChatServerImpl chatClient = new ChatServerImpl();
    private LoginServiceImpl loginService =LoginServiceImpl.getInstance();
    private RegisterServiceImpl registerService = RegisterServiceImpl.getInstance();


    private ServicesAssigner() throws RemoteException {
    }

    public static synchronized ServicesAssigner getInstance()  {
        if (instance == null) {
            try {

                instance = new ServicesAssigner();

            } catch (RemoteException e) {

                e.printStackTrace();

            }
        }
        return instance;
    }


    public boolean initConnection() {
        if (registry == null) {
            try {
                this.registry = LocateRegistry.createRegistry(1099);
                System.out.println(">> RMI-Registry Connection Established...");
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println(">> RMI-Registry Connection Already Established...");
            return true;
        }
    }
    public void startConnection() {
        try {
            //bind service
            registry.rebind("chatApplication", chatClient);

            registry.rebind("loginService",loginService);

            registry.rebind("registerService",registerService);


            System.out.println("Server running ......");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void stopConnection() {
//        if (registry != null) {
//            try {
//                for (String service : this.registry.list()) {
//                    this.registry.unbind(service);
//                    System.out.println(">> RMI-Registry Service " + service + " Unbounded...");
//                }
//                for (ClientInterface client : ServerService.getAllOnlineClients()) {
//                    client.serverDisconnected();
//                }
//            } catch (RemoteException | NotBoundException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println(">> RMI-Registry Connection Already Closed");
//        }
//    }

}
