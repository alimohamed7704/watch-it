package Payment;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import SceneManager.*;

import java.io.IOException;

public class PaymentStepController {
    @FXML
    private RadioButton creditCard;
    @FXML
    private RadioButton cash;

    private ToggleGroup packageToggleGroup;

    public void initialize() {
        // Add buttons to the group
        packageToggleGroup = new ToggleGroup();
        creditCard.setToggleGroup(packageToggleGroup);
        cash.setToggleGroup(packageToggleGroup);

    }
    @FXML
    public void onNextButtonClick() throws Exception {
        //  SceneManager.switchScene("PaymentStepUI.fxml");
        if(creditCard.isSelected()){
            System.out.println(creditCard.getText());
        } else if (cash.isSelected()) {
            System.out.println(cash.getText());}
    }


    @FXML
    protected void backButton() throws IOException {
        SceneManager.switchScene("SubscriptionStepUI.fxml");
    }


    @FXML
    protected void CreditCardDetails() throws IOException {
        SceneManager.switchScene("/Payment/PaymentStepUIcc.fxml");
    }

    @FXML
    protected void CashDetails() throws IOException {
        SceneManager.switchScene("/Payment/PaymentStepUIc.fxml");
    }


}