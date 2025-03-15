package SceneManager;
//class to control the flow of the program
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.Map;

import Profiles.*;

public class SceneManager {
    public static Stage stage;

    public static Scene scene;

    public static MediaView mediaView;

    public static Media media;

    public static MediaPlayer mediaPlayer;

    public static Group group;

    public static final double screenWidth = Screen.getPrimary().getBounds().getWidth();

    public static final double screenHeight = Screen.getPrimary().getBounds().getHeight();

    private static final Map<String, Double> scale = Map.ofEntries(
            Map.entry("/Login/AdminLoginPage.fxml", 1.0),
            Map.entry("/Login/UserLoginPage.fxml", 1.0),
            Map.entry("/Payment/PaymentStepUI.fxml", 1.0),
            Map.entry("/Payment/PaymentStepUIc.fxml", 1.0),
            Map.entry("/Payment/PaymentStepUIcc.fxml", 1.0),
            Map.entry("/Profile/ProfileUI.fxml", 1.0),
            Map.entry("/Subscription/SubscriptionStepUI.fxml", 1.0),
            Map.entry("/SignUp/SignUp.fxml", 1.0),
            Map.entry("/Profile/SubscriptionUI.fxml", 1.0),
            Map.entry("/Profile/PrivacyPolicy.fxml", 1.0),
            Map.entry("/Profile/UpgradePlan/SubscriptionUpgradeUI.fxml", 1.0),
            Map.entry("/Profile/ContactUS.fxml", 1.0)
    );

    public static Profile user;

    public static void init(Stage stage) throws IOException {
        SceneManager.stage = stage;

        String mediaPath = SceneManager.class.getResource("/main/Videos/intro.mp4").toString();
        SceneManager.mediaPlayer = new MediaPlayer(new Media(mediaPath));
        SceneManager.mediaPlayer.setAutoPlay(true);

        SceneManager.mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.setFitWidth(screenWidth);
        mediaView.setFitHeight(screenHeight);
        SceneManager.mediaPlayer.setOnError(() ->
                {
                    try {
                        SceneManager.switchScene("/Login/UserLoginPage.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

        );

        SceneManager.scene = new Scene(new Group(mediaView), screenWidth, screenHeight);
        SceneManager.stage.setScene(scene);
        SceneManager.stage.setFullScreen(true);
        SceneManager.stage.setFullScreenExitHint("");
        SceneManager.stage.setTitle("WATCH IT");
        SceneManager.stage.show();

        mediaPlayer.setOnEndOfMedia(() -> {//displays the next scene when the intro ends
            mediaPlayer.stop();
            try {
                SceneManager.switchScene("/Login/UserLoginPage.fxml");
            } catch (IOException e) {
                throw new RuntimeException("Failed to load next scene", e);
            }
        });
    }



public static void switchScene(String fxml) throws IOException {
    final double initWidth = 1920 / ((scale.containsKey(fxml) ? scale.get(fxml) : 1.25));

    final double initHeight = 1080 / ((scale.containsKey(fxml) ? scale.get(fxml) : 1.25));

    final Pane root = new Pane();

    FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxml));

    Pane controller = fxmlLoader.load();
    controller.setPrefWidth(initWidth);
    controller.setPrefHeight(initHeight);
    root.getChildren().add(controller);

    SceneDetails.scenes.push(root);

    Scale scale = new Scale(1, 1, 0, 0);
    scale.xProperty().bind(root.widthProperty().divide(initWidth));
    scale.yProperty().bind(root.heightProperty().divide(initHeight));
    root.getTransforms().add(scale);

    SceneManager.scene.setRoot(root);
}
    public static Stage getStage()
    {
        return stage;
    }

}
