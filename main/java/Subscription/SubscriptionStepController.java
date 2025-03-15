package main.java.Subscription;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import main.java.SceneManager.*;

import java.io.IOException;
public class SubscriptionStepController {
    @FXML
    private ToggleButton basicButton;

    @FXML
    private ToggleButton standardButton;

    @FXML
    private ToggleButton premiumButton;
    @FXML
    private ToggleGroup packageToggleGroup;
    @FXML
    public void initialize() {
        // Add buttons to the group
        packageToggleGroup = new ToggleGroup();
        basicButton.setToggleGroup(packageToggleGroup);
        standardButton.setToggleGroup(packageToggleGroup);
        premiumButton.setToggleGroup(packageToggleGroup);

    }
    @FXML
    public void onNextButtonClick() throws Exception {
        SceneManager.switchScene("/Payment/PaymentStepUI.fxml");
        if(basicButton.isSelected()){
            System.out.println(basicButton.getText());
        } else if (standardButton.isSelected()) {
            System.out.println(standardButton.getText());
        }else if (premiumButton.isSelected()){
            System.out.println(premiumButton.getText());
        }
    }

    @FXML
    protected void backButton() throws IOException {
        SceneManager.switchScene("/SignUp/SignUp.fxml");
    }



}
