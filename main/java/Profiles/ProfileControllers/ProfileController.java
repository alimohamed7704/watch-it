package Profiles.ProfileControllers;

import FileHandling.FileHandle;
import Profiles.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

import SceneManager.*;

import static SceneManager.SceneDetails.user;

public class ProfileController {


    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField userID;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button showPassword;
    @FXML
    private Button showBalance;
    @FXML
    private PasswordField balance;
    @FXML
    private TextField balanceTextField;



    private boolean isPasswordVisible = false;
    private boolean isBalanceVisible = false;

    @FXML
    protected void initialize() {
        setEditable();
        setAllText();
        Platform.runLater(() -> SceneManager.scene.getRoot().requestFocus());
    }

    private void setAllText() {
        name.setText(user.getFirstName() + " " +user.getLastName());
        username.setText(user.getUsername());
        userID.setText(String.valueOf(user.getID()));
        email.setText(user.getEmail());

        passwordTextField.setVisible(false);
        passwordTextField.setManaged(false);
        password.setText(user.getPassword());
        passwordTextField.setText(user.getPassword());

        balanceTextField.setVisible(false);
        balanceTextField.setManaged(false);
        balance.setText(String.valueOf(((User)(user)).getBalance()));
        balanceTextField.setText(String.valueOf(((User)(user)).getBalance()));
    }

    private void setEditable() {
        name.setEditable(false);
       // username.setEditable(false);
        userID.setEditable(false);
        email.setEditable(false);
        password.setEditable(false);
      //  passwordTextField.setEditable(false);
        balance.setEditable(false);
        balanceTextField.setEditable(false);
    }

    @FXML
    protected void SubscriptionButton() throws IOException {
        SceneManager.switchScene("/Profile/SubscriptionUI.fxml");
    }
    @FXML
    protected void BackButton() throws IOException {
        SceneManager.switchScene("/main/MainPage.fxml");
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
    protected void ShowBalanceButton() {
        if (isBalanceVisible) {
            balanceTextField.setVisible(false);
            balanceTextField.setManaged(false);
            balance.setVisible(true);
            balance.setManaged(true);
            showBalance.setText("Show Balance");
        } else {
            balance.setVisible(false);
            balance.setManaged(false);
            balanceTextField.setVisible(true);
            balanceTextField.setManaged(true);
            showBalance.setText("Hide Balance");
        }
        isBalanceVisible = !isBalanceVisible;
    }

    @FXML
    protected void ShowPasswordButton() {
        if (isPasswordVisible) {
            passwordTextField.setVisible(false);
            passwordTextField.setManaged(false);
            password.setVisible(true);
            password.setManaged(true);
            showPassword.setText("Show Password");
        } else {
            password.setVisible(false);
            password.setManaged(false);
            passwordTextField.setVisible(true);
            passwordTextField.setManaged(true);
            showPassword.setText("Hide Password");
        }
        isPasswordVisible = !isPasswordVisible;
    }
    @FXML
    protected void SaveChanges() throws IOException {
        user.setUsername(username.getText());
        user.setPassword(passwordTextField.getText());
    }
    @FXML
    protected void DeleteUser() throws IOException {
        boolean isConfirmed = showConfirmationAlert("Account Deletion", "Are you sure you want to delete your account?");
        if (isConfirmed) {
            FileHandle.getProfileList().remove(user);
            showAlert("Account Deleted", "Your account has been successfully deleted.");
            SceneManager.switchScene("/Login/UserLoginPage.fxml");

        }
    }

    private boolean showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.initOwner(SceneManager.getStage());

        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == okButton;
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.initOwner(SceneManager.getStage());
        alert.showAndWait();
    }


}
