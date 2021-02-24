package eg.gov.iti.server;

import eg.gov.iti.server.net.serverConfiguration.ServicesAssigner;
import eg.gov.iti.server.ui.helpers.StageCoordinator;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class serverSideApplication extends Application {

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        StageCoordinator stageCoordinator = StageCoordinator.getInstance();
//        stageCoordinator.switchToLoginScene();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/chat-ico.png")));
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToHomeScene();
        primaryStage.show();
    }

    @Override
    public void init() {
        // MyDataSourceFactory myDataSourceFactory = (MyDataSourceFactory) MyDataSourceFactory.getMySQLDataSource();
        //  System.out.println(myDataSourceFactory);

            ServicesAssigner.getInstance().initConnection();
            ServicesAssigner.getInstance().startConnection();


//        try {
//            UserDao userDao = new UserDaoImpl();
////            ChatClient chatClient;
////            chatClient.receiveUserDto();
//            //rmi return UserDto
//            //UserDto -> UserEntity
//            User user = new User("01005425354", "ArabieIbrahim", "1234", "email@dfd.com", "Egypt", Date.valueOf("1997-02-25"), Gender.MALE, "", Status.AVAILABLE);
//            userDao.save(user);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void stop() {
        // Terminate Database & Network Connections
        //  chatServerInterface.unRegister();
    }
}
