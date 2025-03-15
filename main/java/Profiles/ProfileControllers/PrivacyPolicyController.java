package Profiles.ProfileControllers;

import javafx.fxml.FXML;

import java.io.IOException;
import SceneManager.*;

public class PrivacyPolicyController {
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
    protected void ContactUsButton() throws IOException {
        SceneManager.switchScene("/Profile/ContactUS.fxml");
    }

}
