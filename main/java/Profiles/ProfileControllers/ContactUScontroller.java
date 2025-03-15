package Profiles.ProfileControllers;

import SceneManager.SceneManager;
import javafx.fxml.FXML;

import java.io.IOException;

public class ContactUScontroller {

    @FXML
    protected void SubscriptionButton() throws IOException {
        SceneManager.switchScene("/Profile/SubscriptionUI.fxml");
    }
    @FXML
    protected void BackButton() throws IOException {
        SceneManager.switchScene("/main/MainPage.fxml");
    }

    @FXML
    protected void ProfileButton() throws IOException {
        SceneManager.switchScene("/Profile/ProfileUI.fxml");
    }
    @FXML
    protected void PrivacyPolicyButton() throws IOException {
        SceneManager.switchScene("/Profile/PrivacyPolicy.fxml");
    }
}
