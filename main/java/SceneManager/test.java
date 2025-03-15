//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.TilePane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import main.java.Content.Content;
//import main.java.Crew.Crew;
//import main.java.SceneManager.SceneManager;
//import main.java.SceneManager.UpdatableContent;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static main.java.SceneManager.SceneManager.user; /////////////////////
//
//public class CrewController implements UpdatableContent<Crew> {
//
//    @FXML
//    private Label crewName , nationality, birthDate;
//
//    @FXML
//    private ImageView crewImageView;
//
//    @FXML
//    private Button watchIt, facebook, instagram, twitter;
//
//    @FXML
//    private TilePane contentList ;
//
//    // Set movie details
//    public void  setCrewDetails(Crew crew) {
//
//        crewName.setText(crew.getName());
//        nationality.setText(crew.getNationality());
//        birthDate.setText(crew.getStringDateOfBirth());
//
//        Tooltip tooltip = new Tooltip("This is a tooltip"); // Associate the tooltip with the button
//        Tooltip.install(facebook, tooltip);
//
//        try {
//            crewImageView.setImage(new Image(crew.getImagePath()));
//        } catch (Exception e) {
//            crewImageView.setImage(new Image("file:" + "src/main/resources/Styling/images/Avatar.png"));
//        }
//
//        //Crew list
//        for (Content movie:crew.getPreviousWorks()){
//            AnchorPane moviePane = new AnchorPane();
//            moviePane.setPrefWidth(250);
//            moviePane.setPrefHeight(150);
//
//            ImageView movieImageView = new ImageView(new Image("file:" + movie.getPosterPath()));
//            movieImageView.setFitWidth(250);
//            movieImageView.setFitHeight(138);
//            movieImageView.setOnMouseClicked(event -> {
//                try {
//                    SceneManager.switchScene("/main/resources/main/MoviePage.fxml",movie);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//
//            Label nameLabel = new Label(movie.getTitle());
//            nameLabel.setLayoutX(0);
//            nameLabel.setLayoutY(135);
//            nameLabel.setPrefWidth(175);
//            nameLabel.setPrefHeight(30);
//            nameLabel.setTextFill(Color.WHITE);
//            nameLabel.setAlignment(Pos.BOTTOM_LEFT);
//            nameLabel.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
//
//            moviePane.getChildren().addAll(movieImageView, nameLabel);
//
//            contentList.getChildren().add(moviePane);
//        }
//    }
//
//
//    public void MainPage() throws IOException {
//
//        SceneManager.switchScene("/main/resources/main/MainPage.fxml");
//    }
//
//    @FXML
//    private void Search() throws IOException {
//        SceneManager.switchScene("/main/resources/Search/SearchScene.fxml", user);
//    }
//
//    public void setData(Crew content)
//    {
//        setCrewDetails(content);
//    }
//}