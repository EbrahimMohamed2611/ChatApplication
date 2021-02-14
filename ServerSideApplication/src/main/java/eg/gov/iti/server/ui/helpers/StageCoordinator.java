package eg.gov.iti.server.ui.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static Stage primaryStage;
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
    private final Map<String, SceneData> scenes = new HashMap<>();

    private StageCoordinator() { }

    public void initStage(Stage stage) {
        if (primaryStage != null) {
            throw new IllegalArgumentException("The Stage Already been initialized");
        }
        primaryStage = stage;
    }

    public static StageCoordinator getInstance() {
        return stageCoordinator;
    }

    public void switchToLoginScene() {
        if (primaryStage == null) {
            throw new RuntimeException("Stage Coordinator should be initialized with a Stage before it could be used");
        }

        if (!scenes.containsKey("login")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/LoginViews/FirstLoginView.fxml"));
                Parent loginView = fxmlLoader.load();
                Scene loginScene = new Scene(loginView);
                SceneData loginSceneData = new SceneData(fxmlLoader, loginView, loginScene);
                scenes.put("login", loginSceneData);
                primaryStage.setScene(loginScene);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO Exception: Couldn't load 'Login View' FXML file");
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData loginSceneData = scenes.get("login");
            Scene loginScene = loginSceneData.getScene();
            primaryStage.setScene(loginScene);
        }

    }

    public void switchToSignupScene() {
            if (primaryStage == null) {
                throw new RuntimeException("Stage Coordinator should be initialized with a Stage before it could be used");
            }

            if (!scenes.containsKey("signUp")) {
                try {
                    System.out.println("Created New Scene");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/RegistrationView.fxml"));
                    Parent signUpView = fxmlLoader.load();
                    Scene signUpScene = new Scene(signUpView);
                    SceneData signUpSceneData = new SceneData(fxmlLoader, signUpView, signUpScene);
                    scenes.put("signUp", signUpSceneData);
                    primaryStage.setScene(signUpScene);
                } catch (IOException e) {
                    System.out.println("IO Exception: Couldn't load 'signUp View' FXML file");
                }
            } else {
                System.out.println("Loaded Existing Scene");
                SceneData signUpSceneData = scenes.get("signUp");
                Scene signUpScene = signUpSceneData.getScene();
                primaryStage.setScene(signUpScene);
            }
        }

    public void switchToHomeScene() {
        if (primaryStage == null) {
            throw new RuntimeException("Stage Coordinator should be initialized with a Stage before it could be used");
        }

        if (!scenes.containsKey("home")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeView.fxml"));
                Parent homeView = fxmlLoader.load();
                Scene homeScene = new Scene(homeView);
                SceneData homeSceneData = new SceneData(fxmlLoader, homeView, homeScene);
                scenes.put("home", homeSceneData);
                primaryStage.setScene(homeScene);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO Exception: Couldn't load 'Login View' FXML file");
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData homeSceneData = scenes.get("home");
            Scene homeScene = homeSceneData.getScene();
            primaryStage.setScene(homeScene);
        }

    }
}
