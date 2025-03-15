package SignUp;

import Subscription.Basic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import SceneManager.SceneManager;

import java.io.IOException;
import java.time.LocalDate;

import static SceneManager.SceneDetails.user;

public class SubscriptionStepController {
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
    public void initialize() {
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Plan Selected");
            alert.setHeaderText("Please select a subscription plan to continue.");
            alert.showAndWait();
        } else {
            user.setSelectedPlan(selectedPlan);
            SceneManager.switchScene("/Payment/PaymentStepUI.fxml");
        }
    }

    @FXML
    protected void backButton() throws IOException {
        SceneManager.switchScene("/SignUp/SignUp.fxml");
    }
}