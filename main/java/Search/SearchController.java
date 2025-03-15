package Search;

import Content.Content;
import Crew.Crew;
import Profiles.UpdateContentController;
import SceneManager.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {

    @FXML
    private TextField searchBar;

    @FXML
    private TextField minRating;
    @FXML
    private TextField maxRating;
    @FXML
    private TextField minDuration;
    @FXML
    private TextField maxDuration;
    @FXML
    private Button searchButton;

    @FXML
    private RadioButton contentRadioButton;
    @FXML
    private RadioButton crewRadioButton;


    @FXML
    private ListView<CheckBox> genreListView;

    @FXML
    private ListView<CheckBox> languageListView;

    @FXML
    private AnchorPane filtersMenu;

    @FXML
    private Button filtersToggleButton;

    @FXML
    private ToggleGroup searchTypeGroup;

    private String[] genres = {"Drama", "Thriller", "Mystery", "Action", "Adventure"};
    private String[] languages = {"Arabic", "English", "French", "Spanish", "German"};

    @FXML
    private FlowPane resultsContainer;

    private String searchType = "content";
    @FXML
    private void handleSearch(){
        genreListView.setVisible(false);
        languageListView.setVisible(false);
        resultsContainer.getChildren().clear();
        String searchText = searchBar.getText();
        if(searchType.equalsIgnoreCase("content")){
            List<Content> searchResultContent = Search.SearchContent(searchText);
            List<String> selectedLanguages = getSelectedFilters(languageListView);
            List<String> selectedGenres = getSelectedFilters(genreListView);

            double minRatingValue = parseDoubleOrDefault(minRating.getText(), 0);
            double maxRatingValue = parseDoubleOrDefault(maxRating.getText(), 10);
            double minDurationValue = parseDoubleOrDefault(minDuration.getText(), 0);
            double maxDurationValue = parseDoubleOrDefault(maxDuration.getText(), 300);  // Default max duration is 300

            if (!selectedLanguages.isEmpty()) {
                searchResultContent = Search.filterLanguage(searchResultContent, selectedLanguages);
            }
            if (!selectedGenres.isEmpty()) {
                searchResultContent = Search.filterGenre(searchResultContent, selectedGenres);
            }

            // Apply rating filter
            searchResultContent = Search.filterRating(searchResultContent, minRatingValue, maxRatingValue);

            // Apply duration filter
            searchResultContent = Search.filterDuration(searchResultContent, minDurationValue, maxDurationValue);
            System.out.println(searchResultContent);
                displayContent(searchResultContent);
        }
        else if(searchType.equalsIgnoreCase("crew")){
            List<Crew> searchResultCrew = Search.SearchCrew(searchText);
            System.out.println(searchResultCrew);
            displayCrew(searchResultCrew);
        }

    }
    private void displayContent(List<Content> filteredContent) {
        resultsContainer.getChildren().clear();
        for (Content content : filteredContent) {
            // Create a container for each content
            AnchorPane contentPane = new AnchorPane();
            contentPane.setPrefWidth(250);
            contentPane.setPrefHeight(150);
            resultsContainer.setHgap(15); // 15px horizontal gap
            resultsContainer.setVgap(15); // 15px vertical gap


            // Create an ImageView and set its size and alignment
            ImageView movieImageView = new ImageView();
            movieImageView.setFitWidth(250);
            movieImageView.setFitHeight(130);

            contentPane.setOnMouseClicked(event -> {
                try {
                    SceneDetails.currentContent = content;
                    SceneManager.switchScene("/Admin/update_content.fxml");
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
            resultsContainer.getChildren().add(contentPane);
            resultsContainer.setVisible(true);
        }
    }

    private void displayCrew(List<Crew> crewList) {
        // Clear previous results
        resultsContainer.getChildren().clear();
        for (Crew crew  : crewList) {
            // Create AnchorPane for each movie
            AnchorPane crewPane = new AnchorPane();
            crewPane.setPrefWidth(150);
            crewPane.setPrefHeight(100);
            resultsContainer.setHgap(10); // 10px horizontal gap
            resultsContainer.setVgap(10); // 10px vertical gap
            // Set the background color of the AnchorPane to gray if the image cannot be loaded
            crewPane.setStyle(
                    "-fx-background-color: gray;" +
                            "-fx-background-radius: 5px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-border-width: 1px;"
            );

            ImageView crewImageView = new ImageView();
            crewImageView.setFitWidth(100);
            crewImageView.setFitHeight(100);
            crewImageView.setLayoutX(0);
            crewImageView.setLayoutY(0);

            try {
                // Try loading the image
                //Image image = new Image(crew.getImage());
                //crewImageView.setImage(image);

                // If image is not loaded properly, keep the gray background
//                if (image.isError()) {
//                     The background is already gray, so no need to modify it here
//                }

            } catch (Exception e) {
                // If there is an error loading the image, keep the gray background
            }
            crewImageView.setOnMouseClicked(event -> {
                try {
                    SceneDetails.crew = crew;
                    SceneManager.switchScene("/Crew/Crew_Scene.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Create Label for the movie title
            Label titleLabel = new Label(crew.getName());
            titleLabel.setLayoutX(7);
            titleLabel.setLayoutY(129);
            titleLabel.setPrefWidth(122);
            titleLabel.setPrefHeight(29);
            titleLabel.setTextFill(Color.WHITE);
            titleLabel.setAlignment(Pos.BASELINE_LEFT);
            titleLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;");

            // Add ImageView and Label to the crewPane
            crewPane.getChildren().addAll(crewImageView, titleLabel);

            // Add the content pane to the content container (VBox or HBox)
            resultsContainer.getChildren().add(crewPane);
        }
    }



    public List<String> getSelectedFilters(ListView<CheckBox>filterListView){
        List<String> selectedValues = new ArrayList<>();
        for(CheckBox checkBox : filterListView.getItems()){
            if(checkBox.isSelected()){
                selectedValues.add(checkBox.getText());
            }
        }
        return selectedValues;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpViewList(genres,genreListView);
        setUpViewList(languages,languageListView);
        contentRadioButton.setToggleGroup(searchTypeGroup);
        crewRadioButton.setToggleGroup(searchTypeGroup);
        genreListView.setVisible(false);
        languageListView.setVisible(false);
        filtersMenu.setVisible(false);
    }

    private void setUpViewList(String[] values,ListView<CheckBox> listView) {
        for (String value : values) {
            listView.getItems().add(new CheckBox(value)); // Set the value as the label
        }
    }
    @FXML
    private void toggleGenreListView() {
        if(languageListView.isVisible()){
            languageListView.setVisible(false);
        }
        genreListView.setVisible(!genreListView.isVisible());
    }
    @FXML
    private void toggleLanguageListView() {
        if(genreListView.isVisible()){
            genreListView.setVisible(false);
        }
        languageListView.setVisible(!languageListView.isVisible());
    }
    @FXML
    private void toggleFiltersMenuView(){
        filtersMenu.setVisible(!filtersMenu.isVisible());
    }
    private double parseDoubleOrDefault(String text, double defaultValue) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public void  getSearchFor(ActionEvent event) {
        if(contentRadioButton.isSelected()){
            searchBar.setPromptText("Search Movies and Shows");
            searchType="content";
        }
        else if(crewRadioButton.isSelected()) {
            searchBar.setPromptText("Search for Actors and Directors");
            searchType="crew";
        }
    }

    @FXML
    public void back() throws IOException
    {
        SceneManager.switchScene("/Admin/Admin_dashboard.fxml");
    }
}
