package Content;
import Profiles.WatchRecord;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SceneManager.*;
import Crew.*;
import javafx.stage.Stage;

import static SceneManager.SceneDetails.currentContent;
import static SceneManager.SceneDetails.user;

    public class MovieDetailsController {

    @FXML
    private Label movieDescription, releaseDate, rating, country, language , budget, revenue;

    @FXML
    private ImageView moviePosterImageView,movieLogoImageView;

    @FXML
    private HBox genres;

    @FXML
    private TilePane crewList;

    @FXML
    private Button watch;

    @FXML
    private Button watchLater;

    public void MainPage() throws IOException {

        SceneManager.switchScene("/main" +
                "/MainPage.fxml");
    }

    @FXML
    private void Search() throws IOException {
        SceneManager.switchScene("/Search/UserSearchScene.fxml");
    }

    @FXML
    private void toProfile() throws IOException {
        SceneManager.switchScene("/Profile/ProfileUI.fxml");
    }

    @FXML
    private void previousScene() throws IOException {
        SceneDetails.scenes.pop();
        SceneManager.scene.setRoot(SceneDetails.scenes.peek());
    }


    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;

    private List<ImageView> stars;
    private Image filledStar;
    private Image emptyStar;

    @FXML
    public void initialize() {

        filledStar = new Image("/Styling/images/Star.png");//getClass().getResource("main/resources/Styling/images/Star.png").toExternalForm());
        emptyStar = new Image("/Styling/images/EmptyStar.png");//getClass().getResource("main/resources/Styling/images/EmptyStar.png").toExternalForm());

        stars = new ArrayList<>();

        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);

        WatchRecord watchRecord = user.isContentFoundInWatchRecords(SceneDetails.currentContent);
        if(watchRecord!=null){highlightStars(watchRecord.getRate());}
        else highlightStars(0);

        // Attach click event to each star
        for (int i = 0; i < stars.size(); i++) {
            int starIndex = i;
            stars.get(i).setOnMouseClicked(event ->{ highlightStars(starIndex+1);user.Rate(currentContent,starIndex+1);rating.setText(String.valueOf(currentContent.getRating()));});
        }


        String path = ("file:"+SceneDetails.currentContent.getPosterPath());
        String Lpath = ("file:"+SceneDetails.currentContent.getLogoPath());


        moviePosterImageView.setImage(new Image(path));

        movieLogoImageView.setImage(new Image(Lpath));

        movieDescription.setText(SceneDetails.currentContent.getDescription());
        releaseDate.setText(SceneDetails.currentContent.getReleaseDateString());
        rating.setText(String.valueOf(SceneDetails.currentContent.getRating()));
        country.setText(SceneDetails.currentContent.getCountry());
        language.setText(SceneDetails.currentContent.getLanguage());
        budget.setText(String.valueOf(SceneDetails.currentContent.getBudget()));
        revenue.setText(String.valueOf(SceneDetails.currentContent.getRevenue()));


        watch.setOnMouseClicked(event -> {

            if(user.watchAContentIfYouCan(currentContent)){
                System.out.println(user.getNumberOfWatchedMovies());
            }

        });

        watchLater.setOnMouseClicked(event -> {
            user.addToWatchLater(currentContent);
        });

        for(String genre : SceneDetails.currentContent.getGenres()){

            Label genrelabel = new Label(genre);
            genrelabel.setLayoutX(0);
            genrelabel.setLayoutY(0);
            genrelabel.setPrefWidth(105);
            genrelabel.setPrefHeight(30);
            genrelabel.setTextFill(Color.WHITE);
            genrelabel.setAlignment(Pos.BASELINE_LEFT);
            genrelabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");

            genres.getChildren().add(genrelabel);
        }
        for (Crew crew:currentContent.getCrewList()){
            AnchorPane crewPane = new AnchorPane();
            crewPane.setPrefWidth(200);
            crewPane.setPrefHeight(200);

            ImageView crewImageView;
            try {
                crewImageView = new ImageView(new Image("file:"+crew.getImagePath()));
            } catch (Exception e) {
                crewImageView = new ImageView(new Image("file:" + "src/main/resources/Styling/images/Avatar.png"));
            }
            crewImageView.setFitWidth(220);
            crewImageView.setFitHeight(205);
            crewImageView.setOnMouseClicked(event -> {
                try {
                    SceneDetails.crew = crew;
                    SceneManager.switchScene("/Crew/Crew_Scene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Label nameLabel = new Label(crew.getName());
            nameLabel.setLayoutX(0); // Position relative to the AnchorPane
            nameLabel.setLayoutY(166); // Position below the image
            nameLabel.setPrefWidth(185);
            nameLabel.setPrefHeight(70);
            nameLabel.setTextFill(Color.WHITE);
            nameLabel.setAlignment(Pos.BOTTOM_LEFT);
            //nameLabel.setWrapText(true);
            nameLabel.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");

            crewPane.getChildren().addAll(crewImageView, nameLabel);

            crewList.getChildren().add(crewPane);
        }

    }

    private void highlightStars(int index) {
        for (int i = 0; i < stars.size(); i++) {
            if (i < index) {
                stars.get(i).setImage(filledStar);
            } else {
                stars.get(i).setImage(emptyStar);
            }
        }
    }



    @FXML
    protected void BackButton() {
        try {
            SceneManager.switchScene("src/main/resources/Search/UserSearchScene.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
