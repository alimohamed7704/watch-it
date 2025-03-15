package Content;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.image.Image;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Movie extends Content{
    public Movie(String title, String language, String country, LocalDate releaseDate, List<String> genres, int id, long budget, long revenue, double rating, long totalPeopleRated, double duration, String posterPath, String description, String logoPath) {
        super(title, language, country, releaseDate, genres, id, budget, revenue, rating, totalPeopleRated, duration, posterPath, description, logoPath);
    }

    @JsonCreator
    public Movie(
            @JsonProperty("crewIDs") List<Integer> crewIDs,
            @JsonProperty("title") String title,
            @JsonProperty("language") String language,
            @JsonProperty("country") String country,
            @JsonProperty("releaseDateString") String releaseDateString,
            @JsonProperty("genres") List<String> genres,
            @JsonProperty("id") int id,
            @JsonProperty("budget") long budget,
            @JsonProperty("revenue") long revenue,
            @JsonProperty("rating") double rating,
            @JsonProperty("totalPeopleRated") long totalPeopleRated,
            @JsonProperty("duration") double duration,
            @JsonProperty("posterPath") String posterPath,
            @JsonProperty("description") String description,
            @JsonProperty("logoPath") String logoPath) {
        this(title,language,country,LocalDate.parse(releaseDateString),genres,id,budget,revenue,rating,totalPeopleRated,duration,posterPath,description,logoPath);
        initializeCrewList(crewIDs);
    }




    public String getType(){
        return "Movie";
    }
}
// MOVIE
// Mafe4 feh haga
/*import java.util.ArrayList;
import java.util.List;

public class movies {
    private String title;
    private String imagePath;
    private String description;
    private String logoPath;
    public List<Crewc> crewList =new ArrayList<>();

    // Constructor
    public movies(String title, String imagePath, String description,String logoPath) {
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
        this.logoPath=logoPath;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }
    public String getLogoPath() {
        return logoPath;
    }
}
*/