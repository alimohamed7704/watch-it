package Crew;


import SceneManager.SceneDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import Content.*;
import SceneManager.*;


import java.io.IOException;

public class CrewController  {

    @FXML
    private Label crewName , nationality, birthDate;

    @FXML
    private ImageView crewImageView;

    @FXML
    private Button watchIt, facebook,   instagram, twitter;

    @FXML
    private TilePane contentList ;

    // Set movie details

    @FXML
    public void  initialize() {

        crewName.setText(SceneDetails.crew.getName());
        nationality.setText(SceneDetails.crew.getNationality());
        birthDate.setText(SceneDetails.crew.getStringDateOfBirth());

        Tooltip tooltip = new Tooltip("This is a tooltip"); // Associate the tooltip with the button
        Tooltip.install(facebook, tooltip);

        try {
            crewImageView.setImage(new Image("file:"+SceneDetails.crew.getImagePath()));
        } catch (Exception e) {
            crewImageView.setImage(new Image("file:" + "src/main/resources/Styling/images/Avatar.png"));
        }

        //Crew list
        for (Content movie:SceneDetails.crew.getPreviousWorks()){
            AnchorPane moviePane = new AnchorPane();
            moviePane.setPrefWidth(250);
            moviePane.setPrefHeight(150);

            ImageView movieImageView = new ImageView(new Image("file:" + movie.getPosterPath()));
            movieImageView.setFitWidth(250);
            movieImageView.setFitHeight(138);
            movieImageView.setOnMouseClicked(event -> {
                try {
                    SceneDetails.currentContent=movie;
                    SceneManager.switchScene("/main/MoviePage.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Label nameLabel = new Label(movie.getTitle());
            nameLabel.setLayoutX(0);
            nameLabel.setLayoutY(135);
            nameLabel.setPrefWidth(175);
            nameLabel.setPrefHeight(30);
            nameLabel.setTextFill(Color.WHITE);
            nameLabel.setAlignment(Pos.BOTTOM_LEFT);
            nameLabel.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");

            moviePane.getChildren().addAll(movieImageView, nameLabel);

            contentList.getChildren().add(moviePane);
        }
    }


    public void MainPage() throws IOException {

        SceneManager.switchScene("/main/MainPage.fxml");
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

    @FXML
    private void previousScene() throws IOException {
        SceneDetails.scenes.pop();
        SceneManager.scene.setRoot(SceneDetails.scenes.peek());
    }


}