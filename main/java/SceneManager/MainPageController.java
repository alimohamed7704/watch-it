package SceneManager;
import Profiles.User;
import Profiles.WatchRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Content.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static SceneManager.SceneDetails.user;


public class MainPageController  {

    private ContentManager contentManager;


    @FXML
    private HBox For_You, Trending, Recently_Added ,History ,Watch_Later ; // The HBox where the movies will be displayed dynamically

    @FXML
    private ScrollPane Categories, For_YouScrollBar, HistoryScrollBar, Recently_AddedScrollBar, TrendingScrollBar, Watch_LaterScrollBar;

    @FXML
    private Button searchButton;

    @FXML
    public void initialize() {

        listsCreation(For_You,user.getRecommendation(),"For You!");
        listsCreation(Trending,ContentManager.getTrendingMovies(),"Trending");//.subList(0, Math.min(ContentManager.getTrendingMovies().size(), 10)));
        listsCreation(Recently_Added,ContentManager.getNewReleasesContent(),"Recently Added");//.subList(0, Math.min(ContentManager.getNewReleasesContent().size(), 10)));
        listsCreation(History,historyList(),"History");//
        listsCreation(Watch_Later,user.getWatchLater(),"Watch_later");

    }

    public void listsCreation(HBox list, List<Content> movieList,String title){
        List<Content> movieSubList = movieList.subList(0, Math.min(10, movieList.size()));
        for (Content movie : movieSubList) {

            AnchorPane moviePane = new AnchorPane();
            moviePane.setPrefWidth(200);
            moviePane.setPrefHeight(200);

            // Create ImageView for the poster
            String path = ("file:"+movie.getPosterPath());
            ImageView movieImageView = new ImageView(path);
            movieImageView.setFitWidth(233);
            movieImageView.setFitHeight(112);
            movieImageView.setLayoutX(0);
            movieImageView.setLayoutY(0);
            movieImageView.setOnMouseClicked(event -> {
                try {
                    SceneDetails.currentContent = movie;
                    SceneManager.switchScene("/main/MoviePage.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
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

            list.getChildren().add(moviePane);

        }
        if(movieList.size()>10) {
        AnchorPane moviePane = new AnchorPane();
        moviePane.setPrefWidth(200);
        moviePane.setPrefHeight(200);
        //moviePane

        ImageView moreImageView = new ImageView(new Image("/Styling/images/More.png"));
        moreImageView.setFitWidth(135);
        moreImageView.setFitHeight(73);
        moreImageView.setLayoutX(58);
        moreImageView.setLayoutY(26);

        moviePane.setOnMouseClicked(event -> {
            try {
                SceneDetails.currentList = movieList;
                SceneDetails.title = title;
                SceneManager.switchScene("/main/ContentContainerScene.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        moviePane.getChildren().addAll(moreImageView);

        list.getChildren().add(moviePane);
    }
    }

    public List<Content> historyList(){
        List<Content> userHistory=new ArrayList<>();
        for(WatchRecord watchedMovie: (user).getWatchRecords()){
            if(!watchedMovie.getDateOfWatching().isBefore(LocalDate.parse("1900-01-01")))
                userHistory.add(watchedMovie.getContent());
        }
        Collections.reverse(userHistory);
        return userHistory;
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
    private void scrollLeftWatch_LaterScrollBar() {scrollLeft(Watch_LaterScrollBar);}

    @FXML
    private void scrollRightWatch_LaterScrollBar() {scrollRight(Watch_LaterScrollBar);}

    @FXML
    private void scrollLeftCategories() {scrollLeft(Categories);}

    @FXML
    private void scrollRightCategories() {scrollRight(Categories);}

    @FXML
    private void scrollLeftFor_You() {scrollLeft(For_YouScrollBar);}

    @FXML
    private void scrollRightFor_You() {scrollRight(For_YouScrollBar);}

    @FXML
    private void scrollRightHistoryScrollBar() {scrollRight(HistoryScrollBar);}
    
    @FXML
    private void scrollLeftHistoryScrollBar() {scrollLeft(HistoryScrollBar);}

    @FXML
    private void scrollLeftRecently_AddedScrollBar() {
        scrollLeft(Recently_AddedScrollBar);
    }

    @FXML
    private void scrollRightRecently_AddedScrollBar() {
        scrollRight(Recently_AddedScrollBar);
    }

    @FXML
    private void scrollLeftTrendingScrollBar() {
        scrollLeft(TrendingScrollBar);
    }

    @FXML
    private void scrollRightTrendingScrollBar() {
        scrollRight(TrendingScrollBar);
    }

    @FXML
    private void toProfile() throws IOException
    {
        SceneManager.switchScene("/Profile/ProfileUI.fxml");
    }

    @FXML
    public void search() throws IOException {
        SceneManager.switchScene("/Search/UserSearchScene.fxml");
    }
}
