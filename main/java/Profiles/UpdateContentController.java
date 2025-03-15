package Profiles;

import SceneManager.SceneManager;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Content.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import SceneManager.SceneDetails;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static SceneManager.SceneDetails.currentContent;

public class UpdateContentController  {
    @FXML
    private TextField title;

    @FXML
    private TextField country;

    @FXML
    private TextField language;

    @FXML
    private TextField releaseDate;

    @FXML
    private TextField description;

    @FXML
    private TextField posterURL;

    @FXML
    private ComboBox firstGenre;

    @FXML
    private ComboBox secondGenre;

    @FXML
    private TextField numberOfEpisodes;

    @FXML
    private ImageView BackgroundPhoto;

    @FXML
    private AnchorPane rootPane;

    private List<String> currentGenres = currentContent.getGenres();

    @FXML
    public void initialize() {
        this.title.setText(currentContent.getTitle());

        this.country.setText(currentContent.getCountry());

        this.language.setText(currentContent.getLanguage());

        this.releaseDate.setText(currentContent.getReleaseDateString());

        this.description.setText(currentContent.getDescription());

        this.posterURL.setText(currentContent.getPosterPath());



        String[] genres = {"Drama", "Thriller", "Mystery", "Action", "Adventure"};

        ObservableList<String> observableGenres = FXCollections.observableArrayList(genres);

        this.firstGenre.setItems(observableGenres);

        this.secondGenre.setItems(observableGenres);

        this.firstGenre.setPromptText(currentGenres.get(0));
        if(currentGenres.size() > 1)
            this.secondGenre.setPromptText(currentGenres.get(1));

        if(currentContent instanceof Movie)
            this.numberOfEpisodes.setText("1");

        if(currentContent instanceof Show)
            this.numberOfEpisodes.setText(String.valueOf(((Show) currentContent).getNumberOfEpisodes()));

        String imagePath = currentContent.getPosterPath(); // Ensure this is a valid URL or file path
        try {
            String path = imagePath.substring(imagePath.indexOf("S")-1);
            Image image = new Image(path, true); // Use `true` to load asynchronously
            this.BackgroundPhoto.setImage(image);
        } catch (IllegalArgumentException e) {
            System.out.println("Error loading image from path: " + imagePath);
        }

    }

    @FXML
    public void save()
    {
        try {
            currentContent.setTitle(title.getText());
            currentContent.setCountry(country.getText());
            currentContent.setLanguage(language.getText());
            currentContent.setReleaseDate(LocalDate.parse(releaseDate.getText()));
            currentContent.setDescription(description.getText());
            currentContent.setPosterPath(posterURL.getText());
            if(!firstGenre.getSelectionModel().isEmpty())
                currentGenres.set(0,firstGenre.getSelectionModel().getSelectedItem().toString());
            if(currentGenres.size() > 1 && !secondGenre.getSelectionModel().isEmpty())
                currentGenres.set(1,secondGenre.getSelectionModel().getSelectedItem().toString());
            else if(!secondGenre.getSelectionModel().isEmpty())
                currentGenres.add(secondGenre.getSelectionModel().getSelectedItem().toString());
            showSuccess("Changes saved successfully!");

        } catch (Exception e) {
            showSuccess("Error saving changes. Please check your input.");
        }
    }

    @FXML
    public void delete() throws IOException {
        List<Content>toAdd = Admin.getUnlistedContent();
        if(showAlert("Confirmation","Are you sure you want to delete this content?"))
        {
            for(Content content : ContentManager.getContnetList())
                {
                    if(content.getTitle().equals(title.getText()))
                    {
                        ContentManager.getContnetList().remove(content);
                        toAdd.add(content);
                        back();
                        break;
                    }
                }
        }
    }

    @FXML
    public void back() throws IOException
    {
        SceneManager.switchScene("/Search/AdminSearchScene.fxml");
    }

    private void showSuccess(String message) {
        Text toastMessage = new Text(message);
        toastMessage.setStyle("-fx-fill: white; -fx-font-size: 14px;");

        StackPane toastPane = new StackPane(toastMessage);
        toastPane.setStyle("-fx-background-color: green; -fx-background-radius: 10; -fx-padding: 10;");
        toastPane.setTranslateX(rootPane.getWidth()/2 - 50);
        toastPane.setTranslateY(rootPane.getHeight()/2);

        rootPane.getChildren().add(toastPane);

        FadeTransition fade = new FadeTransition(Duration.seconds(3), toastPane);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(event -> rootPane.getChildren().remove(toastPane));
        fade.play();
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
