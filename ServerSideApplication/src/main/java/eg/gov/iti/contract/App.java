package eg.gov.iti.contract;

import eg.gov.iti.contract.db.helpers.MyDataSourceFactory;
import eg.gov.iti.contract.net.serverConfiguration.chatRemoteInterfaceImpl.ChatServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class App {

    public static void main(String[] args)
    {
        new App();
    }

    public App()
    {
       // MyDataSourceFactory myDataSourceFactory = (MyDataSourceFactory) MyDataSourceFactory.getMySQLDataSource();
      //  System.out.println(myDataSourceFactory);
        try{
            ChatServerImpl chatClient=new ChatServerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("chatApplication", chatClient);
            System.out.println("Server running ......");
        }catch(RemoteException ex){
            ex.printStackTrace();
        }


    }
}
