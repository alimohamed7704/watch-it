package SignUp;
import java.util.regex.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.TextField;

import Profiles.*;
import FileHandling.*;

public class Validation {

    public static boolean validateField(TextField field) {

        if (field.getText().isEmpty()) {

            field.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: black");

            field.setPromptText("Field cannot be empty");
        }
        else {
            field.setStyle("-fx-border-color: transparent; -fx-background-color: black;");
            field.setPromptText("");
        }
        return (!field.getText().isEmpty());
    }

    public static boolean validateEmail(TextField field) {

        String email = field.getText();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        Matcher matcher = pattern.matcher(email);

        boolean isFound = false;

        for(Profile profile : FileHandle.getProfileList())
        {
            if(profile.getEmail().equals(email))
            {
                isFound = true;
                break;
            }
        }

        if(!matcher.matches() || isFound) {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: black;");

            field.clear();

            field.setPromptText("Please Enter A Valid Email Address");
        }
        else {
            field.setStyle("-fx-border-color: transparent; -fx-background-color: black");
            field.setPromptText("");
        }
        return matcher.matches();
    }

    public static boolean validatePassword(TextField field) {
        String password = field.getText();

        String passwordRegex = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";

        Pattern pattern = Pattern.compile(passwordRegex);

        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: black; -fx-font-size: 17px;");

            field.clear();
            field.setPromptText("Password must be at least 8 characters long with one special character.");
        }
        else {
            field.setStyle("-fx-border-color: transparent; -fx-background-color: black");
            field.setPromptText("");
        }
        return matcher.matches();
    }

    public static boolean validateCardNumber(TextField field)
    {
        if(field.getText().length() != 16)
        {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-background-color: black; -fx-font-size: 17px;");

            field.clear();
            field.setPromptText("Card number must consist of exactly 16 number");
        }
        else {
            field.setStyle("-fx-border-color: transparent; -fx-background-color: black");
            field.setPromptText("");
        }
        return (field.getText().length() == 16);
    }

    public static boolean validateBalance(TextField field)
    {
        for(char c : field.getText().toCharArray())
        {
            if(!Character.isDigit(c))
                return false;
        }
        return (!field.getText().isEmpty());
    }
}