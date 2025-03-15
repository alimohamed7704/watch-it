package Profiles.ProfileControllers.SubscriptionUpgrade;

import Profiles.User;
import Subscription.Basic;
import Subscription.Premium;
import Subscription.Standard;
import Subscription.Subscription;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import SceneManager.SceneManager;
import javafx.scene.layout.*;
import SceneManager.SceneDetails.*;
import java.io.IOException;
import java.time.LocalDate;

import static SceneManager.SceneDetails.user;

public class SubscriptionUpgradeController {

    @FXML
    private ToggleButton basicButton;

    @FXML
    private ToggleButton standardButton;

    @FXML
    private ToggleButton premiumButton;

    @FXML
    private ToggleGroup packageToggleGroup;

    private String selectedPlan;

    @FXML
    private TextField balanceField = new TextField();
    private double balance;

    LocalDate today = LocalDate.now();
    @FXML
    BorderPane borderPane = new BorderPane();

    @FXML
    public void initialize() {
        Platform.runLater(() -> SceneManager.scene.getRoot().requestFocus());
        // Initialize balance
        balanceField.setEditable(false);
        balanceField.setText(String.valueOf(user.getBalance()));
        balance = user.getBalance();

        // Add buttons to the group
        packageToggleGroup = new ToggleGroup();
        basicButton.setToggleGroup(packageToggleGroup);
        standardButton.setToggleGroup(packageToggleGroup);
        premiumButton.setToggleGroup(packageToggleGroup);

        // Initialize selectedPlan
        packageToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedPlan = ((ToggleButton) newValue).getText();
            } else {
                selectedPlan = null;
            }
        });




    }

    @FXML
    public void onNextButtonClick() throws Exception {
        if (selectedPlan == null) {
            showAlert("No Plan Selected" ,"Please select a subscription plan to continue.");
            return;
        }

        switch (selectedPlan) {
            case "Basic":
                processSubscription(new Basic(user.getID(), today), Basic.getPlanPrice());
                break;
            case "Standard":
                processSubscription(new Standard(user.getID(), today), Standard.getPlanPrice());
                break;
            case "Premium":
                processSubscription(new Premium(user.getID(), today), Premium.getPlanPrice());
                break;
            default:
                showAlert("Invalid Selection", "The selected plan is not recognized.");
                break;
        }

    }

    private void processSubscription(Subscription subscription, double price) throws IOException {
        if (user.getBalance() >= price) {
            user.setSubscription(subscription);
            user.setNumberOfWatchedMovies(0);
            showAlert("Subscription Successful", "Your subscription has been updated to " + selectedPlan + "!");
            user.setBalance(user.getBalance() - price);
            SceneManager.switchScene("/Profile/SubscriptionUI.fxml");
        } else {
            showAlert("Insufficient Balance", "You do not have enough balance for the " + selectedPlan + " plan.");
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.initOwner(SceneManager.getStage());
        alert.showAndWait();
    }

//    private void showSuccess(String message) {
//        Text toastMessage = new Text(message);
//        toastMessage.setStyle("-fx-fill: white; -fx-font-size: 14px; -fx-font-weight: bold");
//
//        StackPane toastPane = new StackPane(toastMessage);
//        toastPane.setStyle("-fx-background-color: green; -fx-background-radius: 10; -fx-padding: 10;");
//
//        toastPane.setTranslateX(borderPane.getWidth()/2 -12 );
//        toastPane.setTranslateY(borderPane.getHeight()/2+480);
//
//        borderPane.getChildren().add(toastPane);
//
//        FadeTransition fade = new FadeTransition(Duration.seconds(5), toastPane);
//        fade.setFromValue(1.0);
//        fade.setToValue(0.0);
//        fade.setOnFinished(event -> borderPane.getChildren().remove(toastPane));
//        fade.play();
//    }

    @FXML
    protected void backButton() throws IOException {
        SceneManager.switchScene("/Profile/SubscriptionUI.fxml");
    }

    @FXML
    public void RechargeButton() throws IOException {
        SceneManager.switchScene("/Payment/PaymentStepUI.fxml");
    }
}
