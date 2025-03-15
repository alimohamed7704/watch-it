package Payment;
import FileHandling.FileHandle;
import Profiles.User;
import SignUp.Validation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import SceneManager.*;
import Subscription.Basic;
import Subscription.Premium;
import Subscription.Standard;
import Subscription.Subscription;
import java.time.LocalDate;
import java.io.IOException;

import static SceneManager.SceneDetails.user;

public class PaymentStepccController {

    @FXML
    private RadioButton creditCard;
    @FXML
    private RadioButton cash;

    private ToggleGroup packageToggleGroup;

    @FXML
    private TextField balance = new TextField();

    @FXML
    private TextField creditCardNumber = new TextField();
    LocalDate today = LocalDate.now();

    public void initialize() {
        // Add buttons to the group
        packageToggleGroup = new ToggleGroup();
        creditCard.setToggleGroup(packageToggleGroup);
        cash.setToggleGroup(packageToggleGroup);
    }
    @FXML
    protected void CashDetails() throws IOException {
        SceneManager.switchScene("/Payment/PaymentStepUIc.fxml");
    }
    @FXML
    protected void backButton() throws IOException {
        if(user.getBalance() == 0.0) {
            SceneManager.switchScene("/Subscription/SubscriptionStepUI.fxml");
        }else {
            SceneManager.switchScene("/Profile/UpgradePlan/SubscriptionUpgradeUI.fxml");
        }
    }

    private void processSubscription(Subscription subscription, double price) throws IOException {
        if (user.getBalance() >= price) {
            user.setSubscription(subscription);
            user.setNumberOfWatchedMovies(0);
            user.setBalance(user.getBalance() - price);
        } else {
            showAlert("Insufficient Balance", "You do not have enough balance for the " + user.getSelectedPlan() + " plan.");
        }
    }

    @FXML
    protected void NextButton() throws IOException {
        boolean newUser = false;
        if(Validation.validateCardNumber(creditCardNumber)){
            if(Validation.validateBalance(balance))
            {
                if(user.getBalance() == 0.0){
                    newUser = true;
                }
                    double balanceAmount = Double.parseDouble(balance.getText());
                    user.setBalance( user.getBalance() + balanceAmount);
                switch (user.getSelectedPlan()) {
                    case "Basic":
                        processSubscription(new Basic(user.getID(), today), Basic.getPlanPrice());
                        break;
                    case "Standard":
                        processSubscription(new Standard(user.getID(), today), Standard.getPlanPrice());
                        break;
                    case "Premium":
                        processSubscription(new Premium(user.getID(), today), Premium.getPlanPrice());
                        break;
                }

                if(newUser) {
                    FileHandle.getProfileList().add(user);
                    newUser = false;
                    SceneManager.switchScene("/Login/UserLoginPage.fxml");
                }else {
                    SceneManager.switchScene("/Profile/SubscriptionUI.fxml");
                }

            }
                else{
                    balance.clear();
                    balance.setPromptText("enter numerical values");
                    balance.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: black; ");
            }
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.initOwner(SceneManager.getStage());
        alert.showAndWait();
    }
}