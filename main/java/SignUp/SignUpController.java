package SignUp;

import FileHandling.*;
import Profiles.Profile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import SceneManager.*;
import Profiles.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static SceneManager.SceneDetails.user;
import java.util.ArrayList;

public class SignUpController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    Button signUpButton = new Button();

    @FXML
    protected void OnButtonClick() throws IOException {
        Boolean userValid = Validation.validateField(username);
        Boolean firstNameValid = Validation.validateField(firstName);
        Boolean lastNameValid = Validation.validateField(lastName);
        Boolean emailRequired = Validation.validateEmail(email);
        Boolean passwordRequired = Validation.validatePassword(password);
        if (userValid && firstNameValid && lastNameValid && emailRequired && passwordRequired) {
            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setUsername(username.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            SceneManager.switchScene("/Subscription/SubscriptionStepUI.fxml");

        }
    }
    @FXML
    protected void onSignIn() throws IOException {
        SceneManager.switchScene("/Login/UserLoginPage.fxml");
    }


    public void onSubmit() {
    }
}