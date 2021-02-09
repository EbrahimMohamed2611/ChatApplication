package eg.gov.iti.jets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage window;
    public static ImageView xx;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
      //  stage.getIcons().add(new Image(getClass().getResourceAsStream("/Pic/Apps-Chat-icon.png")));
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.setTitle("ITI Chat App");
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.show();
//        stage.setResizable(false);


    }


    static void setRoot(Parent parent, double x, double y, boolean b) throws IOException {
        Scene scene1 = new Scene(parent, x, y);
        window.setScene(scene1);
      //  window.setResizable(b);
        window.setMinHeight(600);
        window.setMinWidth(900);

    }


    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/HomeView.fxml"));

        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}