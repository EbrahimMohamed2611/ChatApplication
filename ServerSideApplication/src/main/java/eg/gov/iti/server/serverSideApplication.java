package eg.gov.iti.server;

import eg.gov.iti.contract.client.ChatClient;
import eg.gov.iti.server.db.dao.UserDao;
import eg.gov.iti.server.db.dao.daoImpl.UserDaoImpl;
import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import eg.gov.iti.server.db.entities.User;
import eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl.ChatServerImpl;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.sql.SQLException;

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

        try {
            UserDao userDao = new UserDaoImpl();
//            ChatClient chatClient;
//            chatClient.receiveUserDto();
            //rmi return UserDto
            //UserDto -> UserEntity
            User user = new User("01005425354", "ArabieIbrahim", "1234", "email@dfd.com", "Egypt", Date.valueOf("1997-02-25"), Gender.MALE, "", Status.AVAILABLE);
            userDao.save(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
        //  chatServerInterface.unRegister();
    }
}
