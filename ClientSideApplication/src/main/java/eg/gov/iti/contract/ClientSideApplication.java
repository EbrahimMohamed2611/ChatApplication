package eg.gov.iti.contract;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.server.chatRemoteInterfaces.ChatServerInterface;
import eg.gov.iti.contract.ui.helpers.CachedCredentialsData;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.models.ConnectionModel;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientSideApplication extends Application {
    CachedCredentialsData cachedCredentialsData;
    private static Stage stage;
    private ConnectionModel connectionModel;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/chat-ico.png")));
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchConnectionServer();
        primaryStage.show();
    }

    @Override
    public void init() {
//        ServicesLocator.servicesInit();
//        connectionModel = ModelsFactory.getInstance().getConnectionModel();
//        String hostIp = connectionModel.getServerIp();
//

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

    public static Stage getStage() {
        return stage;
    }


}