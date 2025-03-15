package Search;

import Content.Content;
import Profiles.Admin;
import Profiles.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import Content.ContentManager;
import SceneManager.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContentContainerController {
    @FXML
    private FlowPane contentFlowPane;
    @FXML
    private Label pageTitle;

    @FXML
    private Button backButton;

//    List<Content> contentList = ContentManager.getTrendingMovies();//for testing this should be dynamic
    @FXML
    private void initialize() {
        pageTitle.setText(SceneDetails.title);
        contentFlowPane.getChildren().clear();
        for (Content content : SceneDetails.currentList) {

            // Create a container for each content
            AnchorPane contentPane = new AnchorPane();
            contentPane.setPrefWidth(250);
            contentPane.setPrefHeight(150);
            contentFlowPane.setHgap(15); // 15px horizontal gap
            contentFlowPane.setVgap(15); // 15px vertical gap


            // Create an ImageView and set its size and alignment
            ImageView movieImageView = new ImageView();
            movieImageView.setFitWidth(250);
            movieImageView.setFitHeight(130);
            movieImageView.setOnMouseClicked(event -> {
                try {
                    if(SceneDetails.user != null) {
                        SceneDetails.currentContent = content;
                        SceneManager.switchScene("/main/MoviePage.fxml");
                    }
                    else {
                        if(showAlert("Confirmation","Are you sure you want to list this content?")){
                            Admin.getUnlistedContent().remove(content);
                            ContentManager.getContnetList().add(content);
                            contentFlowPane.getChildren().remove(contentPane);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            try {
                // Try loading the image
                String path = content.getPosterPath().substring(content.getPosterPath().indexOf("S")-1);
                Image image = new Image(path, 250, 130, false, true); // Preserve aspect ratio
                movieImageView.setImage(image);

            } catch (Exception e) {
                contentPane.setStyle(
                        "-fx-background-color: gray;" +
                                "-fx-background-radius: 5px;" +
                                "-fx-border-radius: 5px;" +
                                "-fx-border-width: 1px;"
                );
            }

            // Create a Label for the content title
            Label titleLabel = new Label(content.getTitle());
            titleLabel.setLayoutX(7);
            titleLabel.setLayoutY(139); // Positioned below the image
            titleLabel.setPrefWidth(233); // Align with the image width
            titleLabel.setTextFill(Color.WHITE);
            titleLabel.setAlignment(Pos.BASELINE_LEFT);
            titleLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

            // Add the ImageView and Label to the content pane
            contentPane.getChildren().addAll(movieImageView, titleLabel);

            // Add the content pane to the results container
            contentFlowPane.getChildren().add(contentPane);;
            contentFlowPane.setVisible(true);
        }

    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        //displayContent(contentList,"Trending");//for testing , should also be dynamic
//    }
    @FXML
    public void back() throws IOException {
        if(SceneDetails.user != null)
            SceneManager.switchScene("/main/MainPage.fxml");
        else
            SceneManager.switchScene("/Admin/Unlisted_Movies.fxml");
    }

    private boolean showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.initOwner(SceneManager.stage);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }



}
