package Profiles;

import Content.ContentManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Subscription.*;
import SceneManager.*;

import java.io.IOException;


public class AdminDashboardController {
    @FXML
    private Label basicPlanNumberOfUsers;

    @FXML
    private Label basicPlanPrice;

    @FXML
    private Label basicPlanRevenue;

    @FXML
    private Label standardPlanNumberOfUsers;

    @FXML
    private Label standardPlanPrice;

    @FXML
    private Label standardPlanRevenue;

    @FXML
    private Label premiumPlanNumberOfUsers;

    @FXML
    private Label premiumPlanPrice;

    @FXML
    private Label premiumPlanRevenue;

    @FXML
    private Label mostSubscribedPlan;

    @FXML
    private Label topRevenueMonth;

    @FXML
    private Label totalRevenue;

    @FXML
    private Button modifyContentButton;

    @FXML
    public void initialize() {
        basicPlanNumberOfUsers.setText(String.valueOf(Basic.getNumberOfUsers()));
        basicPlanPrice.setText(String.format("%.2f", Basic.getPlanPrice()));
        basicPlanRevenue.setText(String.format("%.2f", Basic.getTotalRevenue()));

        standardPlanNumberOfUsers.setText(String.valueOf(Standard.getNumberOfUsers()));
        standardPlanPrice.setText(String.format("%.2f", Standard.getPlanPrice()));
        standardPlanRevenue.setText(String.format("%.2f", Standard.getTotalRevenue()));

        premiumPlanNumberOfUsers.setText(String.valueOf(Premium.getNumberOfUsers()));
        premiumPlanPrice.setText(String.format("%.2f", Premium.getPlanPrice()));
        premiumPlanRevenue.setText(String.format("%.2f", Premium.getTotalRevenue()));

        mostSubscribedPlan.setText(Subscription.getMostSubscribedPlan());

        topRevenueMonth.setText(Subscription.getTheMonthWithMaximumRevenue());

        totalRevenue.setText(String.valueOf(Subscription.getTotalRevenue()));
    }

    @FXML
    public void modify() throws IOException {
        SceneManager.switchScene("/Search/AdminSearchScene.fxml");
    }

    @FXML
    public void add() throws IOException
    {
        SceneDetails.currentList = Admin.getUnlistedContent();
//        SceneDetails.title = "Unlisted Content";
//        SceneManager.switchScene("/main/ContentContainerScene.fxml");
        SceneManager.switchScene("/Admin/Unlisted_Movies.fxml");
    }

}
