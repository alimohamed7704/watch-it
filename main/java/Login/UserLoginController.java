package Login;
import Profiles.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import SceneManager.*;
import SignUp.*;
import FileHandling.*;
import Profiles.*;
import java.io.IOException;
import java.util.List;

public class UserLoginController {

    @FXML
    TextField email = new TextField();
    @FXML
    TextField password = new TextField();

    @FXML
    Button signUpButton = new Button();

    List<Profile> profileList =  FileHandle.getProfileList();

    @FXML
    protected void OnButtonClick() throws IOException {
        boolean isFound = false;
        for(Profile profile : profileList){
            if(profile.getEmail().equals(email.getText()) && profile instanceof User){
                isFound = true;
                if (profile.getPassword().equals(password.getText())){
                    SceneDetails.user = (User)profile;
                    SceneManager.switchScene("/main/MainPage.fxml");
                }
                else {
                    password.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                    password.clear();
                    password.setPromptText("Wrong Password!! , Check it and try again");
                }
            }
        }
        if(!isFound){
            email.setStyle("-fx-border-color: red; -fx-border-width: 2;");

            email.clear();

            email.setPromptText("This Email is not registered , Check is email or sign Up");
        }
    }

    @FXML
    protected void onSignUp() throws IOException {
        SceneDetails.user = new User();
        SceneManager.switchScene("/SignUp/SignUp.fxml");
    }

    @FXML
    protected void AdminLogin() throws IOException {
        SceneManager.switchScene("/Login/AdminLoginPage.fxml");
    }



}
