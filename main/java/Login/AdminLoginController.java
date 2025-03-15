package Login;

import FileHandling.FileHandle;
import Profiles.Profile;
import Profiles.User;
import Profiles.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import SceneManager.*;
import SignUp.*;

import java.io.IOException;
import java.util.List;

public class AdminLoginController {

    @FXML
    TextField email = new TextField();
    @FXML
    TextField password = new TextField();

    List<Profile> profileList =  FileHandle.getProfileList();

    @FXML
    protected void OnButtonClick() throws IOException {


            boolean isFound = false;
            for (Profile profile : profileList) {


                if (profile.getEmail().equals(email.getText()) && profile instanceof Admin) {
                    isFound = true;
                    if (profile.getPassword().equals(password.getText())) {
                        SceneDetails.admin = (Admin) profile;
                        SceneManager.switchScene("/Admin/Admin_dashboard.fxml");
                    } else {
                        password.setStyle("-fx-border-color: red; -fx-border-width: 2;");
                        password.clear();
                        password.setPromptText("Wrong Password!! , Check it and try again");
                    }
                }
            }
            if (!isFound) {
                email.setStyle("-fx-border-color: red; -fx-border-width: 2;");

                email.clear();

                email.setPromptText("This Email is not registered , Check is email or sign Up");
            }
        }

    @FXML
    protected void UserLogin() throws IOException {
        SceneManager.switchScene("/Login/UserLoginPage.fxml");
    }
}
