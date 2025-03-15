package Content;

import Profiles.Admin;
import SceneManager.SceneDetails;
import SceneManager.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import Content.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnlistedMoviesController {

    @FXML
    private HBox comedyBox;

    @FXML
    private HBox actionBox;

    @FXML
    private HBox dramaBox;

    @FXML
    private HBox romanticBox;

    @FXML
    private ScrollPane comedyScrollBar;

    @FXML
    private ScrollPane actionScrollBar;

    @FXML
    private ScrollPane dramaScrollBar;

    @FXML
    private ScrollPane romanticScrollBar;

    private List<Content> allContent = Admin.getUnlistedContent();

    private List<Content> comedyList = new ArrayList<Content>();
    private List<Content> actionList = new ArrayList<Content>();
    private List<Content> dramaList = new ArrayList<Content>();
    private List<Content> romanticList = new ArrayList<Content>();
    @FXML
    public void initialize() {

        for(Content content : allContent) {
            if(content.getGenres().contains("Comedy")) {
                comedyList.add(content);
            }
            if(content.getGenres().contains("Action")) {
                actionList.add(content);
            }
            if(content.getGenres().contains("Drama")) {
                dramaList.add(content);
            }
            if(content.getGenres().contains("Romance")) {
                romanticList.add(content);
            }
        }
        listsCreation(comedyBox,comedyList,"Comedy");
        listsCreation(actionBox,actionList,"Action");
        listsCreation(dramaBox,dramaList,"Drama");
        listsCreation(romanticBox,romanticList,"Romantic");
    }

    public void listsCreation(HBox box , List<Content> currentList, String genre)
    {
        List<Content> movieSubList = currentList.subList(0, Math.min(10, currentList.size()));
        for (Content movie : movieSubList) {

            //movie.crewList.add(new Crewc("maged Elkidwaney","Actor","comedy","Egyption",123231, "Pictures/MagedElkidwaney.png"));
            //movie.crewList.add(new Crewc("Noor Elnabawey","Actor","comedy","Egyption",123231, "Pictures/NoorElNabawy.png"));
            //movie.crewList.add(new Crewc("Asser Yassin","Actor","comedy","Egyption",123231, "Pictures/AsserYassin.png"));

            // Create AnchorPane

            AnchorPane moviePane = new AnchorPane();
            moviePane.setPrefWidth(200);
            moviePane.setPrefHeight(200);

            // Create ImageView for the poster
            String path = movie.getPosterPath().substring(movie.getPosterPath().indexOf("S")-1);
            ImageView movieImageView = new ImageView(path);
            movieImageView.setFitWidth(233);
            movieImageView.setFitHeight(112);
            movieImageView.setLayoutX(0);
            movieImageView.setLayoutY(0);
            movieImageView.setOnMouseClicked(event -> {
                    SceneDetails.currentContent = movie;
                    if(showAlert("Confirmation","Are you sure you want to list this content?"))
                    {
                        if(comedyList.remove(movie))
                            comedyBox.getChildren().remove(moviePane);
                        if(actionList.remove(movie))
                            actionBox.getChildren().remove(moviePane);
                        if(dramaList.remove(movie))
                            dramaBox.getChildren().remove(moviePane);
                        if(romanticList.remove(movie))
                            romanticBox.getChildren().remove(moviePane);
                        Admin.getUnlistedContent().remove(movie);
                        ContentManager.getContnetList().add(movie);
                        try {
                            SceneManager.switchScene("/Admin/Unlisted_Movies.fxml");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            });

            // Create Label for the movie title
            Label titleLabel = new Label(movie.getTitle());
            titleLabel.setLayoutX(0);
            titleLabel.setLayoutY(109);
            titleLabel.setPrefWidth(180);
            titleLabel.setPrefHeight(29);
            titleLabel.setTextFill(Color.WHITE);
            titleLabel.setAlignment(Pos.BASELINE_LEFT);
            titleLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");

            moviePane.getChildren().addAll(movieImageView, titleLabel);

            box.getChildren().add(moviePane);

        }
        AnchorPane moviePane = new AnchorPane();
        moviePane.setPrefWidth(200);
        moviePane.setPrefHeight(200);
        //moviePane

        ImageView moreImageView = new ImageView(new Image("/Styling/images/More.png"));
        moreImageView.setFitWidth(135);
        moreImageView.setFitHeight(73);
        moreImageView.setLayoutX(58);
        moreImageView.setLayoutY(26);

        moreImageView.setOnMouseClicked(event -> {
            try {
                SceneDetails.currentList = currentList;
                SceneDetails.title = genre;
                SceneManager.switchScene("/main/ContentContainerScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        moviePane.getChildren().addAll(moreImageView);

        box.getChildren().add(moviePane);
    }

    @FXML
    public void back()
    {
        try {
            SceneManager.switchScene("/Admin/Admin_dashboard.fxml");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void scrollRight(ScrollPane scrollPane) {
        double hValue = scrollPane.getHvalue();
        double newHValue = hValue + 0.1;
        if (newHValue <= 1.0) {
            scrollPane.setHvalue(newHValue);
        }
    }

    private void scrollLeft(ScrollPane scrollPane) {
        double hValue = scrollPane.getHvalue();
        double newHValue = hValue - 0.1;
        if (newHValue >= 0.0) {
            scrollPane.setHvalue(newHValue);
        }
    }

    @FXML
    private void scrollLeftComedy() {
        scrollLeft(comedyScrollBar);
    }

    @FXML
    private void scrollRightComedy() {
        scrollRight(comedyScrollBar);
    }

    @FXML
    private void scrollLeftAction(){
        scrollLeft(actionScrollBar);
    }

    @FXML
    private void scrollRightAction() {
        scrollRight(actionScrollBar);
    }

    @FXML
    private void scrollLeftDrama() {
        scrollLeft(dramaScrollBar);
    }

    @FXML
    private void scrollRightDrama() {
        scrollRight(dramaScrollBar);
    }

    @FXML
    private void scrollLeftRomantic() {
        scrollLeft(romanticScrollBar);
    }

    @FXML
    private void scrollRightRomantic() {
        scrollRight(romanticScrollBar);
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
