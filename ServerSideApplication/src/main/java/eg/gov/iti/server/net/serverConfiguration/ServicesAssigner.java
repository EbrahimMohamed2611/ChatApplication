package eg.gov.iti.server.net.serverConfiguration;

import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.*;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServicesAssigner {

    private static ServicesAssigner instance;
    private Registry registry;
    private final ChatServerImpl chatClient = new ChatServerImpl();
    private final LoginServiceImpl loginService =LoginServiceImpl.getInstance();
    private final MessageServiceImpl messageService = MessageServiceImpl.getInstance();
    private RegisterServiceImpl registerService = RegisterServiceImpl.getInstance();
    private LogoutServiceImpl logoutService = LogoutServiceImpl.getInstance();
    private UpdateProfileServiceImpl updateProfileService = UpdateProfileServiceImpl.getInstance();

    private InetAddress ip;
    private InvitationServiceImpl invitationService = InvitationServiceImpl.getInstance();

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
                ip = InetAddress.getLocalHost();
                System.out.println("HostAddress " + ip.getHostAddress());
                System.out.println("CanonicalHostName" + ip.getCanonicalHostName());

                this.registry = LocateRegistry.createRegistry(1099);
                System.out.println(">> RMI-Registry Connection Established...");
                return true;
            } catch (RemoteException | UnknownHostException e) {
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
            registry.rebind("messageService",messageService);
            registry.rebind("logoutService", logoutService);
            registry.rebind("registerService",registerService);
            registry.rebind("inviteService", invitationService);
            registry.rebind("updateProfileService",updateProfileService);
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
