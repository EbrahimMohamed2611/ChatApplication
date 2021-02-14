package eg.gov.iti.contract;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientSideApplication extends Application {



    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        stageCoordinator.initStage(primaryStage);
   //     stageCoordinator.switchToFirstLoginScene();

//        stageCoordinator.switchToHomeScene();

        stageCoordinator.switchToSignupScene();
//        ChatClient chatClient;
//        chatClient.receiveUserDto();
        primaryStage.show();
    }

    @Override
    public void init() {

        ServicesLocator.servicesInit();

        // Initialize Database & Network Connections
//        try{
//            Registry reg= LocateRegistry.getRegistry("127.0.0.1");
//            ChatServerInterface chatServerInterface = (ChatServerInterface) reg.lookup("chatApplication");
//
//            System.out.println("connection .......");
//        }catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
      //  chatServerInterface.unRegister();
    }

}