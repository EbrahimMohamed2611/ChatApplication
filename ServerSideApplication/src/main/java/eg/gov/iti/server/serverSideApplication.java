package eg.gov.iti.server;

import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.ChatServerImpl;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class serverSideApplication extends Application {

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        stageCoordinator.initStage(primaryStage);
//        stageCoordinator.switchToLoginScene();
        stageCoordinator.switchToHomeScene();
        primaryStage.show();
    }

    @Override
    public void init() {
        // MyDataSourceFactory myDataSourceFactory = (MyDataSourceFactory) MyDataSourceFactory.getMySQLDataSource();
        //  System.out.println(myDataSourceFactory);
        try{
            ChatServerImpl chatClient = new ChatServerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("chatApplication", chatClient);
            System.out.println("Server running ......");
        }catch(RemoteException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
        //  chatServerInterface.unRegister();
    }
}
