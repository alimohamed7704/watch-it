package Profiles.ProfileControllers;

import Profiles.User;
import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;
import SceneManager.*;
import javafx.scene.control.TextField;

import static SceneManager.SceneDetails.user;
import static SceneManager.SceneManager.scene;
import java.time.LocalDate;
public class SubscriptionTapController {
    LocalDate today = LocalDate.now();

    @FXML
    private TextField subType;
    @FXML
    private TextField price;
    @FXML
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private TextField moviesLeft;
    @FXML
    private TextField maxMovies;

    @FXML
    protected void initialize() {
        Editable();
        Platform.runLater(() -> SceneManager.scene.getRoot().requestFocus());

        if(user.getSubscription() != null) {

            if (user.getSubscription().getEnd_date().isAfter(today)) {
                subType.setText(user.getSubscription().getType());
                startDate.setText(String.valueOf(user.getSubscription().getStart_date()));
                endDate.setText(String.valueOf(user.getSubscription().getEnd_date()));
                price.setText(String.valueOf(user.getSubscription().getPrice()));
                maxMovies.setText(String.valueOf(user.getSubscription().getMaximumNumberOfMovies()));
                int numberOfMoviesLeft = Math.max(0,user.getSubscription().getMaximumNumberOfMovies()-(user.getNumberOfWatchedMovies()));
                moviesLeft.setText(String.valueOf(numberOfMoviesLeft));

            } else if (user.getSubscription().getEnd_date().isBefore(today)) {
                subType.setText("Plan Expired");
            }
        }


    }

    private void Editable() {
        subType.setEditable(false);
        price.setEditable(false);
        startDate.setEditable(false);
        endDate.setEditable(false);
        moviesLeft.setEditable(false);
        maxMovies.setEditable(false);
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
    @FXML
    protected void ContactUsButton() throws IOException {
        SceneManager.switchScene("/Profile/ContactUS.fxml");
    }

    @FXML
    protected void UpgradePlanButton() throws IOException {
        SceneManager.switchScene("/Profile/UpgradePlan/SubscriptionUpgradeUI.fxml");
    }


}
